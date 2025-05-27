package co.edu.uco.vapomanager.businesslogic.businesslogic.domain;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class AdministradorDomain {

    public static final AdministradorDomain DEFAULT_OBJECT = new AdministradorDomain();

    private UUID id;
    private String correo;

    public AdministradorDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public AdministradorDomain(final UUID id) {
        setId(id);
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public AdministradorDomain(final UUID id, final String correo) {
        setId(id);
        setCorreo(correo);
    }

    public static AdministradorDomain obtenerValorDefecto() {
        return DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(final String correo) {
        this.correo = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(correo);
    }
}