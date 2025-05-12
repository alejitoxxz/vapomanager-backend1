package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class SaborEntity {

    private UUID id;
    private String sabor;

    public static final SaborEntity DEFAULT_OBJECT = new SaborEntity();

    public SaborEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setSabor(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public SaborEntity(final UUID id, final String sabor) {
        setId(id);
        setSabor(sabor);
    }

    public static SaborEntity obtenerValorDefecto(final SaborEntity entidad) {
        return entidad != null ? entidad : DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public SaborEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getSabor() {
        return sabor;
    }

    public SaborEntity setSabor(String sabor) {
        this.sabor = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(sabor);
        return this;
    }
}
