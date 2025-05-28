package co.edu.uco.vapomanager.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface DepartamentoBusinessLogic {

    DepartamentoDomain consultarDepartamentoPorId(UUID id)throws VapomanagerException;
    List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro)throws VapomanagerException;
}
