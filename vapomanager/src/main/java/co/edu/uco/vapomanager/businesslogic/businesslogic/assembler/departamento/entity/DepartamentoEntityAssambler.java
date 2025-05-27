package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.departamento.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

public final class DepartamentoEntityAssambler implements EntityAssembler<DepartamentoEntity, DepartamentoDomain> {

    private static final DepartamentoEntityAssambler INSTANCE = new DepartamentoEntityAssambler();

    private DepartamentoEntityAssambler() {
        super();
    }

    public static DepartamentoEntityAssambler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoEntity toEntity(DepartamentoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? DepartamentoEntity.obtenerValorDefecto()
                : new DepartamentoEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public DepartamentoDomain toDomain(DepartamentoEntity entity) {
        var departamento = DepartamentoEntity.obtenerValorDefecto(entity);
        return new DepartamentoDomain(departamento.getId(), departamento.getNombre());
    }

    @Override
    public List<DepartamentoDomain> toDomain(List<DepartamentoEntity> entityList) {
        var listaResultados = new ArrayList<DepartamentoDomain>();
        for (DepartamentoEntity departamentoEntity : entityList) {
            listaResultados.add(toDomain(departamentoEntity));
        }
        return listaResultados;
    }
}