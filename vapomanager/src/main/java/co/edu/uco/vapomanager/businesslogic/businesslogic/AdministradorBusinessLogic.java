package co.edu.uco.vapomanager.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.AdministradorDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface AdministradorBusinessLogic {

    void registrarNuevoAdministrador(AdministradorDomain pais)throws VapomanagerException;
    void modificarAdministradorExistente(UUID id, AdministradorDomain pais)throws VapomanagerException;
    void darBajaDefinitivamenteAdministradorExistente(UUID id)throws VapomanagerException;
    AdministradorDomain consultarAdministradorPorId(UUID id)throws VapomanagerException;
    List<AdministradorDomain> consultarAdministradores(AdministradorDomain filtro)throws VapomanagerException;
}
