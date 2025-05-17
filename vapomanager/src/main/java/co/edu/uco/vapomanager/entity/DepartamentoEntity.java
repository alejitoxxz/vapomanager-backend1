package co.edu.uco.vapomanager.entity;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public class DepartamentoEntity {

    private UUID id;
    private String nombre;

    public DepartamentoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoEntity(UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoEntity(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static DepartamentoEntity obtenerValorDefecto() {
        return new DepartamentoEntity();
    }

    public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity departamento) {
        return (departamento == null) ? obtenerValorDefecto() : departamento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombre);
    }
}