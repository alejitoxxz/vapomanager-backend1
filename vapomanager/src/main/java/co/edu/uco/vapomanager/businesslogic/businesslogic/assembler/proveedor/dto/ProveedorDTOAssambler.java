package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.proveedor.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.*;
import co.edu.uco.vapomanager.dto.ProveedorDTO;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.dto.CiudadDTOAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.dto.TipoDocumentoDTOAssembler;

public final class ProveedorDTOAssambler implements DTOAssembler<ProveedorDTO, ProveedorDomain> {

    private static final ProveedorDTOAssambler INSTANCE = new ProveedorDTOAssambler();

    private ProveedorDTOAssambler() { }

    public static ProveedorDTOAssambler getInstance() {
        return INSTANCE;
    }

    
    @Override
    public ProveedorDTO toDto(final ProveedorDomain domain) {

        if (UtilObjeto.getInstance().esNulo(domain)) {
            return new ProveedorDTO();
        }

        return new ProveedorDTO(
                domain.getId(),
                domain.getNombreEmpresa(),
                domain.isConfirmacionTelefono(),
                domain.isConfirmacionCorreo(),
                domain.getCorreoElectronico(),
                domain.isEstadoCuenta(),
                domain.getNumeroTelefono(),
                domain.getDireccion(),
                CiudadDTOAssambler.getInstance().toDto(domain.getCiudad()),
                domain.getDescripcionDireccion(),
                TipoDocumentoDTOAssembler.getInstance().toDto(domain.getTipoDocumento()),
                domain.getNumeroDocumento()
        );
    }

    
    @Override
    public ProveedorDomain toDomain(final ProveedorDTO dto) {

        ProveedorDTO dtoSeguro = ProveedorDTO.obtenerValorDefecto(dto);

        UUID    id          = UtilUUID.obtenerValorDefecto(dtoSeguro.getId());
        String  nombre      = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getNombreEmpresa());
        boolean confTel     = UtilBooleano.obtenerValorDefecto(dtoSeguro.isConfirmacionTelefono());
        boolean confCorreo  = UtilBooleano.obtenerValorDefecto(dtoSeguro.isConfirmacionCorreo());
        String  correo      = UtilCorreo.obtenerValorDefecto(dtoSeguro.getCorreoElectronico());
        boolean estado      = UtilBooleano.obtenerValorDefecto(dtoSeguro.isEstadoCuenta());
        long    telefono    = UtilNumero.obtenerValorDefecto(dtoSeguro.getNumeroTelefono());   // ← seguro
        String  direccion   = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getDireccion());
        var     ciudad      = CiudadDTOAssambler.getInstance().toDomain(dtoSeguro.getCiudad());
        String  descDir     = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getDescripcionDireccion());
        var     tipoDoc     = TipoDocumentoDTOAssembler.getInstance().toDomain(dtoSeguro.getTipoDocumento());
        long    numDoc      = UtilNumero.obtenerValorDefecto(dtoSeguro.getNumeroDocumento());  // ← seguro

        return new ProveedorDomain(
                id,
                nombre,
                confTel,
                confCorreo,
                correo,
                estado,
                telefono,
                direccion,
                ciudad,
                descDir,
                tipoDoc,
                numDoc
        );
    }

    
    @Override
    public List<ProveedorDomain> toDomain(final List<ProveedorDTO> dtoList) {
        List<ProveedorDomain> resultado = new ArrayList<>();
        for (ProveedorDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
