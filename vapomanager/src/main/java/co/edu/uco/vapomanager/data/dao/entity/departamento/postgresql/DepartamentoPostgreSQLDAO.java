package co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    
    private final Connection conexion;

    public DepartamentoPostgreSQLDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public List<DepartamentoEntity> listAll() throws VapomanagerException {

        final List<DepartamentoEntity> resultados = new ArrayList<>();
        final String sql = "SELECT id, nombre FROM departamento ORDER BY nombre ASC";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                var dto = new DepartamentoEntity();
                dto.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                dto.setNombre(rs.getString("nombre"));
                resultados.add(dto);
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar departamentos.",
                "SQLException en SELECT * de departamento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar departamentos.",
                "Excepción NO CONTROLADA en listAll.", e);
        }

        return resultados;
    }

    
    @Override
    public List<DepartamentoEntity> listByFilter(final DepartamentoEntity filter) throws VapomanagerException {

        final List<DepartamentoEntity> resultados = new ArrayList<>();
        final StringBuilder sql = new StringBuilder("""
            SELECT id, nombre
              FROM departamento
             WHERE 1 = 1
            """);

        final List<Object> params = new ArrayList<>();

        if (filter != null && filter.getNombre() != null && !filter.getNombre().isBlank()) {
            sql.append(" AND nombre ILIKE ?");
            params.add('%' + filter.getNombre().trim() + '%');
        }

        sql.append(" ORDER BY nombre ASC");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var dto = new DepartamentoEntity();
                    dto.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    dto.setNombre(rs.getString("nombre"));
                    resultados.add(dto);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al filtrar departamentos.",
                "SQLException en SELECT con filtro de departamento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al filtrar departamentos.",
                "Excepción NO CONTROLADA en listByFilter.", e);
        }

        return resultados;
    }

    
    @Override
    public DepartamentoEntity listById(final UUID id) throws VapomanagerException {

        final String sql = "SELECT id, nombre FROM departamento WHERE id = ?";
        DepartamentoEntity dto = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new DepartamentoEntity();
                    dto.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    dto.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar departamento por ID.",
                "SQLException en SELECT por ID de departamento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar departamento por ID.",
                "Excepción NO CONTROLADA en listById.", e);
        }

        return dto;
    }
}
