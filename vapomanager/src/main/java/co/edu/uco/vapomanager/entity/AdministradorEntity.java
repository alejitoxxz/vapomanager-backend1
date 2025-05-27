package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class AdministradorEntity {

	private UUID id;
	private String correo;

	public AdministradorEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public AdministradorEntity(final UUID id) {
		setId(id);
		setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public AdministradorEntity(final UUID id, final String correo) {
		setId(id);
		setCorreo(correo);
	}

	public static AdministradorEntity obtenerValorDefecto(final AdministradorEntity administrador) {
		return UtilObjeto.getInstance().obtenerValorDefecto(administrador, new AdministradorEntity());
	}

	public static AdministradorEntity obtenerValorDefecto() {
		return new AdministradorEntity();
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