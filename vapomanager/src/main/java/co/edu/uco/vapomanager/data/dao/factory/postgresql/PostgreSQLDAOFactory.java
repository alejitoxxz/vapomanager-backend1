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
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLDAOFactory extends DAOFactory {

    private static final DataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/vapomanager");
        config.setUsername("postgres");
        config.setPassword("123456");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(600_000);
        config.setMaxLifetime(1_800_000);
        config.setConnectionTimeout(30_000);
        dataSource = new HikariDataSource(config);
    }

    private Connection conexion;
    private boolean transaccionEstaIniciada;

    public PostgreSQLDAOFactory() throws VapomanagerException {
        abrirConexion();
        this.transaccionEstaIniciada = false;
    }

    @Override
    protected void abrirConexion() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = dataSource.getConnection();
                conexion.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema obteniendo la conexión con la base de datos.",
                "SQLException al abrir la conexión a vapomanager en localhost.", e);
        } catch (Exception e) {
            throw DataVapomanagerException.reportar(
                "Se presentó un problema INESPERADO obteniendo la conexión con la base de datos.",
                "Excepción NO CONTROLADA al abrir la conexión a vapomanager en localhost.", e);
        }
    }

    private void asegurarConexionAbierta() throws VapomanagerException {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = dataSource.getConnection();
                conexion.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "No fue posible abrir la conexión con la base de datos.",
                "SQLException en asegurarConexionAbierta()", e);
        }
    }

    @Override
    public void iniciarTransaccion() throws VapomanagerException {
        try {
            asegurarConexionAbierta();
            conexion.setAutoCommit(false);
            transaccionEstaIniciada = true;
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Problema al iniciar la transacción.",
                "SQLException al hacer setAutoCommit(false).", e);
        }
    }

    @Override
    public void confirmarTransaccion() throws VapomanagerException {
        try {
            asegurarConexionAbierta();
            conexion.commit();
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Problema al confirmar la transacción.",
                "SQLException al hacer commit().", e);
        } finally {
            transaccionEstaIniciada = false;
            cerrarConexion();
        }
    }

    @Override
    public void cancelarTransaccion() throws VapomanagerException {
        try {
            if (transaccionEstaIniciada) {
                asegurarConexionAbierta();
                conexion.rollback();
            }
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Problema al cancelar la transacción.",
                "SQLException al hacer rollback().", e);
        } finally {
            transaccionEstaIniciada = false;
            cerrarConexion();
        }
    }

    @Override
    public void cerrarConexion() throws VapomanagerException {
        try {
            if (estaConexionAbierta()) {
                if (!conexion.getAutoCommit()) {
                    conexion.setAutoCommit(true);
                }
                conexion.close();
            }
        } catch (SQLException e) {
            throw DataVapomanagerException.reportar(
                "Problema al cerrar la conexión.",
                "SQLException al cerrar la conexión.", e);
        }
    }

    @Override
    public boolean estaConexionAbierta() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
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
