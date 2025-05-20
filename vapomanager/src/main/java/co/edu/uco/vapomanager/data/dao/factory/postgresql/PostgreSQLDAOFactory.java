package co.edu.uco.vapomanager.data.dao.factory.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql.CiudadPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class PostgreSQLDAOFactory extends DAOFactory {

    private final DataSource dataSource;
    private Connection conexion;
    private boolean transaccionEstaIniciada;
    private boolean conexionEstaAbierta;

    public PostgreSQLDAOFactory(DataSource dataSource) throws OnlineTestException {
        this.dataSource = dataSource;
        this.transaccionEstaIniciada = false;
        this.conexionEstaAbierta = false;
        abrirConexion();
    }

    @Override
    protected void abrirConexion() throws OnlineTestException {
        var baseDatos = "vapomanager";
        var servidor = "vapomanager.uco.edu.co";
        try {
            conexion = dataSource.getConnection();
            conexionEstaAbierta = true;
        } catch (Exception exception) {
            if (exception instanceof java.sql.SQLException) {
                var mensajeUsuario = "se ha presentado un problema tratando de obtener la conexcion con la fuente de datos para llevar a cabo la accion deseada...";
                var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de obtener la conexion con la base de datos " + baseDatos + " en el servidor " + servidor + " para tener mas detalles, revise el log de errores... ";
                throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
            }
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de obtener la conexcion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Exeption tratando de obtener la conexion con la base de datos " + baseDatos + " en el servidor " + servidor + " para tener mas detalles, revise el log de errores... ";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void iniciarTransaccion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            conexion.setAutoCommit(false);
            transaccionEstaIniciada = true;
        } catch (OnlineTestException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de iniciar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de iniciar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void confirmarTransaccion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.commit();
        } catch (OnlineTestException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de confirmar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de confirmar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cancelarTransaccion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.rollback();
        } catch (OnlineTestException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de cancelar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de cancelar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cerrarConexion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            conexion.close();
        } catch (OnlineTestException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de cerrar la conexion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de cerrar la conexion sobre la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private void asegurarTransaccionIniciada() throws OnlineTestException {
        if (!transaccionEstaIniciada) {
            var mensajeUsuario = "se ha presentado un problema tratando gestionar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se intento gestionar(COMMIT/ROLLBACK) una transaccion que previamentre no fue iniciada.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    private void asegurarConexionAbierta() throws OnlineTestException {
        if (!conexionEstaAbierta) {
            var mensajeUsuario = "se ha presentado un problema tratando llevar a cabo la operacion deseada con una conexion cerrada...";
            var mensajeTecnico = "se intento llevar a cabo una operacion que requeria una conexion abierta, pero al momento de validarla estaa cerrada(COMMIT/ROLLBACK) una transaccion que previamentre no fue iniciada.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() throws OnlineTestException {
        asegurarConexionAbierta();
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public CiudadDAO getCiudadDAO() throws OnlineTestException {
        asegurarConexionAbierta();
        return new CiudadPostgreSQLDAO(conexion);
    }
}
