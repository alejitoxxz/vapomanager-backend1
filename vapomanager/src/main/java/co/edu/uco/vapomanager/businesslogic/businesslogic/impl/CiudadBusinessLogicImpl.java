package co.edu.uco.vapomanager.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.CiudadBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.entity.CiudadEntityAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

public final class CiudadBusinessLogicImpl implements CiudadBusinessLogic {

    private final DAOFactory factory;

    public CiudadBusinessLogicImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public CiudadDomain consultarCiudadPorId(final UUID id) throws VapomanagerException {
        var ciudadEntity = factory.getCiudadDAO().listById(id);
        return CiudadEntityAssambler.getInstance().toDomain(ciudadEntity);
    }

    @Override
    public List<CiudadDomain> consultarCiudades(final CiudadDomain filtro) throws VapomanagerException {
        var filtroEntity = CiudadEntityAssambler.getInstance().toEntity(filtro);
        var entidades = factory.getCiudadDAO().listByFilter(filtroEntity);
        return CiudadEntityAssambler.getInstance().toDomain(entidades);
    }
}
