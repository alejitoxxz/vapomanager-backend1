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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdministradorPostgreSQLDAO implements AdministradorDAO {

    private Connection conexion;

    public AdministradorPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var mensajeUsuario = "No hay conexión abierta para operar con administradores.";
                var mensajeTecnico = "La conexión JDBC es null o está cerrada.";
                throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "Error verificando el estado de la conexión a la base de datos.";
            var mensajeTecnico = "SQLException al comprobar estado de la conexión: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
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
            var mensajeUsuario = "se ha presentado un problema tratando de registrar la información del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un INSERT en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de registrar la información del nuevo administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un INSERT en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<AdministradorEntity> listByFilter(AdministradorEntity filter) throws VapomanagerException {
        var resultados  = new ArrayList<AdministradorEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador WHERE correo ILIKE ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
                sentenciaPreparada.setString(1, "%" + filter.getCorreo() + "%");
                try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                    while (cursorResultados.next()) {
                        var admin = new AdministradorEntity();
                        admin.setId((UUID) cursorResultados.getObject("id"));
                        admin.setCorreo(cursorResultados.getString("correo"));
                        resultados.add(admin);
                    }
                }
            }
            return resultados;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema consultando administradores por filtro.";
            var mensajeTecnico = "SQLException en SELECT filtro: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar administradores por filtro.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<AdministradorEntity> listAll() throws VapomanagerException {
        var resultados  = new ArrayList<AdministradorEntity>();
        var senteciaSQL = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador");
        try {
            asegurarConexionAbierta();
            try (Statement sentencia = conexion.createStatement();
                 ResultSet cursorResultados = sentencia.executeQuery(senteciaSQL.toString())) {
                while (cursorResultados.next()) {
                    var admin = new AdministradorEntity();
                    admin.setId((UUID) cursorResultados.getObject("id"));
                    admin.setCorreo(cursorResultados.getString("correo"));
                    resultados.add(admin);
                }
            }
            return resultados;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema listando todos los administradores.";
            var mensajeTecnico = "SQLException en SELECT ALL: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al listar todos los administradores.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public AdministradorEntity listById(UUID id) throws VapomanagerException {
        var administradorEntityRetorno = new AdministradorEntity();
        var senteciaSQL               = new StringBuilder();
        senteciaSQL.append("SELECT id, correo FROM administrador WHERE id = ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
                sentenciaPreparada.setObject(1, id);
                try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                    if (cursorResultados.next()) {
                        administradorEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                        administradorEntityRetorno.setCorreo(cursorResultados.getString("correo"));
                    }
                }
            }
            return administradorEntityRetorno;
        } catch (SQLException exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de consultar el administrador con el identificador deseado...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un SELECT en la tabla administrador por id, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de consultar la información del administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un SELECT en la tabla administrador, para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
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
            var mensajeUsuario = "se ha presentado un problema tratando de modificar la información del administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un UPDATE en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de modificar la información del administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un UPDATE en la tabla administrador, para tener mas detalles revise el log de errores... ";
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
            var mensajeUsuario = "se ha presentado un problema tratando de eliminar la información del administrador...";
            var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de hacer un DELETE en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de eliminar la información del administrador...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de hacer un DELETE en la tabla administrador, para tener mas detalles revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
