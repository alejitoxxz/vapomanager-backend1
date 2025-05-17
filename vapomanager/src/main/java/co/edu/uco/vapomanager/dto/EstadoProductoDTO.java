package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class EstadoProductoDTO {

    public static final EstadoProductoDTO DEFAULT_OBJECT = new EstadoProductoDTO();

    private UUID id;
    private String estado;
    private String descripcionEstado;

    public EstadoProductoDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setEstado(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcionEstado(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoProductoDTO(final UUID id, final String estado, final String descripcionEstado) {
        setId(id);
        setEstado(estado);
        setDescripcionEstado(descripcionEstado);
    }

    public static EstadoProductoDTO obtenerValorDefecto(final EstadoProductoDTO estadoProducto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(estadoProducto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public EstadoProductoDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public EstadoProductoDTO setEstado(final String estado) {
        this.estado = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(estado);
        return this;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public EstadoProductoDTO setDescripcionEstado(final String descripcionEstado) {
        this.descripcionEstado = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(descripcionEstado);
        return this;
    }
}