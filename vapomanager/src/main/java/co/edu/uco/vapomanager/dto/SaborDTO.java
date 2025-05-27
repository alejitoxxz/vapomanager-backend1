package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class SaborDTO {

	private UUID id;
	private String sabor;

	public SaborDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setSabor(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public SaborDTO(final UUID id, final String sabor) {
		setId(id);
		setSabor(sabor);
	}

	public static SaborDTO obtenerValorDefecto(final SaborDTO entidad) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entidad, new SaborDTO());
	}

	public UUID getId() {
		return id;
	}

	public SaborDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getSabor() {
		return sabor;
	}

	public SaborDTO setSabor(final String sabor) {
		this.sabor = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(sabor);
		return this;
	}
}