package co.edu.uco.vapomanager.data.dao.factory;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.factory.postgresql.PostgreSQLDAOFactory;

import javax.sql.DataSource;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory, DataSource dataSource) throws VapomanagerException {
        switch (factory) {
            case POSTGRESQL:
                return new PostgreSQLDAOFactory(dataSource);
            default:
                throw new IllegalArgumentException("No se ha implementado la fábrica para " + factory);
        }
    }

    protected abstract void abrirConexion() throws VapomanagerException;
    public abstract void iniciarTransaccion() throws VapomanagerException;
    public abstract void confirmarTransaccion() throws VapomanagerException;
    public abstract void cancelarTransaccion() throws VapomanagerException;
    public abstract void cerrarConexion() throws VapomanagerException;

    public abstract DepartamentoDAO getDepartamentoDAO() throws VapomanagerException;
    public abstract CiudadDAO getCiudadDAO() throws VapomanagerException;
}
