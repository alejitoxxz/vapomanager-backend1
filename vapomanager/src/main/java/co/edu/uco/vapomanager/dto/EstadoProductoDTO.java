package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class EstadoProductoDTO {

	private UUID id;
	private String estado;
	private String descripcionEstado;

	public EstadoProductoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setEstado(UtilTexto.getInstance().obtenerValorDefecto());
		setDescripcionEstado(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public EstadoProductoDTO(final UUID id, final String estado, final String descripcionEstado) {
		setId(id);
		setEstado(estado);
		setDescripcionEstado(descripcionEstado);
	}

	public static EstadoProductoDTO obtenerValorDefecto(final EstadoProductoDTO estadoProducto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(estadoProducto, new EstadoProductoDTO());
	}

	public UUID getId() {
		return id;
	}

	public EstadoProductoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getEstado() {
		return estado;
	}

	public EstadoProductoDTO setEstado(final String estado) {
		this.estado = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(estado);
		return this;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public EstadoProductoDTO setDescripcionEstado(final String descripcionEstado) {
		this.descripcionEstado = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionEstado);
		return this;
	}
}