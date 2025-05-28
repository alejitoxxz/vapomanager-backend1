package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.administrador.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.AdministradorDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

public final class AdministradorEntityAssembler implements EntityAssembler<AdministradorEntity, AdministradorDomain> {

    private static final AdministradorEntityAssembler INSTANCE = new AdministradorEntityAssembler();

    private AdministradorEntityAssembler() {
        super();
    }

    public static AdministradorEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public AdministradorEntity toEntity(final AdministradorDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? AdministradorEntity.obtenerValorDefecto()
                : new AdministradorEntity(
                    domain.getId(),
                    domain.getCorreo()
                );
    }

    @Override
    public AdministradorDomain toDomain(final AdministradorEntity entity) {
        var safeEntity = AdministradorEntity.obtenerValorDefecto(entity);
        return new AdministradorDomain(
            safeEntity.getId(),
            safeEntity.getCorreo()
        );
    }

    @Override
    public List<AdministradorDomain> toDomain(final List<AdministradorEntity> entityList) {
        var resultado = new ArrayList<AdministradorDomain>();
        for (AdministradorEntity entity : entityList) {
            resultado.add(toDomain(entity));
        }
        return resultado;
    }
}
