package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class AdministradorDTO {

    public static final AdministradorDTO DEFAULT_OBJECT = new AdministradorDTO();

    private UUID id;
    private String correo;

    public AdministradorDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public AdministradorDTO(final UUID id) {
        setId(id);
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public AdministradorDTO(final UUID id, final String correo) {
        setId(id);
        setCorreo(correo);
    }

    public static AdministradorDTO obtenerValorDefecto() {
        return DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public AdministradorDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public AdministradorDTO setCorreo(final String correo) {
        this.correo = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(correo);
        return this;
    }
}