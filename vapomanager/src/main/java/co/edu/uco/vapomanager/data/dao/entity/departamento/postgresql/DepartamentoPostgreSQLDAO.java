package co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private final Connection conexion;

    public DepartamentoPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private void asegurarConexionAbierta() throws OnlineTestException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var mensajeUsuario = "No hay conexión abierta para operar con departamentos.";
                var mensajeTecnico = "La conexión JDBC es null o está cerrada.";
                throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
            }
        } catch (SQLException e) {
            var mensajeUsuario = "Error verificando el estado de la conexión a la base de datos.";
            var mensajeTecnico = "SQLException al comprobar estado de la conexión: " + e.getMessage();
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, e);
        }
    }

    @Override
    public void create(DepartamentoEntity entity) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            String sql = "INSERT INTO departamento(id, nombre) VALUES (?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setObject(1, entity.getId());
                ps.setString(2, entity.getNombre());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al crear el departamento.";
            var tecnico = "SQLException en INSERT: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al crear el departamento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public void delete(UUID uuid) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            String sql = "DELETE FROM departamento WHERE id = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setObject(1, uuid);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al eliminar el departamento.";
            var tecnico = "SQLException en DELETE: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al eliminar el departamento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity filter) throws OnlineTestException {
        var resultados = new ArrayList<DepartamentoEntity>();
        try {
            asegurarConexionAbierta();
            String sql = "SELECT id, nombre FROM departamento WHERE nombre ILIKE ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, "%" + filter.getNombre() + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        var dept = new DepartamentoEntity();
                        dept.setId((UUID) rs.getObject("id"));
                        dept.setNombre(rs.getString("nombre"));
                        resultados.add(dept);
                    }
                }
            }
            return resultados;
        } catch (SQLException e) {
            var usuario = "Problema consultando departamentos por filtro.";
            var tecnico = "SQLException en SELECT filtro: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al consultar departamentos por filtro.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public List<DepartamentoEntity> listAll() throws OnlineTestException {
        var resultados = new ArrayList<DepartamentoEntity>();
        try {
            asegurarConexionAbierta();
            String sql = "SELECT id, nombre FROM departamento";
            try (Statement st = conexion.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    var dept = new DepartamentoEntity();
                    dept.setId((UUID) rs.getObject("id"));
                    dept.setNombre(rs.getString("nombre"));
                    resultados.add(dept);
                }
            }
            return resultados;
        } catch (SQLException e) {
            var usuario = "Problema listando todos los departamentos.";
            var tecnico = "SQLException en SELECT ALL: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al listar todos los departamentos.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public DepartamentoEntity listById(UUID uuid) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            String sql = "SELECT id, nombre FROM departamento WHERE id = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setObject(1, uuid);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        var dept = new DepartamentoEntity();
                        dept.setId((UUID) rs.getObject("id"));
                        dept.setNombre(rs.getString("nombre"));
                        return dept;
                    }
                }
            }
            return DepartamentoEntity.obtenerValorDefecto(null);
        } catch (SQLException e) {
            var usuario = "Problema consultando departamento por ID.";
            var tecnico = "SQLException en SELECT BY ID: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al consultar departamento por ID.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }

    @Override
    public void update(UUID uuid, DepartamentoEntity entity) throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            String sql = "UPDATE departamento SET nombre = ? WHERE id = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, entity.getNombre());
                ps.setObject(2, uuid);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            var usuario = "Se presentó un problema al actualizar el departamento.";
            var tecnico = "SQLException en UPDATE: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        } catch (OnlineTestException e) {
            throw e;
        } catch (Exception e) {
            var usuario = "Error inesperado al actualizar el departamento.";
            var tecnico = "Excepción inesperada: " + e.getMessage();
            throw DataOnlineTestException.reportar(usuario, tecnico, e);
        }
    }
}
