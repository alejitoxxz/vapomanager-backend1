package co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.entity.CiudadEntity;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private final DataSource dataSource;

    public CiudadPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CiudadEntity> listAll() throws VapomanagerException {
        var listaResultados = new ArrayList<CiudadEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad ORDER BY nombre ASC");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
            ResultSet cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var ciudadRetorno = new CiudadEntity();
                ciudadRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                ciudadRetorno.setNombre(cursorResultados.getString("nombre"));
                var departamento = new DepartamentoEntity();
                departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                ciudadRetorno.setDepartamento(departamento);

                listaResultados.add(ciudadRetorno);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos las ciudades...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla ciudad para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo ciudad...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla ciudad, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public List<CiudadEntity> listByFilter(CiudadEntity filter) throws VapomanagerException {
        var listaResultados = new ArrayList<CiudadEntity>();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT c.id AS ciudad_id, c.nombre AS ciudad_nombre, d.id AS departamento_id, d.nombre AS departamento_nombre ");
        sentenciaSQL.append("FROM ciudad c ");
        sentenciaSQL.append("INNER JOIN departamento d ON c.departamento_id = d.id ");

        var condiciones = new ArrayList<String>();
        if (!UtilUUID.esValorDefecto(filter.getId())) {
            condiciones.add("c.id = ?");
        }
        if (!UtilUUID.esValorDefecto(filter.getDepartamento().getId())) {
            condiciones.add("d.id = ?");
        }

        if (!condiciones.isEmpty()) {
            sentenciaSQL.append("WHERE ");
            sentenciaSQL.append(String.join(" AND ", condiciones));
        }

        sentenciaSQL.append(" ORDER BY c.nombre ASC");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())
        ) {
            int posicion = 1;

            if (!UtilUUID.esValorDefecto(filter.getId())) {
                sentenciaPreparada.setObject(posicion++, filter.getId());
            }
            if (!UtilUUID.esValorDefecto(filter.getDepartamento().getId())) {
                sentenciaPreparada.setObject(posicion++, filter.getDepartamento().getId());
            }

            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var ciudadRetorno = new CiudadEntity();
                    ciudadRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("ciudad_id")));
                    ciudadRetorno.setNombre(cursorResultados.getString("ciudad_nombre"));

                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                    departamento.setNombre(cursorResultados.getString("departamento_nombre"));
                    ciudadRetorno.setDepartamento(departamento);

                    listaResultados.add(ciudadRetorno);
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "Se presentó un problema tratando de consultar las ciudades filtradas.";
            var mensajeTecnico = "Se lanzó SQLException ejecutando SELECT con JOIN en la tabla ciudad y departamento.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Se presentó un problema inesperado al consultar las ciudades filtradas.";
            var mensajeTecnico = "Se lanzó una excepción NO CONTROLADA al hacer SELECT con JOIN.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public CiudadEntity listById(UUID id) throws VapomanagerException {
        var ciudadRetorno = new CiudadEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE id = ?");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())
        ) {
            sentenciaPreparada.setObject(1, id);

            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    ciudadRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    ciudadRetorno.setNombre(cursorResultados.getString("nombre"));
                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                    ciudadRetorno.setDepartamento(departamento);
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el ciudad con el identificador deseado la informacion del nuevo ciudad...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla ciudad por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo ciudad...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla ciudad, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return ciudadRetorno;
    }
}