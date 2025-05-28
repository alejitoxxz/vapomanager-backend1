package co.edu.uco.vapomanager.businesslogic.fecade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.dto.DepartamentoDTO;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface DepartamentoFacade {

	DepartamentoDTO consultarDepartamentoPorId(UUID id)throws VapomanagerException;
    List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro)throws VapomanagerException;
}
