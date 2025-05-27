package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class EdicionDTO {

	private UUID id;
	private int edicion;

	public EdicionDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setEdicion(UtilNumero.obtenerValorDefecto(0));
	}

	public EdicionDTO(final UUID id) {
		setId(id);
		setEdicion(UtilNumero.obtenerValorDefecto(0));
	}

	public EdicionDTO(final UUID id, final int edicion) {
		setId(id);
		setEdicion(edicion);
	}

	public static EdicionDTO obtenerValorDefecto(final EdicionDTO edicion) {
		return UtilObjeto.getInstance().obtenerValorDefecto(edicion, new EdicionDTO());
	}

	public UUID getId() {
		return id;
	}

	public EdicionDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public int getEdicion() {
		return edicion;
	}

	public EdicionDTO setEdicion(final int edicion) {
		this.edicion = UtilNumero.obtenerValorDefecto(edicion);
		return this;
	}
}