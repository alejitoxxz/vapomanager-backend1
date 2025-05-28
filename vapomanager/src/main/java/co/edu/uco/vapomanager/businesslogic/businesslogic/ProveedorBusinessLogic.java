package co.edu.uco.vapomanager.businesslogic.businesslogic;



import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.ProveedorDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

import java.util.List;
import java.util.UUID;

public interface ProveedorBusinessLogic {

    void registrarNuevoProveedor(ProveedorDomain pais)throws VapomanagerException;
    void modificarProveedorExistente(UUID id, ProveedorDomain pais)throws VapomanagerException;
    void darBajaDefinitivamenteProveedorExistente(UUID id)throws VapomanagerException;
    ProveedorDomain consultarProveedorPorId(UUID id)throws VapomanagerException;
    List<ProveedorDomain> consultarProveedores(ProveedorDomain filtro)throws VapomanagerException;
}
