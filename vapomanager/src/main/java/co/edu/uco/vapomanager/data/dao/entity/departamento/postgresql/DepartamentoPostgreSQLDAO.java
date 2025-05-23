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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private final Connection conexion;

    public DepartamentoPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                var mensajeUsuario = "No hay conexión abierta para operar con departamentos.";
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
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity filter) throws VapomanagerException {
        var listaDepartamentos = new ArrayList<DepartamentoEntity>();
        var sentenciaSQL       = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre FROM departamento WHERE nombre ILIKE ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement preparador = conexion.prepareStatement(sentenciaSQL.toString())) {
                preparador.setString(1, "%" + filter.getNombre() + "%");
                try (ResultSet resultados = preparador.executeQuery()) {
                    while (resultados.next()) {
                        var departamento = new DepartamentoEntity();
                        departamento.setId(UtilUUID.convertirAUUID(resultados.getString("id")));
                        departamento.setNombre(resultados.getString("nombre"));
                        listaDepartamentos.add(departamento);
                    }
                }
            }
            return listaDepartamentos;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema consultando departamentos por filtro.";
            var mensajeTecnico = "SQLException en SELECT filtro: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar departamentos por filtro.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<DepartamentoEntity> listAll() throws VapomanagerException {
        var listaDepartamentos = new ArrayList<DepartamentoEntity>();
        var sentenciaSQL       = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre FROM departamento");
        try {
            asegurarConexionAbierta();
            try (Statement ejecutor = conexion.createStatement();
                 ResultSet resultados = ejecutor.executeQuery(sentenciaSQL.toString())) {
                while (resultados.next()) {
                    var departamento = new DepartamentoEntity();
                    departamento.setId(UtilUUID.convertirAUUID(resultados.getString("id")));
                    departamento.setNombre(resultados.getString("nombre"));
                    listaDepartamentos.add(departamento);
                }
            }
            return listaDepartamentos;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema listando todos los departamentos.";
            var mensajeTecnico = "SQLException en SELECT ALL: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al listar todos los departamentos.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public DepartamentoEntity listById(UUID id) throws VapomanagerException {
        var departamentoEntityRetorno = new DepartamentoEntity();
        var sentenciaSQL             = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre FROM departamento WHERE id = ?");
        try {
            asegurarConexionAbierta();
            try (PreparedStatement preparador = conexion.prepareStatement(sentenciaSQL.toString())) {
                preparador.setObject(1, id);
                try (ResultSet resultados = preparador.executeQuery()) {
                    if (resultados.next()) {
                        departamentoEntityRetorno.setId(UtilUUID.convertirAUUID(resultados.getString("id")));
                        departamentoEntityRetorno.setNombre(resultados.getString("nombre"));
                    }
                }
            }
            return departamentoEntityRetorno;
        } catch (SQLException exception) {
            var mensajeUsuario = "Problema consultando departamento por ID.";
            var mensajeTecnico = "SQLException en SELECT BY ID: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Error inesperado al consultar departamento por ID.";
            var mensajeTecnico = "Excepción inesperada: " + exception.getMessage();
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
