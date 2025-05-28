package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.departamento.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.dto.DepartamentoDTO;

public final class DepartamentoDTOAssambler implements DTOAssembler<DepartamentoDTO, DepartamentoDomain> {

    private static final DepartamentoDTOAssambler INSTANCE = new DepartamentoDTOAssambler();

    private DepartamentoDTOAssambler() {
        super();
    }

    public static DepartamentoDTOAssambler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoDTO toDto(final DepartamentoDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return new DepartamentoDTO();
        }
        return new DepartamentoDTO(domain.getId(), domain.getNombre());
    }

    @Override
    public DepartamentoDomain toDomain(final DepartamentoDTO dto) {
        var dtoSeguro = DepartamentoDTO.obtenerValorDefecto(dto);
        return new DepartamentoDomain(
            UtilUUID.obtenerValorDefecto(dtoSeguro.getId()),
            UtilTexto.getInstance().obtenerValorDefecto(dtoSeguro.getNombre())
        );
    }

    @Override
    public List<DepartamentoDomain> toDomain(final List<DepartamentoDTO> dtoList) {
        var resultado = new ArrayList<DepartamentoDomain>();
        for (DepartamentoDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
