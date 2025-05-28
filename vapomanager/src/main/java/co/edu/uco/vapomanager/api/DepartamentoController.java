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

import co.edu.uco.vapomanager.businesslogic.fecade.DepartamentoFacade;
import co.edu.uco.vapomanager.businesslogic.fecade.impl.DepartamentoFacadeImpl;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.dto.DepartamentoDTO;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentoController {
    
    private final DepartamentoFacade departamentoFacade;

    // Spring inyectará el DataSource automáticamente
    public DepartamentoController(DataSource dataSource) throws VapomanagerException {
        this.departamentoFacade = new DepartamentoFacadeImpl(dataSource);
    }

    @GetMapping("/dummy")
    public DepartamentoDTO getDummy() {
        return new DepartamentoDTO();
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> consultarTodos() throws VapomanagerException {
        List<DepartamentoDTO> lista = departamentoFacade.consultarDepartamentos(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> consultarPorId(@PathVariable("id") UUID id) throws VapomanagerException {
        DepartamentoDTO departamento = departamentoFacade.consultarDepartamentoPorId(id);
        return new ResponseEntity<>(departamento, HttpStatus.OK);
    }
}
