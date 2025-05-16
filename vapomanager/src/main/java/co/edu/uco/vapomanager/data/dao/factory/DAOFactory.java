package co.edu.uco.vapomanager.data.dao.factory;

import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.data.dao.factory.postgresql.PostgreSQLDAOFactory;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory) {
        switch (factory) {
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            default:
                throw new IllegalArgumentException("No se ha implementado la fábrica para " + factory);
        }
    }

    protected abstract void abrirConexion();
    public abstract void iniciarTransaccion();
    public abstract void confirmarTransaccion();
    public abstract void cancelarTransaccion();
    public abstract void cerrarConexion();

    
    public abstract DepartamentoDAO getDepartamentoDAO();
    public abstract CiudadDAO getCiudadDAO();
}
