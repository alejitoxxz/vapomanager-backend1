package co.edu.uco.vapomanager.api;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.vapomanager.businesslogic.fecade.CiudadFacade;
import co.edu.uco.vapomanager.businesslogic.fecade.impl.CiudadFacadeImpl;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.dto.CiudadDTO;
import co.edu.uco.vapomanager.dto.DepartamentoDTO;

@RestController
@RequestMapping("/api/v1/ciudades")
public class CiudadController {

    private final CiudadFacade ciudadFacade;

    
    public CiudadController() throws VapomanagerException {
        this.ciudadFacade = new CiudadFacadeImpl();
    }

    @GetMapping("/dummy")
    public CiudadDTO getDummy() {
        return new CiudadDTO();
    }
    
    @GetMapping(params = "departamentoId")
    public ResponseEntity<List<CiudadDTO>> consultarPorDepartamento(
            @RequestParam("departamentoId") UUID departamentoId) throws VapomanagerException {

        CiudadDTO filtro = new CiudadDTO();
        filtro.setDepartamento(new DepartamentoDTO(departamentoId));

        List<CiudadDTO> lista = ciudadFacade.consultarCiudades(filtro);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CiudadDTO>> consultarTodos() throws VapomanagerException {
        List<CiudadDTO> lista = ciudadFacade.consultarCiudades(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> consultarPorId(@PathVariable("id") UUID id) throws VapomanagerException {
        CiudadDTO ciudad = ciudadFacade.consultarCiudadPorId(id);
        return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }
}
