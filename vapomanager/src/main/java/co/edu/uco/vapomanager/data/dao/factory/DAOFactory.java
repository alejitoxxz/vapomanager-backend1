package co.edu.uco.vapomanager.data.dao.factory;

import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.factory.postgresql.PostgreSQLDAOFactory;

import javax.sql.DataSource;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory, DataSource dataSource) throws OnlineTestException {
        switch (factory) {
            case POSTGRESQL:
                return new PostgreSQLDAOFactory(dataSource);
            default:
                throw new IllegalArgumentException("No se ha implementado la fábrica para " + factory);
        }
    }

    protected abstract void abrirConexion() throws OnlineTestException;
    public abstract void iniciarTransaccion() throws OnlineTestException;
    public abstract void confirmarTransaccion() throws OnlineTestException;
    public abstract void cancelarTransaccion() throws OnlineTestException;
    public abstract void cerrarConexion() throws OnlineTestException;

    public abstract DepartamentoDAO getDepartamentoDAO() throws OnlineTestException;
    public abstract CiudadDAO getCiudadDAO() throws OnlineTestException;
}
