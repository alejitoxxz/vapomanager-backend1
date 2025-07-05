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

public final class CiudadPostgreSQLDAO implements CiudadDAO {

   
    private final Connection conexion;

    public CiudadPostgreSQLDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public List<CiudadEntity> listAll() throws VapomanagerException {

        final List<CiudadEntity> resultados = new ArrayList<>();
        final String sql = """
            SELECT id, nombre, departamento_id
              FROM ciudad
          ORDER BY nombre ASC
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(mapearCiudad(rs));
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar ciudades.",
                "SQLException en SELECT * de ciudad.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar ciudades.",
                "Excepción NO CONTROLADA en listAll.", e);
        }

        return resultados;
    }

    
    @Override
    public List<CiudadEntity> listByFilter(final CiudadEntity filter) throws VapomanagerException {

        final List<CiudadEntity> resultados = new ArrayList<>();
        final StringBuilder sql = new StringBuilder("""
            SELECT c.id, c.nombre,
                   d.id AS departamento_id,
                   d.nombre AS departamento_nombre
              FROM ciudad c
         INNER JOIN departamento d ON c.departamento_id = d.id
             WHERE 1 = 1
            """);

        final List<Object> params = new ArrayList<>();

        if (!UtilUUID.esValorDefecto(filter.getId())) {
            sql.append(" AND c.id = ?");
            params.add(filter.getId());
        }
        if (filter.getNombre() != null && !filter.getNombre().isBlank()) {
            sql.append(" AND c.nombre ILIKE ?");
            params.add('%' + filter.getNombre().trim() + '%');
        }
        if (filter.getDepartamento() != null && !UtilUUID.esValorDefecto(filter.getDepartamento().getId())) {
            sql.append(" AND d.id = ?");
            params.add(filter.getDepartamento().getId());
        }

        sql.append(" ORDER BY c.nombre ASC");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var ciudad = mapearCiudad(rs);
                    ciudad.getDepartamento().setNombre(rs.getString("departamento_nombre"));
                    resultados.add(ciudad);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al filtrar ciudades.",
                "SQLException en SELECT con filtro de ciudad.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al filtrar ciudades.",
                "Excepción NO CONTROLADA en listByFilter.", e);
        }

        return resultados;
    }

    
    @Override
    public CiudadEntity listById(final UUID id) throws VapomanagerException {

        final String sql = """
            SELECT id, nombre, departamento_id
              FROM ciudad
             WHERE id = ?
            """;

        CiudadEntity ciudad = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ciudad = mapearCiudad(rs);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar ciudad por ID.",
                "SQLException en SELECT por ID de ciudad.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar ciudad por ID.",
                "Excepción NO CONTROLADA en listById.", e);
        }

        return ciudad;
    }

    
    private CiudadEntity mapearCiudad(final ResultSet rs) throws SQLException {

        var ciudad = new CiudadEntity();
        ciudad.setId(UtilUUID.convertirAUUID(rs.getString("id")));
        ciudad.setNombre(rs.getString("nombre"));

        var depto = new DepartamentoEntity();
        depto.setId(UtilUUID.convertirAUUID(rs.getString("departamento_id")));
        ciudad.setDepartamento(depto);

        return ciudad;
    }
}
