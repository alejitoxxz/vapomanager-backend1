package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import co.edu.uco.vapomanager.businesslogic.businesslogic.AdministradorBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.administrador.dto.AdministradorDTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.AdministradorDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.impl.AdministradorBusinessLogicImpl;
import co.edu.uco.vapomanager.businesslogic.fecade.AdministradorFacade;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.data.dao.factory.Factory;
import co.edu.uco.vapomanager.dto.AdministradorDTO;

public class AdministradorFacadeImpl implements AdministradorFacade {

    private final DAOFactory daoFactory;
    private final AdministradorBusinessLogic administradorBusinessLogic;

    public AdministradorFacadeImpl(final DataSource dataSource) throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL, dataSource);
        administradorBusinessLogic = new AdministradorBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoAdministrador(final AdministradorDTO administrador) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            AdministradorDomain administradorDomain = AdministradorDTOAssembler.getInstance().toDomain(administrador);
            administradorBusinessLogic.registrarNuevoAdministrador(administradorDomain);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de REGISTRAR la información de un nuevo administrador. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema INESPERADO tratando de registrar la información de un nuevo administrador.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarAdministradorExistente(final UUID id, final AdministradorDTO administrador) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            AdministradorDomain administradorDomain = AdministradorDTOAssembler.getInstance().toDomain(administrador);
            administradorBusinessLogic.modificarAdministradorExistente(id, administradorDomain);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de MODIFICAR la información de un administrador existente. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de modificar la información de un administrador.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteAdministradorExistente(final UUID id) throws VapomanagerException {
        try {
            daoFactory.iniciarTransaccion();

            administradorBusinessLogic.darBajaDefinitivamenteAdministradorExistente(id);

            daoFactory.confirmarTransaccion();
        } catch (VapomanagerException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de DAR DE BAJA DEFINITIVAMENTE un administrador existente. Para más detalles revise el log de errores.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de eliminar la información de un administrador.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public AdministradorDTO consultarAdministradorPorId(final UUID id) throws VapomanagerException {
        try {
            AdministradorDomain administradorDomain = administradorBusinessLogic.consultarAdministradorPorId(id);
            AdministradorDTO administradorDTO = AdministradorDTOAssembler.getInstance().toDto(administradorDomain);
            return administradorDTO;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la información de un administrador por ID.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar el administrador.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<AdministradorDTO> consultarAdministradores(final AdministradorDTO filtro) throws VapomanagerException {
        try {
            AdministradorDomain administradorDomainFiltro = AdministradorDTOAssembler.getInstance().toDomain(filtro);
            List<AdministradorDomain> administradorDomainResultado = administradorBusinessLogic.consultarAdministradores(administradorDomainFiltro);

            List<AdministradorDTO> administradorDTOResultado = new ArrayList<>();
            for (AdministradorDomain administradorDomain : administradorDomainResultado) {
                administradorDTOResultado.add(AdministradorDTOAssembler.getInstance().toDto(administradorDomain));
            }
            return administradorDTOResultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción inesperada tratando de consultar administradores con filtro.";
            String mensajeUsuario = "Se ha producido un problema inesperado tratando de consultar administradores.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
