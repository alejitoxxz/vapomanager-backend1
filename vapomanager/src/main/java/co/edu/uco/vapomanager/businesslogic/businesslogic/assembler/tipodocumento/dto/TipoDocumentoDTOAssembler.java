package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.TipoDocumentoDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.dto.TipoDocumentoDTO;

public final class TipoDocumentoDTOAssembler implements DTOAssembler<TipoDocumentoDTO, TipoDocumentoDomain> {

    private static final TipoDocumentoDTOAssembler INSTANCE = new TipoDocumentoDTOAssembler();

    private TipoDocumentoDTOAssembler() {
        super();
    }

    public static TipoDocumentoDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public TipoDocumentoDTO toDto(final TipoDocumentoDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return new TipoDocumentoDTO();
        }
        return new TipoDocumentoDTO(domain.getId(), domain.getNombre(), domain.getDescripcion());
    }

    @Override
    public TipoDocumentoDomain toDomain(final TipoDocumentoDTO dto) {
        var dtoSeguro = TipoDocumentoDTO.obtenerValorDefecto(dto);
        return new TipoDocumentoDomain(
            UtilUUID.obtenerValorDefecto(dtoSeguro.getId()),
            UtilTexto.getInstance().obtenerValorDefecto(dtoSeguro.getNombre()),
            UtilTexto.getInstance().obtenerValorDefecto(dtoSeguro.getDescripcion())
        );
    }

    @Override
    public List<TipoDocumentoDomain> toDomain(final List<TipoDocumentoDTO> dtoList) {
        var resultado = new ArrayList<TipoDocumentoDomain>();
        for (TipoDocumentoDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
