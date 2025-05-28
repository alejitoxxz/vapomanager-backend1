package co.edu.uco.vapomanager.businesslogic.fecade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.dto.ProveedorDTO;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface ProveedorFacade {

    void registrarNuevoProveedor(ProveedorDTO pais)throws VapomanagerException;
    void modificarProveedorExistente(UUID id, ProveedorDTO pais)throws VapomanagerException;
    void darBajaDefinitivamenteProveedorExistente(UUID id)throws VapomanagerException;
    ProveedorDTO consultarProveedorPorId(UUID id)throws VapomanagerException;
    List<ProveedorDTO> consultarProveedores(ProveedorDTO filtro)throws VapomanagerException;
}
