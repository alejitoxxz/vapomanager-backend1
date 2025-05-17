package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class MarcaEntity {

    private UUID id;
    private String marca;

    public static final MarcaEntity DEFAULT_OBJECT = new MarcaEntity();

    public MarcaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setMarca(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public MarcaEntity(final UUID id, final String marca) {
        setId(id);
        setMarca(marca);
    }

    public static MarcaEntity obtenerValorDefecto(final MarcaEntity entidad) {
        return entidad != null ? entidad : DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public MarcaEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public MarcaEntity setMarca(String marca) {
        this.marca = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(marca);
        return this;
    }
}