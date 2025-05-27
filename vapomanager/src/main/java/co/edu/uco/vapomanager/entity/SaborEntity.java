package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class SaborEntity {

	private UUID id;
	private String sabor;

	public SaborEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setSabor(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public SaborEntity(final UUID id, final String sabor) {
		setId(id);
		setSabor(sabor);
	}

	public static SaborEntity obtenerValorDefecto(final SaborEntity entidad) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entidad, new SaborEntity());
	}

	public static SaborEntity obtenerValorDefecto() {
		return new SaborEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(final String sabor) {
		this.sabor = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(sabor);
	}
}