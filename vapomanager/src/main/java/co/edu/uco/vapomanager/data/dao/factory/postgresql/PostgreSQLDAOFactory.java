package co.edu.uco.vapomanager.data.dao.factory.postgresql;

import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql.CiudadPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

import java.sql.Connection;

public class PostgreSQLDAOFactory extends DAOFactory {

    private Connection conexion;

    public PostgreSQLDAOFactory() {
        abrirConexion();
    }

    @Override
    protected void abrirConexion() {
        // Aquí deberías abrir la conexión real a PostgreSQL usando JDBC.
        // Ejemplo:
        // conexion = DriverManager.getConnection(url, user, password);
        conexion = null;
    }

    @Override
    public void iniciarTransaccion() {
        // Implementar inicio de transacción
    }

    @Override
    public void confirmarTransaccion() {
        // Implementar confirmación de transacción
    }

    @Override
    public void cancelarTransaccion() {
        // Implementar cancelación de transacción
    }

    @Override
    public void cerrarConexion() {
        // Implementar cierre de conexión si es necesario
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public CiudadDAO getCiudadDAO() {
        return new CiudadPostgreSQLDAO(conexion);
    }
}
