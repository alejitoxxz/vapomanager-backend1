package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class EstadoProductoEntity {

    private UUID id;
    private String estado;
    private String descripcionEstado;

    public static final EstadoProductoEntity DEFAULT_OBJECT = new EstadoProductoEntity();

    public EstadoProductoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setEstado(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcionEstado(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoProductoEntity(final UUID id, final String estado, final String descripcionEstado) {
        setId(id);
        setEstado(estado);
        setDescripcionEstado(descripcionEstado);
    }

    public static EstadoProductoEntity obtenerValorDefecto(final EstadoProductoEntity estadoProducto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(estadoProducto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(estado);
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(final String descripcionEstado) {
        this.descripcionEstado = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(descripcionEstado);
    }
}