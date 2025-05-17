package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class TipoDocumentoDTO {

    public static final TipoDocumentoDTO DEFAULT_OBJECT = new TipoDocumentoDTO();

    private UUID id;
    private String nombre;
    private String descripcion;

    public TipoDocumentoDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoDocumentoDTO(final UUID id, final String nombre, final String descripcion) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public static TipoDocumentoDTO obtenerValorDefecto(final TipoDocumentoDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public TipoDocumentoDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDocumentoDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().obtenerValorDefecto(nombre);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoDocumentoDTO setDescripcion(final String descripcion) {
        this.descripcion = UtilTexto.getInstance().obtenerValorDefecto(descripcion);
        return this;
    }
}