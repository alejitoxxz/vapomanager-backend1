package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CategoriaEntity {

	private UUID id;
	private String nombreCategoria;
	private String descripcion;

	public CategoriaEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public CategoriaEntity(final UUID id) {
		setId(id);
		setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public CategoriaEntity(final UUID id, final String nombreCategoria, final String descripcion) {
		setId(id);
		setNombreCategoria(nombreCategoria);
		setDescripcion(descripcion);
	}

	public static CategoriaEntity obtenerValorDefecto(final CategoriaEntity categoria) {
		return UtilObjeto.getInstance().obtenerValorDefecto(categoria, new CategoriaEntity());
	}

	public static CategoriaEntity obtenerValorDefecto() {
		return new CategoriaEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(final String nombreCategoria) {
		this.nombreCategoria = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreCategoria);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
	}
}