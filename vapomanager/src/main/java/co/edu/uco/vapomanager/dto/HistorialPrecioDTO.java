package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBigDecimal;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class HistorialPrecioDTO {

	private UUID id;
	private UUID proveedorProducto;
	private BigDecimal cantidadComprada;
	private BigDecimal precioUnidad;
	private ZonedDateTime fechaDesde;
	private ZonedDateTime fechaHasta;

	public HistorialPrecioDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setProveedorProducto(UtilUUID.obtenerValorDefecto());
		setCantidadComprada(UtilBigDecimal.obtenerValorDefecto());
		setPrecioUnidad(UtilBigDecimal.obtenerValorDefecto());
		setFechaDesde(UtilFecha.obtenerFechaHoraActualZona());
		setFechaHasta(UtilFecha.obtenerFechaHoraActualZona());
	}

	public HistorialPrecioDTO(final UUID id, final UUID proveedorProducto, final BigDecimal cantidadComprada,
	                          final BigDecimal precioUnidad, final ZonedDateTime fechaDesde, final ZonedDateTime fechaHasta) {
		setId(id);
		setProveedorProducto(proveedorProducto);
		setCantidadComprada(cantidadComprada);
		setPrecioUnidad(precioUnidad);
		setFechaDesde(fechaDesde);
		setFechaHasta(fechaHasta);
	}

	public static HistorialPrecioDTO obtenerValorDefecto(final HistorialPrecioDTO historial) {
		return UtilObjeto.getInstance().obtenerValorDefecto(historial, new HistorialPrecioDTO());
	}

	public UUID getId() {
		return id;
	}

	public HistorialPrecioDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public UUID getProveedorProducto() {
		return proveedorProducto;
	}

	public HistorialPrecioDTO setProveedorProducto(final UUID proveedorProducto) {
		this.proveedorProducto = UtilUUID.obtenerValorDefecto(proveedorProducto);
		return this;
	}

	public BigDecimal getCantidadComprada() {
		return cantidadComprada;
	}

	public HistorialPrecioDTO setCantidadComprada(final BigDecimal cantidadComprada) {
		this.cantidadComprada = UtilBigDecimal.obtenerValorDefecto(cantidadComprada);
		return this;
	}

	public BigDecimal getPrecioUnidad() {
		return precioUnidad;
	}

	public HistorialPrecioDTO setPrecioUnidad(final BigDecimal precioUnidad) {
		this.precioUnidad = UtilBigDecimal.obtenerValorDefecto(precioUnidad);
		return this;
	}

	public ZonedDateTime getFechaDesde() {
		return fechaDesde;
	}

	public HistorialPrecioDTO setFechaDesde(final ZonedDateTime fechaDesde) {
		this.fechaDesde = UtilFecha.obtenerValorDefecto(fechaDesde);
		return this;
	}

	public ZonedDateTime getFechaHasta() {
		return fechaHasta;
	}

	public HistorialPrecioDTO setFechaHasta(final ZonedDateTime fechaHasta) {
		this.fechaHasta = UtilFecha.obtenerValorDefecto(fechaHasta);
		return this;
	}
}