package co.edu.uco.vapomanager.data.dao.entity.tipodocumento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;
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

    private void asegurarConexionAbierta() throws OnlineTestException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var usuario = "No hay conexión abierta para operar con tipos de documento.";
                var tecnico = "La conexión JDBC es null o está cerrada.";
                throw DataOnlineTestException.reportar(usuario, tecnico);
            }
        } catch (SQLException e) {
            var usuario = "Error verificando el estado de la conexión a la base de datos.";
            var tecnico = "SQLException al comprobar estado de la conexión: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public void create(TipoDocumentoEntity entity) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            var sql = new StringBuilder();
            sql.append("INSERT INTO tipo_documento(id, nombre, descripcion) VALUES (?, ?, ?)");
            try (var ps = conexion.prepareStatement(sql.toString())) {
                ps.setObject(1, entity.getId());
                ps.setString(2, entity.getNombre());
                ps.setString(3, entity.getDescripcion());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al crear el tipo de documento.";
            var tecnico = "SQLException en INSERT: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al crear el tipo de documento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public void delete(UUID uuid) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            var sql = new StringBuilder();
            sql.append("DELETE FROM tipo_documento WHERE id = ?");
            try (var ps = conexion.prepareStatement(sql.toString())) {
                ps.setObject(1, uuid);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al eliminar el tipo de documento.";
            var tecnico = "SQLException en DELETE: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al eliminar el tipo de documento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public List<TipoDocumentoEntity> listByFilter(TipoDocumentoEntity filter) throws OnlineTestException {
        var resultados = new ArrayList<TipoDocumentoEntity>();
        try {
            asegurarConexionAbierta();
            var sql = new StringBuilder();
            sql.append("SELECT id, nombre, descripcion FROM tipo_documento WHERE nombre ILIKE ?");
            try (var ps = conexion.prepareStatement(sql.toString())) {
                ps.setString(1, "%" + filter.getNombre() + "%");
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        var td = new TipoDocumentoEntity();
                        td.setId((UUID) rs.getObject("id"));
                        td.setNombre(rs.getString("nombre"));
                        td.setDescripcion(rs.getString("descripcion"));
                        resultados.add(td);
                    }
                }
            }
            return resultados;
        } catch (SQLException e) {
            var usuario = "Problema consultando tipos de documento por filtro.";
            var tecnico = "SQLException en SELECT filtro: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al consultar tipos de documento por filtro.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public List<TipoDocumentoEntity> listAll() throws OnlineTestException {
        var resultados = new ArrayList<TipoDocumentoEntity>();
        try {
            asegurarConexionAbierta();
            var sql = new StringBuilder();
            sql.append("SELECT id, nombre, descripcion FROM tipo_documento");
            try (var st = conexion.createStatement();
                 var rs = st.executeQuery(sql.toString())) {
                while (rs.next()) {
                    var td = new TipoDocumentoEntity();
                    td.setId((UUID) rs.getObject("id"));
                    td.setNombre(rs.getString("nombre"));
                    td.setDescripcion(rs.getString("descripcion"));
                    resultados.add(td);
                }
            }
            return resultados;
        } catch (SQLException e) {
            var usuario = "Problema listando todos los tipos de documento.";
            var tecnico = "SQLException en SELECT ALL: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al listar todos los tipos de documento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public TipoDocumentoEntity listById(UUID uuid) throws OnlineTestException {
        var tipoDocumentoRetorno = new TipoDocumentoEntity();
        var sql = new StringBuilder();
        sql.append("SELECT id, nombre, descripcion FROM tipo_documento WHERE id = ?");
        try (var ps = conexion.prepareStatement(sql.toString())) {
            ps.setObject(1, uuid);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipoDocumentoRetorno.setId((UUID) rs.getObject("id"));
                    tipoDocumentoRetorno.setNombre(rs.getString("nombre"));
                    tipoDocumentoRetorno.setDescripcion(rs.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            var usuario = "Problema consultando tipo de documento por ID.";
            var tecnico = "SQLException en SELECT BY ID: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (Exception e) {
            var usuario = "Error inesperado al consultar tipo de documento por ID.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
        return tipoDocumentoRetorno;
    }

    @Override
    public void update(UUID uuid, TipoDocumentoEntity entity) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            var sql = new StringBuilder();
            sql.append("UPDATE tipo_documento SET nombre = ?, descripcion = ? WHERE id = ?");
            try (var ps = conexion.prepareStatement(sql.toString())) {
                ps.setString(1, entity.getNombre());
                ps.setString(2, entity.getDescripcion());
                ps.setObject(3, uuid);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al actualizar el tipo de documento.";
            var tecnico = "SQLException en UPDATE: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al actualizar el tipo de documento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }
}