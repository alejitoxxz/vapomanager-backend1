package co.edu.uco.vapomanager.data.dao.entity.administrador.postgresql;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.entity.administrador.AdministradorDAO;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdministradorPostgreSQLDAO implements AdministradorDAO {

    private final Connection conexion;

    public AdministradorPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(AdministradorEntity entity) throws VapomanagerException {
        var sentenciaSQL = new StringBuilder("INSERT INTO administrador(id, correo) VALUES (?, ?)");

        try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getCorreo());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información del nuevo administrador...";
            var mensajeTecnico = "Excepción SQL al hacer INSERT en la tabla administrador.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<AdministradorEntity> listByFilter(AdministradorEntity filter) throws VapomanagerException {
        var listaResultados = new ArrayList<AdministradorEntity>();
        var sentenciaSQL = new StringBuilder("SELECT id, correo FROM administrador ORDER BY correo ASC");

        try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());
             var cursorResultados = sentenciaPreparada.executeQuery()) {

            while (cursorResultados.next()) {
                var admin = new AdministradorEntity();
                admin.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                admin.setCorreo(cursorResultados.getString("correo"));
                listaResultados.add(admin);
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de los administradores...";
            var mensajeTecnico = "Excepción SQL al hacer SELECT en la tabla administrador.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }

    @Override
    public List<AdministradorEntity> listAll() throws VapomanagerException {
        return listByFilter(new AdministradorEntity());
    }

    @Override
    public AdministradorEntity listById(UUID id) throws VapomanagerException {
        var administradorRetorno = new AdministradorEntity();
        var sentenciaSQL = new StringBuilder("SELECT id, correo FROM administrador WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                if (cursorResultados.next()) {
                    administradorRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    administradorRetorno.setCorreo(cursorResultados.getString("correo"));
                }
            }
        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema consultando el administrador con el ID deseado...";
            var mensajeTecnico = "Excepción SQL al hacer SELECT por ID en la tabla administrador.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return administradorRetorno;
    }

    @Override
    public void update(UUID id, AdministradorEntity entity) throws VapomanagerException {
        var sentenciaSQL = new StringBuilder("UPDATE administrador SET correo = ? WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
            sentenciaPreparada.setString(1, entity.getCorreo());
            sentenciaPreparada.setObject(2, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema modificando la información del administrador...";
            var mensajeTecnico = "Excepción SQL al hacer UPDATE en la tabla administrador.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws VapomanagerException {
        var sentenciaSQL = new StringBuilder("DELETE FROM administrador WHERE id = ?");

        try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
            sentenciaPreparada.setObject(1, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema eliminando la información del administrador...";
            var mensajeTecnico = "Excepción SQL al hacer DELETE en la tabla administrador.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
