package co.edu.uco.vapomanager.data.dao.entity.proveedor.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.proveedor.ProveedorDAO;
import co.edu.uco.vapomanager.entity.CiudadEntity;
import co.edu.uco.vapomanager.entity.ProveedorEntity;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProveedorPostgreSQLDAO implements ProveedorDAO {

    
    private final Connection conexion;

    public ProveedorPostgreSQLDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public void create(final ProveedorEntity entity) throws VapomanagerException {

        final String sql = """
            INSERT INTO proveedor (
                id, nombre_empresa, confirmacion_telefono, confirmacion_correo,
                correo_electronico, estado_cuenta, direccion, ciudad_id,
                descripcion_direccion, tipo_documento_id, numero_documento,
                numero_telefono
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1,  entity.getId());
            ps.setString(2,  entity.getNombreEmpresa());
            ps.setBoolean(3, entity.isConfirmacionTelefono());
            ps.setBoolean(4, entity.isConfirmacionCorreo());
            ps.setString(5,  entity.getCorreoElectronico());
            ps.setBoolean(6, entity.isEstadoCuenta());
            ps.setString(7,  entity.getDireccion());
            ps.setObject(8,  entity.getCiudad().getId());
            ps.setString(9,  entity.getDescripcionDireccion());
            ps.setObject(10, entity.getTipoDocumento().getId());
            ps.setLong(11,  entity.getNumeroDocumento());
            ps.setLong(12,  entity.getNumeroTelefono());

            ps.executeUpdate();

        } catch (SQLException e) {
            final String msg = e.getMessage().toLowerCase();

            if (msg.contains("fk_proveedor_ciudad")) {
                throw BusinessLogicVapomanagerException.reportar(
                    "La ciudad seleccionada no existe.",
                    "Violación FK_PROVEEDOR_CIUDAD.", e);
            }
            if (msg.contains("fk_proveedor_tipodocumento")) {
                throw BusinessLogicVapomanagerException.reportar(
                    "El tipo de documento seleccionado no existe.",
                    "Violación FK_PROVEEDOR_TIPODOCUMENTO.", e);
            }
            if (msg.contains("fk_proveedor_administrador")) {
                throw BusinessLogicVapomanagerException.reportar(
                    "El administrador asignado no existe.",
                    "Violación FK_PROVEEDOR_ADMINISTRADOR.", e);
            }

            throw DataVapomanagerException.reportar(
                "Error al registrar el proveedor.",
                "SQLException en INSERT de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al registrar el proveedor.",
                "Excepción NO CONTROLADA en INSERT de proveedor.", e);
        }
    }

    
    @Override
    public List<ProveedorEntity> listByFilter(final ProveedorEntity filter) throws VapomanagerException {

        final List<ProveedorEntity> resultados = new ArrayList<>();
        final StringBuilder sql = new StringBuilder("""
            SELECT id, nombre_empresa, confirmacion_telefono, confirmacion_correo,
                   correo_electronico, estado_cuenta, direccion, ciudad_id,
                   descripcion_direccion, tipo_documento_id, numero_documento,
                   numero_telefono
              FROM proveedor
             WHERE 1 = 1
            """);

        final List<Object> params = new ArrayList<>();

        if (filter != null) {
            if (filter.getCorreoElectronico() != null && !filter.getCorreoElectronico().isBlank()) {
                sql.append(" AND correo_electronico ILIKE ?");
                params.add('%' + filter.getCorreoElectronico().trim() + '%');
            }
            if (filter.getNumeroDocumento() != null && filter.getNumeroDocumento() > 0) {
                sql.append(" AND numero_documento = ?");
                params.add(filter.getNumeroDocumento());
            }
            if (filter.getNumeroTelefono() != null && filter.getNumeroTelefono() > 0) {
                sql.append(" AND numero_telefono = ?");
                params.add(filter.getNumeroTelefono());
            }
        }
        sql.append(" ORDER BY nombre_empresa ASC");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.add(mapearProveedor(rs));
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar proveedores por filtro.",
                "SQLException en SELECT dinámico de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar proveedores.",
                "Excepción NO CONTROLADA en listByFilter.", e);
        }

        return resultados;
    }

    
    @Override
    public List<ProveedorEntity> listAll() throws VapomanagerException {

        final List<ProveedorEntity> resultados = new ArrayList<>();
        final String sql = """
            SELECT id, nombre_empresa, confirmacion_telefono, confirmacion_correo,
                   correo_electronico, estado_cuenta, direccion, ciudad_id,
                   descripcion_direccion, tipo_documento_id, numero_documento,
                   numero_telefono
              FROM proveedor
          ORDER BY nombre_empresa ASC
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(mapearProveedor(rs));
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar todos los proveedores.",
                "SQLException en SELECT * de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar proveedores.",
                "Excepción NO CONTROLADA en listAll.", e);
        }

        return resultados;
    }

    
    @Override
    public ProveedorEntity listById(final UUID id) throws VapomanagerException {

        final String sql = """
            SELECT id, nombre_empresa, confirmacion_telefono, confirmacion_correo,
                   correo_electronico, estado_cuenta, direccion, ciudad_id,
                   descripcion_direccion, tipo_documento_id, numero_documento,
                   numero_telefono
              FROM proveedor
             WHERE id = ?
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearProveedor(rs);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar proveedor por ID.",
                "SQLException en SELECT por ID de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar proveedor por ID.",
                "Excepción NO CONTROLADA en listById.", e);
        }

        return null;
    }

    
    @Override
    public void update(final UUID id, final ProveedorEntity entity) throws VapomanagerException {

        final String sql = """
            UPDATE proveedor SET
                nombre_empresa       = ?,
                confirmacion_telefono = ?,
                confirmacion_correo   = ?,
                correo_electronico    = ?,
                estado_cuenta         = ?,
                direccion             = ?,
                ciudad_id             = ?,
                descripcion_direccion = ?,
                tipo_documento_id     = ?,
                numero_documento      = ?,
                numero_telefono       = ?
            WHERE id = ?
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1,  entity.getNombreEmpresa());
            ps.setBoolean(2, entity.isConfirmacionTelefono());
            ps.setBoolean(3, entity.isConfirmacionCorreo());
            ps.setString(4,  entity.getCorreoElectronico());
            ps.setBoolean(5, entity.isEstadoCuenta());
            ps.setString(6,  entity.getDireccion());
            ps.setObject(7,  entity.getCiudad().getId());
            ps.setString(8,  entity.getDescripcionDireccion());
            ps.setObject(9,  entity.getTipoDocumento().getId());
            ps.setLong(10,  entity.getNumeroDocumento());
            ps.setLong(11,  entity.getNumeroTelefono());
            ps.setObject(12, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al modificar proveedor.",
                "SQLException en UPDATE de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al modificar proveedor.",
                "Excepción NO CONTROLADA en update.", e);
        }
    }

    
    @Override
    public void delete(final UUID id) throws VapomanagerException {

        final String sql = "DELETE FROM proveedor WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al eliminar proveedor.",
                "SQLException en DELETE de proveedor.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al eliminar proveedor.",
                "Excepción NO CONTROLADA en delete.", e);
        }
    }

    
    private ProveedorEntity mapearProveedor(final ResultSet rs) throws SQLException {

        var proveedor = new ProveedorEntity();

        proveedor.setId(UtilUUID.convertirAUUID(rs.getString("id")));
        proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
        proveedor.setConfirmacionTelefono(rs.getBoolean("confirmacion_telefono"));
        proveedor.setConfirmacionCorreo(rs.getBoolean("confirmacion_correo"));
        proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
        proveedor.setEstadoCuenta(rs.getBoolean("estado_cuenta"));
        proveedor.setDireccion(rs.getString("direccion"));

        var ciudad = new CiudadEntity();
        ciudad.setId(UtilUUID.convertirAUUID(rs.getString("ciudad_id")));
        proveedor.setCiudad(ciudad);

        proveedor.setDescripcionDireccion(rs.getString("descripcion_direccion"));

        var tipoDoc = new TipoDocumentoEntity();
        tipoDoc.setId(UtilUUID.convertirAUUID(rs.getString("tipo_documento_id")));
        proveedor.setTipoDocumento(tipoDoc);

        proveedor.setNumeroDocumento(rs.getLong("numero_documento"));
        proveedor.setNumeroTelefono(rs.getLong("numero_telefono"));

        return proveedor;
    }
}
