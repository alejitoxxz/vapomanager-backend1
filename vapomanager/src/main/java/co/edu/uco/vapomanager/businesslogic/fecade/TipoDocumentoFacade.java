package co.edu.uco.vapomanager.businesslogic.fecade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.dto.TipoDocumentoDTO;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface TipoDocumentoFacade {

	TipoDocumentoDTO consultarTipoDocumentoPorId(UUID id)throws VapomanagerException;
    List<TipoDocumentoDTO> consultarTipoDocumentos(TipoDocumentoDTO filtro)throws VapomanagerException;
}
