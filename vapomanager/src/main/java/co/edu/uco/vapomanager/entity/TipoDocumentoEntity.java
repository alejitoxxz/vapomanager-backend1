package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class TipoDocumentoEntity {

    public static final TipoDocumentoEntity DEFAULT_OBJECT = new TipoDocumentoEntity();

    private UUID id;
    private String nombre;
    private String descripcion;

    public TipoDocumentoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoDocumentoEntity(UUID id, String nombre, String descripcion) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public static TipoDocumentoEntity create(UUID id, String nombre, String descripcion) {
        return new TipoDocumentoEntity(id, nombre, descripcion);
    }

    public UUID getId() {
        return id;
    }

    public TipoDocumentoEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDocumentoEntity setNombre(String nombre) {
        this.nombre = UtilTexto.getInstance().obtenerValorDefecto(nombre);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoDocumentoEntity setDescripcion(String descripcion) {
        this.descripcion = UtilTexto.getInstance().obtenerValorDefecto(descripcion);
        return this;
    }
}