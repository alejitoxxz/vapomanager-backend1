package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import co.edu.uco.vapomanager.businesslogic.businesslogic.ProveedorBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.proveedor.dto.ProveedorDTOAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.impl.ProveedorBusinessLogicImpl;
import co.edu.uco.vapomanager.businesslogic.fecade.ProveedorFacade;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.data.dao.factory.Factory;
import co.edu.uco.vapomanager.dto.ProveedorDTO;

public class ProveedorFacadeImpl implements ProveedorFacade {

    private final DAOFactory daoFactory;
    private final ProveedorBusinessLogic proveedorBusinessLogic;

    public ProveedorFacadeImpl(final DataSource dataSource) throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL, dataSource);
        proveedorBusinessLogic = new ProveedorBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoProveedor(final ProveedorDTO proveedor) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            ProveedorDomain proveedorDomain = ProveedorDTOAssambler.getInstance().toDomain(proveedor);
            proveedorBusinessLogic.registrarNuevoProveedor(proveedorDomain);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de REGISTRAR la información de un nuevo proveedor. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema INESPERADO tratando de registrar la información de un nuevo proveedor.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarProveedorExistente(final UUID id, final ProveedorDTO proveedor) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            ProveedorDomain proveedorDomain = ProveedorDTOAssambler.getInstance().toDomain(proveedor);
            proveedorBusinessLogic.modificarProveedorExistente(id, proveedorDomain);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de MODIFICAR la información de un proveedor existente. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de modificar la información de un proveedor.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteProveedorExistente(final UUID id) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            proveedorBusinessLogic.darBajaDefinitivamenteProveedorExistente(id);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de DAR DE BAJA DEFINITIVAMENTE un proveedor existente. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de eliminar la información de un proveedor.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public ProveedorDTO consultarProveedorPorId(final UUID id) throws VapomanagerException {
        try {
            ProveedorDomain proveedorDomainResultado = proveedorBusinessLogic.consultarProveedorPorId(id);
            ProveedorDTO proveedorDTOResultado = ProveedorDTOAssambler.getInstance().toDto(proveedorDomainResultado);
            return proveedorDTOResultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la información de un proveedor por ID.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar el proveedor.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<ProveedorDTO> consultarProveedores(final ProveedorDTO filtro) throws VapomanagerException {
        try {
            ProveedorDomain proveedorDomainFiltro = ProveedorDTOAssambler.getInstance().toDomain(filtro);
            List<ProveedorDomain> proveedorDomainResultado = proveedorBusinessLogic.consultarProveedores(proveedorDomainFiltro);

            List<ProveedorDTO> proveedorDTOResultado = new ArrayList<>();
            for (ProveedorDomain proveedorDomain : proveedorDomainResultado) {
                proveedorDTOResultado.add(ProveedorDTOAssambler.getInstance().toDto(proveedorDomain));
            }
            return proveedorDTOResultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción inesperada tratando de consultar proveedores con filtro.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de consultar proveedores.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
