package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.departamento.entity.DepartamentoEntityAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.entity.CiudadEntity;

public final class CiudadEntityAssambler implements EntityAssembler<CiudadEntity, CiudadDomain> {

    private static final CiudadEntityAssambler INSTANCE = new CiudadEntityAssambler();

    private CiudadEntityAssambler() {
        super();
    }

    public static CiudadEntityAssambler getInstance() {
        return INSTANCE;
    }

    @Override
    public CiudadEntity toEntity(CiudadDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? CiudadEntity.obtenerValorDefecto()
                : new CiudadEntity(
                    domain.getId(),
                    domain.getNombre(),
                    DepartamentoEntityAssambler.getInstance().toEntity(domain.getDepartamento())
                );
    }

    @Override
    public CiudadDomain toDomain(CiudadEntity entity) {
        var ciudadEntity = CiudadEntity.obtenerValorDefecto(entity);
        return new CiudadDomain(
            ciudadEntity.getId(),
            ciudadEntity.getNombre(),
            DepartamentoEntityAssambler.getInstance().toDomain(ciudadEntity.getDepartamento())
        );
    }

    @Override
    public List<CiudadDomain> toDomain(List<CiudadEntity> entityList) {
        var resultado = new ArrayList<CiudadDomain>();
        for (CiudadEntity entity : entityList) {
            resultado.add(toDomain(entity));
        }
        return resultado;
    }
}
