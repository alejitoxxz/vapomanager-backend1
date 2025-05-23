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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private final Connection conexion;

    public CiudadPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var mensajeUsuario = "No hay conexión abierta para operar con ciudades.";
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
    public List<CiudadEntity> listByFilter(CiudadEntity filter) throws VapomanagerException {
        var listaCiudades = new ArrayList<CiudadEntity>();
        var senteciaSQL   = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE nombre ILIKE ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
                sentenciaPreparada.setString(1, "%" + filter.getNombre() + "%");
                try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                    while (cursorResultados.next()) {
                        var ciudad = new CiudadEntity();
                        ciudad.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                        ciudad.setNombre(cursorResultados.getString("nombre"));
                        var departamento = new DepartamentoEntity();
                        departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                        ciudad.setDepartamento(departamento);
                        listaCiudades.add(ciudad);
                    }
                }
            }
            return listaCiudades;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema consultando ciudades por filtro.";
            var mensajeTecnico = "SQLException en SELECT filtro: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar ciudades por filtro.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<CiudadEntity> listAll() throws VapomanagerException {
        var listaCiudades = new ArrayList<CiudadEntity>();
        var senteciaSQL   = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad");
        try {
            asegurarConexionAbierta();
            try (Statement sentencia = conexion.createStatement();
                 ResultSet cursorResultados = sentencia.executeQuery(senteciaSQL.toString())) {
                while (cursorResultados.next()) {
                    var ciudad = new CiudadEntity();
                    ciudad.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    ciudad.setNombre(cursorResultados.getString("nombre"));
                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                    ciudad.setDepartamento(departamento);
                    listaCiudades.add(ciudad);
                }
            }
            return listaCiudades;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema listando todas las ciudades.";
            var mensajeTecnico = "SQLException en SELECT ALL: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al listar todas las ciudades.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public CiudadEntity listById(UUID id) throws VapomanagerException {
        var ciudadEntityRetorno = new CiudadEntity();
        var senteciaSQL         = new StringBuilder();
        senteciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE id = ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(senteciaSQL.toString())) {
                sentenciaPreparada.setObject(1, id);
                try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
                    if (cursorResultados.next()) {
                        ciudadEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                        ciudadEntityRetorno.setNombre(cursorResultados.getString("nombre"));
                        var departamento = new DepartamentoEntity();
                        departamento.setId(UtilUUID.convertirAUUID(cursorResultados.getString("departamento_id")));
                        ciudadEntityRetorno.setDepartamento(departamento);
                    }
                }
            }
            return ciudadEntityRetorno;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema consultando ciudad por ID.";
            var mensajeTecnico = "SQLException en SELECT BY ID: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar ciudad por ID.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
