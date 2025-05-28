package co.edu.uco.vapomanager.businesslogic.fecade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.dto.AdministradorDTO;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface AdministradorFacade {

    void registrarNuevoAdministrador(AdministradorDTO pais)throws VapomanagerException;
    void modificarAdministradorExistente(UUID id, AdministradorDTO pais)throws VapomanagerException;
    void darBajaDefinitivamenteAdministradorExistente(UUID id)throws VapomanagerException;
    AdministradorDTO consultarAdministradorPorId(UUID id)throws VapomanagerException;
    List<AdministradorDTO> consultarAdministradores(AdministradorDTO filtro)throws VapomanagerException;
}
