package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.administrador.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.AdministradorDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilCorreo;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.dto.AdministradorDTO;

public final class AdministradorDTOAssembler implements DTOAssembler<AdministradorDTO, AdministradorDomain> {

    private static final AdministradorDTOAssembler INSTANCE = new AdministradorDTOAssembler();

    private AdministradorDTOAssembler() {
        super();
    }

    public static AdministradorDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public AdministradorDTO toDto(final AdministradorDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return new AdministradorDTO();
        }

        return new AdministradorDTO(
            domain.getId(),
            domain.getCorreo()
        );
    }

    @Override
    public AdministradorDomain toDomain(final AdministradorDTO dto) {
        var dtoSeguro = AdministradorDTO.obtenerValorDefecto(dto);

        return new AdministradorDomain(
            UtilUUID.obtenerValorDefecto(dtoSeguro.getId()),
            UtilCorreo.obtenerValorDefecto(dtoSeguro.getCorreo())
        );
    }

    @Override
    public List<AdministradorDomain> toDomain(final List<AdministradorDTO> dtoList) {
        var resultado = new ArrayList<AdministradorDomain>();
        for (AdministradorDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
