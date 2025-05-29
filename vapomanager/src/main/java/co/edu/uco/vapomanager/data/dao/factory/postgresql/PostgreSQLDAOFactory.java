package co.edu.uco.vapomanager.data.dao.factory.postgresql;

import co.edu.uco.vapomanager.crosscutting.excepciones.DataVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.entity.administrador.AdministradorDAO;
import co.edu.uco.vapomanager.data.dao.entity.administrador.postgresql.AdministradorPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql.CiudadPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.proveedor.ProveedorDAO;
import co.edu.uco.vapomanager.data.dao.entity.proveedor.postgresql.ProveedorPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.tipodocumento.TipoDocumentoDAO;
import co.edu.uco.vapomanager.data.dao.entity.tipodocumento.postgresql.TipoDocumentoPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class PostgreSQLDAOFactory extends DAOFactory {

    private final DataSource dataSource;
    private Connection conexion;
    private boolean transaccionEstaIniciada;
    private boolean conexionEstaAbierta;

    public PostgreSQLDAOFactory(DataSource dataSource) throws VapomanagerException {
        this.dataSource = dataSource;
        this.transaccionEstaIniciada = false;
        this.conexionEstaAbierta = false;
        abrirConexion();
    }

    @Override
    protected void abrirConexion() throws VapomanagerException {
        var baseDatos = "vapomanager";
        var servidor = "vapomanager.uco.edu.co";
        try {
            conexion = dataSource.getConnection();
            conexionEstaAbierta = true;
        } catch (Exception exception) {
            if (exception instanceof java.sql.SQLException) {
                var mensajeUsuario = "se ha presentado un problema tratando de obtener la conexcion con la fuente de datos para llevar a cabo la accion deseada...";
                var mensajeTecnico = "se presento una excepcion de tipo SQLExeption tratando de obtener la conexion con la base de datos " + baseDatos + " en el servidor " + servidor + " para tener mas detalles, revise el log de errores... ";
                throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
            }
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de obtener la conexcion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Exeption tratando de obtener la conexion con la base de datos " + baseDatos + " en el servidor " + servidor + " para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void iniciarTransaccion() throws VapomanagerException {
        try {
            System.out.println("🚀 Iniciando transacción");
            asegurarConexionAbierta();
            conexion.setAutoCommit(false);
            transaccionEstaIniciada = true;
        } catch (VapomanagerException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema tratando de iniciar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de iniciar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void confirmarTransaccion() throws VapomanagerException {
        try {
            System.out.println("✅ Confirmando transacción");
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.commit();
        } catch (VapomanagerException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de confirmar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de confirmar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cancelarTransaccion() throws VapomanagerException {
        try {
            System.out.println("💥 Cancelando transacción");
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.rollback();
        } catch (VapomanagerException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de cancelar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de cancelar la transaccion sobre la conexion con la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cerrarConexion() throws VapomanagerException {
        try {
            System.out.println("🔒 Cerrando conexión");
            asegurarConexionAbierta();
            conexion.close();
        } catch (VapomanagerException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "se ha presentado un problema INESPERADO tratando de cerrar la conexion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se presento una excepcion NO CONTROLADA de tipo Eception tratando de cerrar la conexion sobre la base de datos para tener mas detalles, revise el log de errores... ";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private void asegurarTransaccionIniciada() throws VapomanagerException {
        if (!transaccionEstaIniciada) {
            var mensajeUsuario = "se ha presentado un problema tratando gestionar la transaccion con la fuente de datos para llevar a cabo la accion deseada...";
            var mensajeTecnico = "se intento gestionar(COMMIT/ROLLBACK) una transaccion que previamentre no fue iniciada.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        if (!conexionEstaAbierta) {
            var mensajeUsuario = "se ha presentado un problema tratando llevar a cabo la operacion deseada con una conexion cerrada...";
            var mensajeTecnico = "se intento llevar a cabo una operacion que requeria una conexion abierta, pero al momento de validarla estaa cerrada(COMMIT/ROLLBACK) una transaccion que previamentre no fue iniciada.";
            throw DataVapomanagerException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() throws VapomanagerException {
        asegurarConexionAbierta();
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public CiudadDAO getCiudadDAO() throws VapomanagerException {
        asegurarConexionAbierta();
        return new CiudadPostgreSQLDAO(conexion);
    }

    @Override
    public TipoDocumentoDAO getTipoDocumentoDAO() throws VapomanagerException {
        asegurarConexionAbierta();
        return new TipoDocumentoPostgreSQLDAO(conexion);
    }

    @Override
    public ProveedorDAO getProveedorDAO() throws VapomanagerException {
        asegurarConexionAbierta();
        return new ProveedorPostgreSQLDAO(conexion);
    }

    @Override
    public AdministradorDAO getAdministradorDAO() throws VapomanagerException {
        asegurarConexionAbierta();
        return new AdministradorPostgreSQLDAO(conexion);
    }
}
