package co.edu.uco.vapomanager.data.dao.entity.tipodocumento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.tipodocumento.TipoDocumentoDAO;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoDocumentoPostgreSQLDAO implements TipoDocumentoDAO {

    private final DataSource dataSource;

    public TipoDocumentoPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TipoDocumentoEntity> listAll() throws VapomanagerException {
        var listaTiposDocumento = new ArrayList<TipoDocumentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento FROM tipo_documento");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
            ResultSet cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var tipoDocumento = new TipoDocumentoEntity();
                tipoDocumento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                tipoDocumento.setNombre(cursorResultados.getString("tipo_documento"));
                listaTiposDocumento.add(tipoDocumento);
            }
            return listaTiposDocumento;

        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los tipodocumentos...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla tipodocumento para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo tipodocumento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla tipodocumento, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<TipoDocumentoEntity> listByFilter(TipoDocumentoEntity filter) throws VapomanagerException {
        var listaResultados = new ArrayList<TipoDocumentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento FROM tipo_documento ORDER BY tipo_documento ASC");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
            ResultSet cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var tipoDocumentoRetorno = new TipoDocumentoEntity();
                tipoDocumentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                tipoDocumentoRetorno.setNombre(cursorResultados.getString("tipo_documento"));
                listaResultados.add(tipoDocumentoRetorno);
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los tipodocumentos...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla tipodocumento para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo tipodocumento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla tipodocumento, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public TipoDocumentoEntity listById(UUID id) throws VapomanagerException {
        var tipoDocumentoRetorno = new TipoDocumentoEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento FROM tipo_documento WHERE id = ?");

        try (
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())
        ) {
            sentenciaPreparada.setObject(1, id);
            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    tipoDocumentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    tipoDocumentoRetorno.setNombre(cursorResultados.getString("tipo_documento"));
                }
            }
            return tipoDocumentoRetorno;

        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el tipodocumento con el identificador deseado la informacion del nuevo tipodocumento...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla tipodocumento por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo tipodocumento...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla tipodocumento, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
