package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class EdicionEntity {

	private UUID id;
	private int edicion;

	public EdicionEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setEdicion(UtilNumero.obtenerValorDefecto(0));
	}

	public EdicionEntity(final UUID id) {
		setId(id);
		setEdicion(UtilNumero.obtenerValorDefecto(0));
	}

	public EdicionEntity(final UUID id, final int edicion) {
		setId(id);
		setEdicion(edicion);
	}

	public static EdicionEntity obtenerValorDefecto(final EdicionEntity edicion) {
		return UtilObjeto.getInstance().obtenerValorDefecto(edicion, new EdicionEntity());
	}

	public static EdicionEntity obtenerValorDefecto() {
		return new EdicionEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(final int edicion) {
		this.edicion = UtilNumero.obtenerValorDefecto(edicion);
	}
}