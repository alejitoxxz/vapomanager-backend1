package co.edu.uco.vapomanager.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.ProveedorBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.proveedor.entity.ProveedorEntityAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.entity.ProveedorEntity;

public final class ProveedorBusinessLogicImpl implements ProveedorBusinessLogic {

    private final DAOFactory factory;

    public ProveedorBusinessLogicImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoProveedor(final ProveedorDomain proveedor) throws VapomanagerException {

        validarIntegridadInformacionRegistrarProveedor(proveedor);
        validarNoExistaProveedorConMismoID(proveedor.getId());
        validarNoExistaProveedorConMismoCorreo(proveedor.getCorreoElectronico());
        validarNoExistaProveedorConMismoTelefono(proveedor.getNumeroTelefono());
        validarNoExistaProveedorConMismoDocumento(proveedor.getNumeroDocumento());

        UUID nuevoId = generarIdentificadorNuevoProveedor();

        ProveedorDomain domainACrear = new ProveedorDomain(
                nuevoId,
                proveedor.getNombreEmpresa(),
                proveedor.isConfirmacionTelefono(),
                proveedor.isConfirmacionCorreo(),
                proveedor.getCorreoElectronico(),
                proveedor.isEstadoCuenta(),
                proveedor.getNumeroTelefono(),
                proveedor.getDireccion(),
                proveedor.getCiudad(),
                proveedor.getDescripcionDireccion(),
                proveedor.getTipoDocumento(),
                proveedor.getNumeroDocumento()
        );

        ProveedorEntity entity = ProveedorEntityAssambler.getInstance().toEntity(domainACrear);
        factory.getProveedorDAO().create(entity);
    }

    public void validarIntegridadInformacionRegistrarProveedor(final ProveedorDomain p) throws VapomanagerException {

        
        if (UtilTexto.getInstance().estaVacia(p.getNombreEmpresa())) {
            throw BusinessLogicVapomanagerException.reportar("El nombre de la empresa es obligatorio");
        }
        if (p.getNombreEmpresa().length() > 100) {
            throw BusinessLogicVapomanagerException.reportar("El nombre de la empresa supera los 100 caracteres");
        }

        
        if (UtilTexto.getInstance().estaVacia(p.getCorreoElectronico())) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico es obligatorio");
        }
        if (!UtilTexto.getInstance().patronValido(p.getCorreoElectronico(), ".+@.+\\..+")) {
            throw BusinessLogicVapomanagerException.reportar("Formato de correo electrónico inválido");
        }
        if (p.getCorreoElectronico().length() > 100) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico supera los 100 caracteres");
        }

        
        if (!UtilNumero.esPositivo(p.getNumeroTelefono())) {
            throw BusinessLogicVapomanagerException.reportar("El número de teléfono es obligatorio y debe ser un numero positivo");
        }
        if (!String.valueOf(p.getNumeroTelefono()).matches("\\d+")) {
            throw BusinessLogicVapomanagerException.reportar("El número de teléfono solo puede contener dígitos");
        }

        
        if (!UtilNumero.esPositivo(p.getNumeroDocumento())) {
            throw BusinessLogicVapomanagerException.reportar("El número de documento es obligatorio y debe un numero positivo");
        }
        if (!String.valueOf(p.getNumeroDocumento()).matches("\\d+")) {
            throw BusinessLogicVapomanagerException.reportar("El número de documento solo puede contener dígitos");
        }
        int lenDoc = String.valueOf(p.getNumeroDocumento()).length();
        if (lenDoc < 6 || lenDoc > 12) {
            throw BusinessLogicVapomanagerException.reportar("El número de documento debe tener entre 6 y 12 dígitos");
        }
    }

    
    private void validarNoExistaProveedorConMismoID(final UUID id) throws VapomanagerException {
        if (!UtilUUID.esValorDefecto(id)) {
            ProveedorEntity existente = factory.getProveedorDAO().listById(id);
            if (existente != null) {
                throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con el mismo ID");
            }
        }
    }

    private void validarNoExistaProveedorConMismoCorreo(final String correo) throws VapomanagerException {
        ProveedorEntity filtro = new ProveedorEntity();
        filtro.setCorreoElectronico(correo);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese correo electrónico");
        }
    }

    private void validarNoExistaProveedorConMismoTelefono(final long telefono) throws VapomanagerException {
        ProveedorEntity filtro = new ProveedorEntity();
        filtro.setNumeroTelefono(telefono);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese número de teléfono");
        }
    }

    private void validarNoExistaProveedorConMismoDocumento(final long documento) throws VapomanagerException {
        ProveedorEntity filtro = new ProveedorEntity();
        filtro.setNumeroDocumento(documento);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese número de documento");
        }
    }

    
    private UUID generarIdentificadorNuevoProveedor() throws VapomanagerException {
        UUID nuevoId;
        ProveedorEntity existente;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            existente = factory.getProveedorDAO().listById(nuevoId);
        } while (existente != null);
        return nuevoId;
    }

    
    @Override
    public void modificarProveedorExistente(final UUID id, final ProveedorDomain p) throws VapomanagerException {
        ProveedorEntity entity = ProveedorEntityAssambler.getInstance().toEntity(p);
        factory.getProveedorDAO().update(id, entity);
    }

    @Override
    public void darBajaDefinitivamenteProveedorExistente(final UUID id) throws VapomanagerException {
        factory.getProveedorDAO().delete(id);
    }

    @Override
    public ProveedorDomain consultarProveedorPorId(final UUID id) throws VapomanagerException {
        ProveedorEntity entity = factory.getProveedorDAO().listById(id);
        return ProveedorEntityAssambler.getInstance().toDomain(entity);
    }

    @Override
    public List<ProveedorDomain> consultarProveedores(final ProveedorDomain filtro) throws VapomanagerException {
        ProveedorEntity filtroEntity = ProveedorEntityAssambler.getInstance().toEntity(filtro);
        List<ProveedorEntity> entities = factory.getProveedorDAO().listByFilter(filtroEntity);
        return ProveedorEntityAssambler.getInstance().toDomain(entities);
    }
}
