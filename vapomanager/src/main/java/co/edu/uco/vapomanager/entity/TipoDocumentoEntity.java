package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class TipoDocumentoEntity {

	private UUID id;
	private String nombre;
	private String descripcion;

	public TipoDocumentoEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public TipoDocumentoEntity(final UUID id, final String nombre, final String descripcion) {
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
	}

	public static TipoDocumentoEntity obtenerValorDefecto(final TipoDocumentoEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new TipoDocumentoEntity());
	}

	public static TipoDocumentoEntity obtenerValorDefecto() {
		return new TipoDocumentoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
	}
}