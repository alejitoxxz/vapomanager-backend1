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
        // 1. Validar integridad de datos (tipo, longitud, obligatoriedad, formato, rango)
        validarIntegridadInformacionRegistrarProveedor(proveedor);

        // 2. Validar unicidad
        validarNoExistaProveedorConMismoID(proveedor.getId());
        validarNoExistaProveedorConMismoCorreo(proveedor.getCorreoElectronico());
        validarNoExistaProveedorConMismoTelefono(proveedor.getNumeroTelefono());
        validarNoExistaProveedorConMismoDocumento(proveedor.getNumeroDocumento());

        // 3. Generar nuevo identificador
        var nuevoId = generarIdentificadorNuevoProveedor();

        // 4. Reconstruir el domain con el ID generado
        var domainACrear = new ProveedorDomain(
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

        // 5. Crear en la capa de persistencia
        var entity = ProveedorEntityAssambler.getInstance().toEntity(domainACrear);
        factory.getProveedorDAO().create(entity);
    }

    private void validarIntegridadInformacionRegistrarProveedor(final ProveedorDomain p) throws VapomanagerException {
        // nombreEmpresa obligatorio y ≤ 100 caracteres
        if (UtilTexto.getInstance().estaVacia(p.getNombreEmpresa())) {
            throw BusinessLogicVapomanagerException.reportar("El nombre de la empresa es obligatorio");
        }
        if (p.getNombreEmpresa().length() > 100) {
            throw BusinessLogicVapomanagerException.reportar("El nombre de la empresa supera los 100 caracteres");
        }

        // correo electrónico obligatorio, formato y ≤ 100 caracteres
        if (UtilTexto.getInstance().estaVacia(p.getCorreoElectronico())) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico es obligatorio");
        }
        if (!UtilTexto.getInstance().patronValido(p.getCorreoElectronico(), ".+@.+\\..+")) {
            throw BusinessLogicVapomanagerException.reportar("Formato de correo electrónico inválido");
        }
        if (p.getCorreoElectronico().length() > 100) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico supera los 100 caracteres");
        }

        // número de teléfono obligatorio y positivo
        if (!UtilNumero.esPositivo(p.getNumeroTelefono())) {
            throw BusinessLogicVapomanagerException.reportar("El número de teléfono es obligatorio y debe ser positivo");
        }

        // número de documento obligatorio, positivo y entre 6 y 12 dígitos
        if (!UtilNumero.esPositivo(p.getNumeroDocumento())) {
            throw BusinessLogicVapomanagerException.reportar("El número de documento es obligatorio y debe ser positivo");
        }
        var lenDoc = String.valueOf(p.getNumeroDocumento()).length();
        if (lenDoc < 6 || lenDoc > 12) {
            throw BusinessLogicVapomanagerException.reportar("El número de documento debe tener entre 6 y 12 dígitos");
        }
    }

    private void validarNoExistaProveedorConMismoID(final UUID id) throws VapomanagerException {
        if (!UtilUUID.esValorDefecto(id)) {
            var existente = factory.getProveedorDAO().listById(id);
            if (!UtilUUID.esValorDefecto(existente.getId())) {
                throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con el mismo ID");
            }
        }
    }

    private void validarNoExistaProveedorConMismoCorreo(final String correo) throws VapomanagerException {
        var filtro = new ProveedorEntity();
        filtro.setCorreoElectronico(correo);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese correo electrónico");
        }
    }

    private void validarNoExistaProveedorConMismoTelefono(final int telefono) throws VapomanagerException {
        var filtro = new ProveedorEntity();
        filtro.setNumeroTelefono(telefono);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese número de teléfono");
        }
    }

    private void validarNoExistaProveedorConMismoDocumento(final int documento) throws VapomanagerException {
        var filtro = new ProveedorEntity();
        filtro.setNumeroDocumento(documento);
        if (!factory.getProveedorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un proveedor con ese número de documento");
        }
    }

    private UUID generarIdentificadorNuevoProveedor() throws VapomanagerException {
        UUID nuevoId;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
        } while (!UtilUUID.esValorDefecto(factory.getProveedorDAO().listById(nuevoId).getId()));
        return nuevoId;
    }

    @Override
    public void modificarProveedorExistente(final UUID id, final ProveedorDomain p) throws VapomanagerException {
        var entity = ProveedorEntityAssambler.getInstance().toEntity(p);
        factory.getProveedorDAO().update(id, entity);
    }

    @Override
    public void darBajaDefinitivamenteProveedorExistente(final UUID id) throws VapomanagerException {
        factory.getProveedorDAO().delete(id);
    }

    @Override
    public ProveedorDomain consultarProveedorPorId(final UUID id) throws VapomanagerException {
        var entity = factory.getProveedorDAO().listById(id);
        return ProveedorEntityAssambler.getInstance().toDomain(entity);
    }

    @Override
    public List<ProveedorDomain> consultarProveedores(final ProveedorDomain filtro) throws VapomanagerException {
        var filtroEntity = ProveedorEntityAssambler.getInstance().toEntity(filtro);
        var entities = factory.getProveedorDAO().listByFilter(filtroEntity);
        return ProveedorEntityAssambler.getInstance().toDomain(entities);
    }
}
