package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.TipoDocumentoDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

public final class TipoDocumentoEntityAssembler implements EntityAssembler<TipoDocumentoEntity, TipoDocumentoDomain> {

    private static final TipoDocumentoEntityAssembler INSTANCE = new TipoDocumentoEntityAssembler();

    private TipoDocumentoEntityAssembler() {
        super();
    }

    public static TipoDocumentoEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public TipoDocumentoEntity toEntity(final TipoDocumentoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? TipoDocumentoEntity.obtenerValorDefecto()
                : new TipoDocumentoEntity(domain.getId(), domain.getNombre(), domain.getDescripcion());
    }

    @Override
    public TipoDocumentoDomain toDomain(final TipoDocumentoEntity entity) {
        var entidadSegura = TipoDocumentoEntity.obtenerValorDefecto(entity);
        return new TipoDocumentoDomain(entidadSegura.getId(), entidadSegura.getNombre(), entidadSegura.getDescripcion());
    }

    @Override
    public List<TipoDocumentoDomain> toDomain(final List<TipoDocumentoEntity> entityList) {
        var listaResultado = new ArrayList<TipoDocumentoDomain>();
        for (TipoDocumentoEntity entity : entityList) {
            listaResultado.add(toDomain(entity));
        }
        return listaResultado;
    }
}
