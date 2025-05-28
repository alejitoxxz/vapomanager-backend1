package co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private final DataSource dataSource;

    public DepartamentoPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<DepartamentoEntity> listAll() throws VapomanagerException {
        var listaResultados = new ArrayList<DepartamentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre FROM departamento ORDER BY nombre ASC");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
            ResultSet cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var departamentoRetorno = new DepartamentoEntity();
                departamentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                departamentoRetorno.setNombre(cursorResultados.getString("nombre"));

                listaResultados.add(departamentoRetorno);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los departamentos...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla departamento para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo departamento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla departamento, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity filter) throws VapomanagerException {
        var listaResultados = new ArrayList<DepartamentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre FROM departamento ORDER BY nombre ASC");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
            ResultSet cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var departamentoRetorno = new DepartamentoEntity();
                departamentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                departamentoRetorno.setNombre(cursorResultados.getString("nombre"));

                listaResultados.add(departamentoRetorno);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los departamentos...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla departamento para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo departamento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla departamento, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public DepartamentoEntity listById(UUID id) throws VapomanagerException {
        var departamentoRetorno = new DepartamentoEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre FROM departamento WHERE id = ?");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())
        ) {
            sentenciaPreparada.setObject(1, id);

            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    departamentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    departamentoRetorno.setNombre(cursorResultados.getString("nombre"));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el departamento con el identificador deseado la informacion del nuevo departamento...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla departamento por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo departamento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla departamento, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return departamentoRetorno;
    }
}