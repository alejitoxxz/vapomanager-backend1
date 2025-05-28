package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.proveedor.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.entity.ProveedorEntity;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.entity.CiudadEntityAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.entity.TipoDocumentoEntityAssembler;

public final class ProveedorEntityAssambler implements EntityAssembler<ProveedorEntity, ProveedorDomain> {

    private static final ProveedorEntityAssambler INSTANCE = new ProveedorEntityAssambler();

    private ProveedorEntityAssambler() {
        super();
    }

    public static ProveedorEntityAssambler getInstance() {
        return INSTANCE;
    }

    @Override
    public ProveedorEntity toEntity(final ProveedorDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
            ? ProveedorEntity.obtenerValorDefecto()
            : new ProveedorEntity(
                  domain.getId(),
                  domain.getNombreEmpresa(),
                  domain.isConfirmacionTelefono(),
                  domain.isConfirmacionCorreo(),
                  domain.getCorreoElectronico(),
                  domain.isEstadoCuenta(),
                  domain.getNumeroTelefono(),
                  domain.getDireccion(),
                  CiudadEntityAssambler.getInstance().toEntity(domain.getCiudad()),
                  domain.getDescripcionDireccion(),
                  TipoDocumentoEntityAssembler.getInstance().toEntity(domain.getTipoDocumento()),
                  domain.getNumeroDocumento()
              );
    }

    @Override
    public ProveedorDomain toDomain(final ProveedorEntity entity) {
        var safe = ProveedorEntity.obtenerValorDefecto(entity);
        return new ProveedorDomain(
            safe.getId(),
            safe.getNombreEmpresa(),
            safe.isConfirmacionTelefono(),
            safe.isConfirmacionCorreo(),
            safe.getCorreoElectronico(),
            safe.isEstadoCuenta(),
            safe.getNumeroTelefono(),
            safe.getDireccion(),
            CiudadEntityAssambler.getInstance().toDomain(safe.getCiudad()),
            safe.getDescripcionDireccion(),
            TipoDocumentoEntityAssembler.getInstance().toDomain(safe.getTipoDocumento()),
            safe.getNumeroDocumento()
        );
    }

    @Override
    public List<ProveedorDomain> toDomain(final List<ProveedorEntity> entityList) {
        var resultado = new ArrayList<ProveedorDomain>();
        for (ProveedorEntity e : entityList) {
            resultado.add(toDomain(e));
        }
        return resultado;
    }
}
