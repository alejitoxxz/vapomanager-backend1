package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CategoriaDTO {

	private UUID id;
	private String nombreCategoria;
	private String descripcion;

	public CategoriaDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public CategoriaDTO(final UUID id) {
		setId(id);
		setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public CategoriaDTO(final UUID id, final String nombreCategoria, final String descripcion) {
		setId(id);
		setNombreCategoria(nombreCategoria);
		setDescripcion(descripcion);
	}

	private CategoriaDTO(final Builder builder) {
		setId(builder.id);
		setNombreCategoria(builder.nombreCategoria);
		setDescripcion(builder.descripcion);
	}

	public static CategoriaDTO obtenerValorDefecto(final CategoriaDTO categoria) {
		return UtilObjeto.getInstance().obtenerValorDefecto(categoria, new CategoriaDTO());
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

	public static class Builder {
		private UUID id;
		private String nombreCategoria;
		private String descripcion;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder nombreCategoria(final String nombreCategoria) {
			this.nombreCategoria = nombreCategoria;
			return this;
		}

		public Builder descripcion(final String descripcion) {
			this.descripcion = descripcion;
			return this;
		}

		public CategoriaDTO crear() {
			return new CategoriaDTO(this);
		}
	}
}