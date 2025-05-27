package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class AdministradorDTO {

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

	private AdministradorDTO(final Builder builder) {
		setId(builder.id);
		setCorreo(builder.correo);
	}

	public static AdministradorDTO obtenerValorDefecto(final AdministradorDTO administrador) {
		return UtilObjeto.getInstance().obtenerValorDefecto(administrador, new AdministradorDTO());
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

	public static class Builder {
		private UUID id;
		private String correo;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder correo(final String correo) {
			this.correo = correo;
			return this;
		}

		public AdministradorDTO crear() {
			return new AdministradorDTO(this);
		}
	}
}