package co.edu.uco.vapomanager.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.TipoDocumentoDomain;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface TipoDcumentoBusinessLogic {

	TipoDocumentoDomain consultarTipoDocumentoPorId(UUID id)throws VapomanagerException;
    List<TipoDocumentoDomain> consultarTipoDocumentos(TipoDocumentoDomain filtro)throws VapomanagerException;
}
