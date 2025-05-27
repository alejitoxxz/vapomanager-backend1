package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class MarcaDTO {

	private UUID id;
	private String marca;

	public MarcaDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setMarca(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public MarcaDTO(final UUID id, final String marca) {
		setId(id);
		setMarca(marca);
	}

	public static MarcaDTO obtenerValorDefecto(final MarcaDTO entidad) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entidad, new MarcaDTO());
	}

	public UUID getId() {
		return id;
	}

	public MarcaDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getMarca() {
		return marca;
	}

	public MarcaDTO setMarca(final String marca) {
		this.marca = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(marca);
		return this;
	}
}