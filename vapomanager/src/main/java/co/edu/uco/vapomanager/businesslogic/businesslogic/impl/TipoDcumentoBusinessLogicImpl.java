package co.edu.uco.vapomanager.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.TipoDcumentoBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.entity.TipoDocumentoEntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.TipoDocumentoDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;

public final class TipoDcumentoBusinessLogicImpl implements TipoDcumentoBusinessLogic {

    private final DAOFactory factory;

    public TipoDcumentoBusinessLogicImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public TipoDocumentoDomain consultarTipoDocumentoPorId(final UUID id) throws VapomanagerException {
        var entity = factory.getTipoDocumentoDAO().listById(id);
        return TipoDocumentoEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<TipoDocumentoDomain> consultarTipoDocumentos(final TipoDocumentoDomain filtro) throws VapomanagerException {
        var filtroEntity = TipoDocumentoEntityAssembler.getInstance().toEntity(filtro);
        var entities = factory.getTipoDocumentoDAO().listByFilter(filtroEntity);
        return TipoDocumentoEntityAssembler.getInstance().toDomain(entities);
    }
}
