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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TipoDocumentoPostgreSQLDAO implements TipoDocumentoDAO {

    
    private final Connection conexion;

    public TipoDocumentoPostgreSQLDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public List<TipoDocumentoEntity> listAll() throws VapomanagerException {

        final List<TipoDocumentoEntity> resultados = new ArrayList<>();
        final String sql = "SELECT id, tipo_documento FROM tipo_documento ORDER BY tipo_documento ASC";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                var tipo = new TipoDocumentoEntity();
                tipo.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                tipo.setNombre(rs.getString("tipo_documento"));
                resultados.add(tipo);
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar tipos de documento.",
                "SQLException en SELECT * de tipo_documento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar tipos de documento.",
                "Excepción NO CONTROLADA en listAll.", e);
        }

        return resultados;
    }

    
    @Override
    public List<TipoDocumentoEntity> listByFilter(final TipoDocumentoEntity filter) throws VapomanagerException {

        final List<TipoDocumentoEntity> resultados = new ArrayList<>();
        final StringBuilder sql = new StringBuilder("""
            SELECT id, tipo_documento
              FROM tipo_documento
             WHERE 1 = 1
            """);

        final List<Object> params = new ArrayList<>();

        if (filter != null && filter.getNombre() != null && !filter.getNombre().isBlank()) {
            sql.append(" AND tipo_documento ILIKE ?");
            params.add('%' + filter.getNombre().trim() + '%');
        }

        sql.append(" ORDER BY tipo_documento ASC");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var tipo = new TipoDocumentoEntity();
                    tipo.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    tipo.setNombre(rs.getString("tipo_documento"));
                    resultados.add(tipo);
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al filtrar tipos de documento.",
                "SQLException en SELECT con filtro de tipo_documento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al filtrar tipos de documento.",
                "Excepción NO CONTROLADA en listByFilter.", e);
        }

        return resultados;
    }

    
    @Override
    public TipoDocumentoEntity listById(final UUID id) throws VapomanagerException {

        final String sql = "SELECT id, tipo_documento FROM tipo_documento WHERE id = ?";
        TipoDocumentoEntity tipo = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipo = new TipoDocumentoEntity();
                    tipo.setId(UtilUUID.convertirAUUID(rs.getString("id")));
                    tipo.setNombre(rs.getString("tipo_documento"));
                }
            }

        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Error al consultar tipo de documento por ID.",
                "SQLException en SELECT por ID de tipo_documento.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Error inesperado al consultar tipo de documento por ID.",
                "Excepción NO CONTROLADA en listById.", e);
        }

        return tipo;
    }
}
