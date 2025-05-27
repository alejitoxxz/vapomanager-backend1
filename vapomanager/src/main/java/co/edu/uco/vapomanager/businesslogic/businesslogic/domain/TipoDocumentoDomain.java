package co.edu.uco.vapomanager.businesslogic.businesslogic.domain;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class TipoDocumentoDomain {

    public static final TipoDocumentoDomain DEFAULT_OBJECT = new TipoDocumentoDomain();

    private UUID id;
    private String nombre;
    private String descripcion;

    public TipoDocumentoDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoDocumentoDomain(UUID id, String nombre, String descripcion) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public static TipoDocumentoDomain create(UUID id, String nombre, String descripcion) {
        return new TipoDocumentoDomain(id, nombre, descripcion);
    }

    public UUID getId() {
        return id;
    }

    public TipoDocumentoDomain setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDocumentoDomain setNombre(String nombre) {
        this.nombre = UtilTexto.getInstance().obtenerValorDefecto(nombre);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoDocumentoDomain setDescripcion(String descripcion) {
        this.descripcion = UtilTexto.getInstance().obtenerValorDefecto(descripcion);
        return this;
    }
}