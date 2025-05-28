package co.edu.uco.vapomanager.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface CiudadBusinessLogic {

	CiudadDomain consultarCiudadPorId(UUID id)throws VapomanagerException;
    List<CiudadDomain> consultarCiudades(CiudadDomain filtro)throws VapomanagerException;
}
