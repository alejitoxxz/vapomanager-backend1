package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.proveedor.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilCorreo;
import co.edu.uco.vapomanager.dto.ProveedorDTO;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.dto.CiudadDTOAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.dto.TipoDocumentoDTOAssembler;

public final class ProveedorDTOAssambler implements DTOAssembler<ProveedorDTO, ProveedorDomain> {

    private static final ProveedorDTOAssambler INSTANCE = new ProveedorDTOAssambler();

    private ProveedorDTOAssambler() {
        super();
    }

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
        var dtoSeguro = ProveedorDTO.obtenerValorDefecto(dto);
        UUID id = UtilUUID.obtenerValorDefecto(dtoSeguro.getId());
        String nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getNombreEmpresa());
        boolean confTel = UtilBooleano.obtenerValorDefecto(dtoSeguro.isConfirmacionTelefono());
        boolean confCorreo = UtilBooleano.obtenerValorDefecto(dtoSeguro.isConfirmacionCorreo());
        String correo = UtilCorreo.obtenerValorDefecto(dtoSeguro.getCorreoElectronico());
        boolean estado = UtilBooleano.obtenerValorDefecto(dtoSeguro.isEstadoCuenta());
        int telefono = UtilNumero.obtenerValorDefecto(dtoSeguro.getNumeroTelefono());
        String direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getDireccion());
        var ciudad = CiudadDTOAssambler.getInstance().toDomain(dtoSeguro.getCiudad());
        String descDir = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(dtoSeguro.getDescripcionDireccion());
        var tipoDoc = TipoDocumentoDTOAssembler.getInstance().toDomain(dtoSeguro.getTipoDocumento());
        int numDoc = dtoSeguro.getNumeroDocumento();

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
        var resultado = new ArrayList<ProveedorDomain>();
        for (ProveedorDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
