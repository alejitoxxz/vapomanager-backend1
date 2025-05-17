package co.edu.uco.vapomanager.dto;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class CiudadDTO {

    public static final CiudadDTO DEFAULT_OBJECT = new CiudadDTO();

    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;

    public CiudadDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());
    }

    public CiudadDTO(UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());
    }

    public CiudadDTO(UUID id, String nombre, DepartamentoDTO departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public UUID getId() {
        return id;
    }

    public CiudadDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombre);
        return this;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = (departamento != null) ? departamento : DepartamentoDTO.obtenerValorDefecto();
        return this;
    }
}