package co.edu.uco.vapomanager.businesslogic.fecade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.dto.CiudadDTO;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface CiudadFacade {

	CiudadDTO consultarCiudadPorId(UUID id)throws VapomanagerException;
    List<CiudadDTO> consultarCiudades(CiudadDTO filtro)throws VapomanagerException;
}
