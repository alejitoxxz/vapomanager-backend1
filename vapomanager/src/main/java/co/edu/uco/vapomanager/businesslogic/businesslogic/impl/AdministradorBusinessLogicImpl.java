package co.edu.uco.vapomanager.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.AdministradorBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.administrador.entity.AdministradorEntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.AdministradorDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

public final class AdministradorBusinessLogicImpl implements AdministradorBusinessLogic {

    private final DAOFactory factory;

    public AdministradorBusinessLogicImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoAdministrador(final AdministradorDomain admin) throws VapomanagerException {
        // 1. Validar integridad del correo
        validarIntegridadCorreo(admin.getCorreo());

        // 2. Validar unicidad
        validarNoExistaAdministradorConMismoID(admin.getId());
        validarNoExistaAdministradorConMismoCorreo(admin.getCorreo());

        // 3. Generar nuevo identificador
        var nuevoId = generarIdentificadorNuevoAdministrador();

        // 4. Reconstruir el domain con el ID generado
        var domainACrear = new AdministradorDomain(nuevoId, admin.getCorreo());

        // 5. Crear en la capa de persistencia
        var entity = AdministradorEntityAssembler.getInstance().toEntity(domainACrear);
        factory.getAdministradorDAO().create(entity);
    }

    private void validarIntegridadCorreo(final String correo) throws VapomanagerException {
        if (UtilTexto.getInstance().estaVacia(correo)) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico es obligatorio");
        }
        if (!UtilTexto.getInstance().patronValido(correo, ".+@.+\\..+")) {
            throw BusinessLogicVapomanagerException.reportar("Formato de correo electrónico inválido");
        }
        if (correo.length() > 100) {
            throw BusinessLogicVapomanagerException.reportar("El correo electrónico supera los 100 caracteres");
        }
    }

    private void validarNoExistaAdministradorConMismoID(final UUID id) throws VapomanagerException {
        if (!UtilUUID.esValorDefecto(id)) {
            var existente = factory.getAdministradorDAO().listById(id);
            if (!UtilUUID.esValorDefecto(existente.getId())) {
                throw BusinessLogicVapomanagerException.reportar("Ya existe un administrador con el mismo ID");
            }
        }
    }

    private void validarNoExistaAdministradorConMismoCorreo(final String correo) throws VapomanagerException {
        var filtro = new AdministradorEntity();
        filtro.setCorreo(correo);;
        if (!factory.getAdministradorDAO().listByFilter(filtro).isEmpty()) {
            throw BusinessLogicVapomanagerException.reportar("Ya existe un administrador con ese correo electrónico");
        }
    }

    private UUID generarIdentificadorNuevoAdministrador() throws VapomanagerException {
        UUID nuevoId;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
        } while (!UtilUUID.esValorDefecto(
            factory.getAdministradorDAO().listById(nuevoId).getId()
        ));
        return nuevoId;
    }

    @Override
    public void modificarAdministradorExistente(final UUID id, final AdministradorDomain admin) throws VapomanagerException {
        var entity = AdministradorEntityAssembler.getInstance().toEntity(admin);
        factory.getAdministradorDAO().update(id, entity);
    }

    @Override
    public void darBajaDefinitivamenteAdministradorExistente(final UUID id) throws VapomanagerException {
        factory.getAdministradorDAO().delete(id);
    }

    @Override
    public AdministradorDomain consultarAdministradorPorId(final UUID id) throws VapomanagerException {
        var entity = factory.getAdministradorDAO().listById(id);
        return AdministradorEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<AdministradorDomain> consultarAdministradores(final AdministradorDomain filtro) throws VapomanagerException {
        var filtroEntity = AdministradorEntityAssembler.getInstance().toEntity(filtro);
        var entities = factory.getAdministradorDAO().listByFilter(filtroEntity);
        return AdministradorEntityAssembler.getInstance().toDomain(entities);
    }
}
