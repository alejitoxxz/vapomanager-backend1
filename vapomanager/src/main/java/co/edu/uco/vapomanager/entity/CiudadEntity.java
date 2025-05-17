package co.edu.uco.vapomanager.entity;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class CiudadEntity {

    private UUID id;
    private String nombre;
    private DepartamentoEntity departamento;

    public CiudadEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoEntity.obtenerValorDefecto());
    }

    public CiudadEntity(UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoEntity.obtenerValorDefecto());
    }

    public CiudadEntity(UUID id, String nombre, DepartamentoEntity departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static CiudadEntity obtenerValorDefecto() {
        return new CiudadEntity();
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

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(final DepartamentoEntity departamento) {
        this.departamento = (departamento != null) ? departamento : DepartamentoEntity.obtenerValorDefecto();
    }
}