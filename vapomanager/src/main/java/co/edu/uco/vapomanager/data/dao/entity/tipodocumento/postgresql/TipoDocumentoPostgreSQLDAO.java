package co.edu.uco.vapomanager.data.dao.entity.tipodocumento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.tipodocumento.TipoDocumentoDAO;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoDocumentoPostgreSQLDAO implements TipoDocumentoDAO {

    private final Connection conexion;

    public TipoDocumentoPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var mensajeUsuario = "No hay conexión abierta para operar sobre tipos de documento.";
                var mensajeTecnico = "La conexión JDBC es null o está cerrada.";
                throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "Error verificando el estado de la conexión a la base de datos.";
            var mensajeTecnico = "SQLException comprobando conexión: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<TipoDocumentoEntity> listAll() throws VapomanagerException {
        asegurarConexionAbierta();
        var listaTiposDocumento = new ArrayList<TipoDocumentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento, descripcion FROM tipo_documento");

        try (Statement sentencia = conexion.createStatement();
             ResultSet cursorResultados = sentencia.executeQuery(senteciaSQL.toString())) {

            while (cursorResultados.next()) {
                var tipoDocumento = new TipoDocumentoEntity();
                tipoDocumento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                tipoDocumento.setNombre(cursorResultados.getString("tipo_documento"));
                tipoDocumento.setDescripcion(cursorResultados.getString("descripcion"));
                listaTiposDocumento.add(tipoDocumento);
            }
            return listaTiposDocumento;

        } catch (SQLException exception) {
            var mensajeUsuario = "Se presentó un problema listando todos los tipos de documento.";
            var mensajeTecnico = "SQLException en SELECT ALL: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al listar todos los tipos de documento.";
            var mensajeTecnico = "Excepción no controlada en SELECT ALL: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<TipoDocumentoEntity> listByFilter(TipoDocumentoEntity filter) throws VapomanagerException {
        asegurarConexionAbierta();
        var listaTiposDocumento = new ArrayList<TipoDocumentoEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento, descripcion FROM tipo_documento WHERE tipo_documento ILIKE ?");

        try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setString(1, "%" + filter.getNombre() + "%");
            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var tipoDocumento = new TipoDocumentoEntity();
                    tipoDocumento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    tipoDocumento.setNombre(cursorResultados.getString("tipo_documento"));
                    tipoDocumento.setDescripcion(cursorResultados.getString("descripcion"));
                    listaTiposDocumento.add(tipoDocumento);
                }
            }
            return listaTiposDocumento;

        } catch (SQLException exception) {
            var mensajeUsuario = "Se presentó un problema consultando tipos de documento por filtro.";
            var mensajeTecnico = "SQLException en SELECT filtro: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar tipos de documento por filtro.";
            var mensajeTecnico = "Excepción no controlada en SELECT filtro: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public TipoDocumentoEntity listById(UUID id) throws VapomanagerException {
        asegurarConexionAbierta();
        var tipoDocumentoRetorno = new TipoDocumentoEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, tipo_documento, descripcion FROM tipo_documento WHERE id = ?");

        try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);
            try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    tipoDocumentoRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    tipoDocumentoRetorno.setNombre(cursorResultados.getString("tipo_documento"));
                    tipoDocumentoRetorno.setDescripcion(cursorResultados.getString("descripcion"));
                }
            }
            return tipoDocumentoRetorno;

        } catch (SQLException exception) {
            var mensajeUsuario = "Se presentó un problema consultando el tipo de documento por ID.";
            var mensajeTecnico = "SQLException en SELECT BY ID: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar el tipo de documento por ID.";
            var mensajeTecnico = "Excepción no controlada en SELECT BY ID: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
