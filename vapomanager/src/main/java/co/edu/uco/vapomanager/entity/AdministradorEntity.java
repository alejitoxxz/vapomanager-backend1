package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class AdministradorEntity {

    public static final AdministradorEntity DEFAULT_OBJECT = new AdministradorEntity();

    private UUID id;
    private String correo;

    public AdministradorEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public AdministradorEntity(UUID id, String correo) {
        setId(id);
        setCorreo(correo);
    }

    public static AdministradorEntity create(UUID id, String correo) {
        return new AdministradorEntity(id, correo);
    }

    public UUID getId() {
        return id;
    }

    public AdministradorEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public AdministradorEntity setCorreo(String correo) {
        this.correo = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(correo);
        return this;
    }
}