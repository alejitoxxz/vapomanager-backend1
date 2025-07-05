package co.edu.uco.vapomanager.api;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.vapomanager.businesslogic.fecade.TipoDocumentoFacade;
import co.edu.uco.vapomanager.businesslogic.fecade.impl.TipoDocumentoFacadeImpl;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.dto.TipoDocumentoDTO;

@RestController
@RequestMapping("/api/v1/tipodocumentos")
public class TipoDocumentoController {

    private final TipoDocumentoFacade tipoDocumentoFacade;

    // Spring inyecta automáticamente el DataSource configurado
    public TipoDocumentoController() throws VapomanagerException {
        this.tipoDocumentoFacade = new TipoDocumentoFacadeImpl();
    }

    @GetMapping("/dummy")
    public TipoDocumentoDTO getDummy() {
        return new TipoDocumentoDTO();
    }

    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> consultarTodos() throws VapomanagerException {
        List<TipoDocumentoDTO> lista = tipoDocumentoFacade.consultarTipoDocumentos(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoDTO> consultarPorId(@PathVariable("id") UUID id) throws VapomanagerException {
        TipoDocumentoDTO tipoDocumento = tipoDocumentoFacade.consultarTipoDocumentoPorId(id);
        return new ResponseEntity<>(tipoDocumento, HttpStatus.OK);
    }
}
