package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.dto.CiudadDTO;
import co.edu.uco.vapomanager.dto.DepartamentoDTO;

public final class CiudadDTOAssambler implements DTOAssembler<CiudadDTO, CiudadDomain> {

    private static final CiudadDTOAssambler INSTANCE = new CiudadDTOAssambler();

    private CiudadDTOAssambler() {
        super();
    }

    public static CiudadDTOAssambler getInstance() {
        return INSTANCE;
    }

    @Override
    public CiudadDTO toDto(final CiudadDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return new CiudadDTO();
        }

        var departamentoDTO = new DepartamentoDTO(
            domain.getDepartamento().getId(),
            domain.getDepartamento().getNombre()
        );

        return new CiudadDTO(
            domain.getId(),
            domain.getNombre(),
            departamentoDTO
        );
    }

    @Override
    public CiudadDomain toDomain(final CiudadDTO dto) {
        var dtoSeguro = CiudadDTO.obtenerValorDefecto(dto);
        var departamento = new DepartamentoDomain(
            UtilUUID.obtenerValorDefecto(dtoSeguro.getDepartamento().getId()),
            UtilTexto.getInstance().obtenerValorDefecto(dtoSeguro.getDepartamento().getNombre())
        );

        return new CiudadDomain(
            UtilUUID.obtenerValorDefecto(dtoSeguro.getId()),
            UtilTexto.getInstance().obtenerValorDefecto(dtoSeguro.getNombre()),
            departamento
        );
    }

    @Override
    public List<CiudadDomain> toDomain(final List<CiudadDTO> dtoList) {
        var resultado = new ArrayList<CiudadDomain>();
        for (CiudadDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
