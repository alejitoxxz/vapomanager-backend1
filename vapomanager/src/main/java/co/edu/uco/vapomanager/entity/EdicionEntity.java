package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public class EdicionEntity {

    private UUID id;
    private int edicion;

    public static final EdicionEntity DEFAULT_OBJECT = new EdicionEntity();

    public EdicionEntity() {
        setId(UtilUUID.obtenerValorDefecto())
        .setEdicion(0);
    }

    public EdicionEntity(final UUID id) {
        setId(id)
        .setEdicion(0);
    }

    public EdicionEntity(final UUID id, final int edicion) {
        setId(id)
        .setEdicion(edicion);
    }

    public static EdicionEntity obtenerValorDefecto(final EdicionEntity edicion) {
        return co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto.getInstance()
                .obtenerValorDefecto(edicion, EdicionEntity.DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public EdicionEntity setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public int getEdicion() {
        return edicion;
    }

    public EdicionEntity setEdicion(final int edicion) {
        this.edicion = UtilNumero.obtenerValorDefecto(edicion);
        return this;
    }
}
