package co.edu.uco.vapomanager.data.dao.entity.administrador.postgresql;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.entity.administrador.AdministradorDAO;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdministradorPostgreSQLDAO implements AdministradorDAO {

    private Connection conexion;

    public AdministradorPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(AdministradorEntity entity) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("INSERT INTO administrador(id, correo) VALUES (?, ?)");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getCorreo());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de registrar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un INSERT en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de registrar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un INSERT en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<AdministradorEntity> listByFilter(AdministradorEntity filter) throws VapomanagerException {
        var listaResultados = new ArrayList<AdministradorEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador ORDER BY correo ASC");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var admin = new AdministradorEntity();
                    admin.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    admin.setCorreo(cursorResultados.getString("correo"));
                    listaResultados.add(admin);
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los administradores...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla administrador para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla administrador, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public List<AdministradorEntity> listAll() throws VapomanagerException {
        var listaResultados = new ArrayList<AdministradorEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador ORDER BY correo ASC");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    var admin = new AdministradorEntity();
                    admin.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    admin.setCorreo(cursorResultados.getString("correo"));
                    listaResultados.add(admin);
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar la informacion de toddos los administradores...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla administrador para consultar todos los registros...";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla administrador, para consultar todos los registros... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public AdministradorEntity listById(UUID id) throws VapomanagerException {
        var administradorRetorno = new AdministradorEntity();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    administradorRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    administradorRetorno.setCorreo(cursorResultados.getString("correo"));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el administrador con el identificador deseado la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla administrador por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return administradorRetorno;
    }

    @Override
    public void update(UUID id, AdministradorEntity entity) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("UPDATE administrador SET correo = ? WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setString(1, entity.getCorreo());
            sentenciaPreparada.setObject(2, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de modificar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un UPDATE en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de modificar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un UPDATE en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws VapomanagerException {
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("DELETE FROM administrador WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de eliminar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un DELETE en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de eliminar la informacion del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un DELETE en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
