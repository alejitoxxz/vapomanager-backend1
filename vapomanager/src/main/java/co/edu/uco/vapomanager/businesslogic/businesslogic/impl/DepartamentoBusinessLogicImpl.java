package co.edu.uco.vapomanager.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.departamento.entity.DepartamentoEntityAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

public final class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

    private final DAOFactory factory;

    public DepartamentoBusinessLogicImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(final UUID id) throws VapomanagerException {
        var departamentoEntity = factory.getDepartamentoDAO().listById(id);
        return DepartamentoEntityAssambler.getInstance().toDomain(departamentoEntity);
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamentos(final DepartamentoDomain filtro) throws VapomanagerException {
        var filtroEntity = DepartamentoEntityAssambler.getInstance().toEntity(filtro);
        var entidades = factory.getDepartamentoDAO().listByFilter(filtroEntity);
        return DepartamentoEntityAssambler.getInstance().toDomain(entidades);
    }
}
