package co.edu.uco.vapomanager.data.dao.entity.administrador.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.administrador.AdministradorDAO;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AdministradorPostgreSQLDAO implements AdministradorDAO {

   
    private final Connection conexion;

    public AdministradorPostgreSQLDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public void create(final AdministradorEntity entity) throws VapomanagerException {

        final String sql = "INSERT INTO administrador(id, correo) VALUES (?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getCorreo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema al registrar el administrador.",
                "SQLException al INSERT en tabla administrador.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Ocurrió un problema INESPERADO al registrar el administrador.",
                "Excepción NO CONTROLADA en INSERT de administrador.", e);
        }
    }

    
    @Override
    public List<AdministradorEntity> listByFilter(final AdministradorEntity filter) throws VapomanagerException {

        final List<AdministradorEntity> resultados = new ArrayList<>();
        final StringBuilder sql = new StringBuilder("""
            SELECT id, correo
              FROM administrador
             WHERE 1 = 1
            """);

        final List<Object> params = new ArrayList<>();

        if (filter != null && filter.getCorreo() != null && !filter.getCorreo().isBlank()) {
            sql.append(" AND correo ILIKE ?");
            params.add('%' + filter.getCorreo().trim() + '%');
        }

        sql.append(" ORDER BY correo ASC");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var admin = new AdministradorEntity();
                    admin.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    admin.setCorreo(rs.getString("correo"));
                    resultados.add(admin);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema al consultar administradores.",
                "SQLException en SELECT con filtro de administrador.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Ocurrió un problema INESPERADO al consultar administradores.",
                "Excepción NO CONTROLADA en listByFilter.", e);
        }

        return resultados;
    }

    
    @Override
    public List<AdministradorEntity> listAll() throws VapomanagerException {
        return listByFilter(new AdministradorEntity());
    }

    
    @Override
    public AdministradorEntity listById(final UUID id) throws VapomanagerException {

        final String sql = "SELECT id, correo FROM administrador WHERE id = ?";
        AdministradorEntity admin = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new AdministradorEntity();
                    admin.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    admin.setCorreo(rs.getString("correo"));
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema al consultar administrador por ID.",
                "SQLException en SELECT por ID de administrador.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema INESPERADO al consultar administrador por ID.",
                "Excepción NO CONTROLADA en listById.", e);
        }

        return admin;
    }

    
    @Override
    public void update(final UUID id, final AdministradorEntity entity) throws VapomanagerException {

        final String sql = "UPDATE administrador SET correo = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, entity.getCorreo());
            ps.setObject(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema al modificar el administrador.",
                "SQLException en UPDATE de administrador.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema INESPERADO al modificar el administrador.",
                "Excepción NO CONTROLADA en update.", e);
        }
    }

    
    @Override
    public void delete(final UUID id) throws VapomanagerException {

        final String sql = "DELETE FROM administrador WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema al eliminar el administrador.",
                "SQLException en DELETE de administrador.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema INESPERADO al eliminar el administrador.",
                "Excepción NO CONTROLADA en delete.", e);
        }
    }
}
