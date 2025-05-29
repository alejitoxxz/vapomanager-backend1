package co.edu.uco.vapomanager.api;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.vapomanager.businesslogic.fecade.ProveedorFacade;
import co.edu.uco.vapomanager.businesslogic.fecade.impl.ProveedorFacadeImpl;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.dto.ProveedorDTO;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    private final ProveedorFacade proveedorFachada;

    public ProveedorController(DataSource dataSource) throws VapomanagerException {
        proveedorFachada = new ProveedorFacadeImpl(dataSource);
    }

    @GetMapping("/dummy")
    public ProveedorDTO getDummy() {
        return new ProveedorDTO();
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> consultar() throws VapomanagerException {
        var lista = proveedorFachada.consultarProveedores(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> consultar(@PathVariable("id") UUID id) throws VapomanagerException {
        var proveedor = proveedorFachada.consultarProveedorPorId(id);
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ProveedorDTO proveedor) throws VapomanagerException {
        proveedorFachada.registrarNuevoProveedor(proveedor);
        var mensajeExito = "El proveedor " + proveedor.getNombreEmpresa() + " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody ProveedorDTO proveedor) throws VapomanagerException {
        proveedor.setId(id);
        proveedorFachada.modificarProveedorExistente(id, proveedor);
        var mensajeExito = "El proveedor " + proveedor.getNombreEmpresa() + " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws VapomanagerException {
        var proveedor = proveedorFachada.consultarProveedorPorId(id);
        proveedorFachada.darBajaDefinitivamenteProveedorExistente(id);
        var mensajeExito = "El proveedor " + proveedor.getNombreEmpresa() + " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }
}