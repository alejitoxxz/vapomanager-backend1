package co.edu.uco.vapomanager.dto;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public class DepartamentoDTO {

    private UUID id;
    private String nombre;

    public DepartamentoDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoDTO(UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoDTO(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static DepartamentoDTO obtenerValorDefecto() {
        return new DepartamentoDTO();
    }

    public static DepartamentoDTO obtenerValorDefecto(final DepartamentoDTO departamento) {
        return (departamento == null) ? obtenerValorDefecto() : departamento;
    }

    public UUID getId() {
        return id;
    }

    public DepartamentoDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombre);
        return this;
    }
}