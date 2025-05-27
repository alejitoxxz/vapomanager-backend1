package co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.entity.CiudadEntity;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private final Connection conexion;

    public CiudadPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<CiudadEntity> listAll() throws VapomanagerException {
        var listaResultados = new ArrayList<CiudadEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad ORDER BY nombre ASC");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var ciudadRetorno = new CiudadEntity();
                    ciudadRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    ciudadRetorno.setNombre(cursorResultados.getString("nombre"));
                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                    ciudadRetorno.setDepartamento(departamento);

                    listaResultados.add(ciudadRetorno);
                }
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
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad ORDER BY nombre ASC");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var ciudadRetorno = new CiudadEntity();
                    ciudadRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    ciudadRetorno.setNombre(cursorResultados.getString("nombre"));
                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                    ciudadRetorno.setDepartamento(departamento);

                    listaResultados.add(ciudadRetorno);
                }
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
    public CiudadEntity listById(UUID id) throws VapomanagerException {
        var ciudadRetorno = new CiudadEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
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
