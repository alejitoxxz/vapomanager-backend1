package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public class EdicionDTO {

    private UUID id;
    private int edicion;

    public static final EdicionDTO DEFAULT_OBJECT = new EdicionDTO();

    public static EdicionDTO obtenerValorDefecto(final EdicionDTO edicion) {
        return UtilObjeto.getInstance().obtenerValorDefecto(edicion, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public EdicionDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public int getEdicion() {
        return edicion;
    }

    public EdicionDTO setEdicion(final int edicion) {
        this.edicion = UtilNumero.obtenerValorDefecto(edicion);
        return this;
    }
}