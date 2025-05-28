package co.edu.uco.vapomanager.data.dao.entity.proveedor.postgresql;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.entity.proveedor.ProveedorDAO;
import co.edu.uco.vapomanager.entity.ProveedorEntity;
import co.edu.uco.vapomanager.entity.CiudadEntity;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProveedorPostgreSQLDAO implements ProveedorDAO {

    private final DataSource dataSource;

    public ProveedorPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(ProveedorEntity entity) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("""
            INSERT INTO proveedor(
              id,
              nombre_empresa,
              confirmacion_telefono,
              confirmacion_correo,
              correo_electronico,
              estado_cuenta,
              direccion,
              ciudad_id,
              descripcion_direccion,
              tipo_documento_id,
              numero_documento
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """);

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {

            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getNombreEmpresa());
            sentenciaPreparada.setBoolean(3, entity.isConfirmacionTelefono());
            sentenciaPreparada.setBoolean(4, entity.isConfirmacionCorreo());
            sentenciaPreparada.setString(5, entity.getCorreoElectronico());
            sentenciaPreparada.setBoolean(6, entity.isEstadoCuenta());
            sentenciaPreparada.setString(7, entity.getDireccion());
            sentenciaPreparada.setObject(8, entity.getCiudad().getId());
            sentenciaPreparada.setString(9, entity.getDescripcionDireccion());
            sentenciaPreparada.setObject(10, entity.getTipoDocumento().getId());
            sentenciaPreparada.setInt(11, entity.getNumeroDocumento());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de registrar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un INSERT en la tabla proveedor, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de registrar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un INSERT en la tabla proveedor, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<ProveedorEntity> listByFilter(ProveedorEntity filter) throws VapomanagerException {
        return listAll();
    }

    @Override
    public List<ProveedorEntity> listAll() throws VapomanagerException {
        var listaResultados = new ArrayList<ProveedorEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre_empresa, confirmacion_telefono, confirmacion_correo, correo_electronico, estado_cuenta, direccion, ciudad_id, descripcion_direccion, tipo_documento_id, numero_documento FROM proveedor ORDER BY nombre_empresa ASC");

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString());
             var cursorResultados = sentenciaPreparada.executeQuery()) {

            while (cursorResultados.next()) {
                var proveedor = new ProveedorEntity();
                proveedor.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                proveedor.setNombreEmpresa(cursorResultados.getString("nombre_empresa"));
                proveedor.setConfirmacionTelefono(cursorResultados.getBoolean("confirmacion_telefono"));
                proveedor.setConfirmacionCorreo(cursorResultados.getBoolean("confirmacion_correo"));
                proveedor.setCorreoElectronico(cursorResultados.getString("correo_electronico"));
                proveedor.setEstadoCuenta(cursorResultados.getBoolean("estado_cuenta"));
                proveedor.setDireccion(cursorResultados.getString("direccion"));
                var ciudad = new CiudadEntity();
                ciudad.setId(UtilUUID.convertirAUUID(cursorResultados.getString("ciudad_id")));
                proveedor.setCiudad(ciudad);
                proveedor.setDescripcionDireccion(cursorResultados.getString("descripcion_direccion"));
                var tipoDoc = new TipoDocumentoEntity();
                tipoDoc.setId(UtilUUID.convertirAUUID(cursorResultados.getString("tipo_documento_id")));
                proveedor.setTipoDocumento(tipoDoc);
                proveedor.setNumeroDocumento(cursorResultados.getInt("numero_documento"));
                listaResultados.add(proveedor);
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los proveedores...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla proveedor para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla proveedor, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public ProveedorEntity listById(UUID id) throws VapomanagerException {
        var proveedor = new ProveedorEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre_empresa, confirmacion_telefono, confirmacion_correo, correo_electronico, estado_cuenta, direccion, ciudad_id, descripcion_direccion, tipo_documento_id, numero_documento FROM proveedor WHERE id = ?");

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);
            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    proveedor.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    proveedor.setNombreEmpresa(cursorResultados.getString("nombre_empresa"));
                    proveedor.setConfirmacionTelefono(cursorResultados.getBoolean("confirmacion_telefono"));
                    proveedor.setConfirmacionCorreo(cursorResultados.getBoolean("confirmacion_correo"));
                    proveedor.setCorreoElectronico(cursorResultados.getString("correo_electronico"));
                    proveedor.setEstadoCuenta(cursorResultados.getBoolean("estado_cuenta"));
                    proveedor.setDireccion(cursorResultados.getString("direccion"));
                    var ciudad = new CiudadEntity();
                    ciudad.setId(UtilUUID.convertirAUUID(cursorResultados.getString("ciudad_id")));
                    proveedor.setCiudad(ciudad);
                    proveedor.setDescripcionDireccion(cursorResultados.getString("descripcion_direccion"));
                    var tipoDoc = new TipoDocumentoEntity();
                    tipoDoc.setId(UtilUUID.convertirAUUID(cursorResultados.getString("tipo_documento_id")));
                    proveedor.setTipoDocumento(tipoDoc);
                    proveedor.setNumeroDocumento(cursorResultados.getInt("numero_documento"));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el proveedor con el identificador deseado la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla proveedor por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla proveedor, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return proveedor;
    }

    @Override
    public void update(UUID id, ProveedorEntity entity) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("""
            UPDATE proveedor SET
              nombre_empresa = ?,
              confirmacion_telefono = ?,
              confirmacion_correo = ?,
              correo_electronico = ?,
              estado_cuenta = ?,
              direccion = ?,
              ciudad_id = ?,
              descripcion_direccion = ?,
              tipo_documento_id = ?,
              numero_documento = ?
            WHERE id = ?
            """);

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setString(1, entity.getNombreEmpresa());
            sentenciaPreparada.setBoolean(2, entity.isConfirmacionTelefono());
            sentenciaPreparada.setBoolean(3, entity.isConfirmacionCorreo());
            sentenciaPreparada.setString(4, entity.getCorreoElectronico());
            sentenciaPreparada.setBoolean(5, entity.isEstadoCuenta());
            sentenciaPreparada.setString(6, entity.getDireccion());
            sentenciaPreparada.setObject(7, entity.getCiudad().getId());
            sentenciaPreparada.setString(8, entity.getDescripcionDireccion());
            sentenciaPreparada.setObject(9, entity.getTipoDocumento().getId());
            sentenciaPreparada.setInt(10, entity.getNumeroDocumento());
            sentenciaPreparada.setObject(11, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de modificar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un UPDATE en la tabla proveedor, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de modificar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un UPDATE en la tabla proveedor, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("DELETE FROM proveedor WHERE id = ?");

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de eliminar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un DELETE en la tabla proveedor, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de eliminar la informacion del nuevo proveedor...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un DELETE en la tabla proveedor, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}