package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

public final class ProveedorFacadeImpl implements ProveedorFacade {

    
    private final DAOFactory daoFactory;
    private final ProveedorBusinessLogic proveedorBusinessLogic;

    public ProveedorFacadeImpl() throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL);
        proveedorBusinessLogic = new ProveedorBusinessLogicImpl(daoFactory);
    }

    
    @Override
    public void registrarNuevoProveedor(final ProveedorDTO proveedor) throws VapomanagerException {

        try {
            daoFactory.iniciarTransaccion();

            ProveedorDomain proveedorDomain =
                    ProveedorDTOAssambler.getInstance().toDomain(proveedor);

            proveedorBusinessLogic.registrarNuevoProveedor(proveedorDomain);

            daoFactory.confirmarTransaccion();

        } catch (VapomanagerException ex) {                       // Reglas de negocio, validaciones, etc.
            intentarCancelarTransaccionSilencioso();
            throw ex;                                             // Propaga intacto

        } catch (Exception ex) {                                  // Errores inesperados
            intentarCancelarTransaccionSilencioso();
            throw BusinessLogicVapomanagerException.reportar(
                    "Se produjo un problema INESPERADO tratando de registrar un nuevo proveedor.",
                    "Excepción NO CONTROLADA en registrarNuevoProveedor.", ex);

        } finally {
            intentarCerrarConexionSilencioso();
        }
    }

    
    @Override
    public void modificarProveedorExistente(final UUID id, final ProveedorDTO proveedor) throws VapomanagerException {

        try {
            daoFactory.iniciarTransaccion();

            ProveedorDomain proveedorDomain =
                    ProveedorDTOAssambler.getInstance().toDomain(proveedor);

            proveedorBusinessLogic.modificarProveedorExistente(id, proveedorDomain);

            daoFactory.confirmarTransaccion();

        } catch (VapomanagerException ex) {
            intentarCancelarTransaccionSilencioso();
            throw ex;

        } catch (Exception ex) {
            intentarCancelarTransaccionSilencioso();
            throw BusinessLogicVapomanagerException.reportar(
                    "Se produjo un problema INESPERADO tratando de modificar un proveedor.",
                    "Excepción NO CONTROLADA en modificarProveedorExistente.", ex);

        } finally {
            intentarCerrarConexionSilencioso();
        }
    }

    
    @Override
    public void darBajaDefinitivamenteProveedorExistente(final UUID id) throws VapomanagerException {

        try {
            daoFactory.iniciarTransaccion();

            proveedorBusinessLogic.darBajaDefinitivamenteProveedorExistente(id);

            daoFactory.confirmarTransaccion();

        } catch (VapomanagerException ex) {
            intentarCancelarTransaccionSilencioso();
            throw ex;

        } catch (Exception ex) {
            intentarCancelarTransaccionSilencioso();
            throw BusinessLogicVapomanagerException.reportar(
                    "Se produjo un problema INESPERADO tratando de eliminar un proveedor.",
                    "Excepción NO CONTROLADA en darBajaDefinitivamenteProveedorExistente.", ex);

        } finally {
            intentarCerrarConexionSilencioso();
        }
    }

    
    @Override
    public ProveedorDTO consultarProveedorPorId(final UUID id) throws VapomanagerException {

        try {
            ProveedorDomain resultado =
                    proveedorBusinessLogic.consultarProveedorPorId(id);

            return ProveedorDTOAssambler.getInstance().toDto(resultado);

        } catch (VapomanagerException ex) {
            throw ex;

        } catch (Exception ex) {
            throw BusinessLogicVapomanagerException.reportar(
                    "Se produjo un problema INESPERADO al consultar un proveedor.",
                    "Excepción NO CONTROLADA en consultarProveedorPorId.", ex);

        } finally {
            intentarCerrarConexionSilencioso();
        }
    }

    
    @Override
    public List<ProveedorDTO> consultarProveedores(final ProveedorDTO filtro) throws VapomanagerException {

        try {
            ProveedorDomain filtroDomain =
                    ProveedorDTOAssambler.getInstance().toDomain(filtro);

            List<ProveedorDomain> dominios =
                    proveedorBusinessLogic.consultarProveedores(filtroDomain);

            List<ProveedorDTO> dtos = new ArrayList<>();
            for (ProveedorDomain domain : dominios) {
                dtos.add(ProveedorDTOAssambler.getInstance().toDto(domain));
            }
            return dtos;

        } catch (VapomanagerException ex) {
            throw ex;

        } catch (Exception ex) {
            throw BusinessLogicVapomanagerException.reportar(
                    "Se produjo un problema INESPERADO al consultar proveedores.",
                    "Excepción NO CONTROLADA en consultarProveedores.", ex);

        } finally {
            intentarCerrarConexionSilencioso();
        }
    }

    private void intentarCancelarTransaccionSilencioso() {
        try {
            if (daoFactory.estaConexionAbierta()) {
                daoFactory.cancelarTransaccion();
            }
        } catch (Exception e) {
            
        }
    }

    private void intentarCerrarConexionSilencioso() {
        try {
            daoFactory.cerrarConexion();
        } catch (Exception e) {
        	
        }
    }
}
