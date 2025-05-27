package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBigDecimal;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class HistorialPrecioEntity {

	private UUID id;
	private UUID proveedorProducto;
	private BigDecimal cantidadComprada;
	private BigDecimal precioUnidad;
	private ZonedDateTime fechaDesde;
	private ZonedDateTime fechaHasta;

	public HistorialPrecioEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setProveedorProducto(UtilUUID.obtenerValorDefecto());
		setCantidadComprada(UtilBigDecimal.obtenerValorDefecto());
		setPrecioUnidad(UtilBigDecimal.obtenerValorDefecto());
		setFechaDesde(UtilFecha.obtenerFechaHoraActualZona());
		setFechaHasta(UtilFecha.obtenerFechaHoraActualZona());
	}

	public HistorialPrecioEntity(final UUID id, final UUID proveedorProducto, final BigDecimal cantidadComprada,
	                             final BigDecimal precioUnidad, final ZonedDateTime fechaDesde, final ZonedDateTime fechaHasta) {
		setId(id);
		setProveedorProducto(proveedorProducto);
		setCantidadComprada(cantidadComprada);
		setPrecioUnidad(precioUnidad);
		setFechaDesde(fechaDesde);
		setFechaHasta(fechaHasta);
	}

	public static HistorialPrecioEntity obtenerValorDefecto(final HistorialPrecioEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new HistorialPrecioEntity());
	}

	public static HistorialPrecioEntity obtenerValorDefecto() {
		return new HistorialPrecioEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public UUID getProveedorProducto() {
		return proveedorProducto;
	}

	public void setProveedorProducto(final UUID proveedorProducto) {
		this.proveedorProducto = UtilUUID.obtenerValorDefecto(proveedorProducto);
	}

	public BigDecimal getCantidadComprada() {
		return cantidadComprada;
	}

	public void setCantidadComprada(final BigDecimal cantidadComprada) {
		this.cantidadComprada = UtilBigDecimal.obtenerValorDefecto(cantidadComprada);
	}

	public BigDecimal getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(final BigDecimal precioUnidad) {
		this.precioUnidad = UtilBigDecimal.obtenerValorDefecto(precioUnidad);
	}

	public ZonedDateTime getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(final ZonedDateTime fechaDesde) {
		this.fechaDesde = UtilFecha.obtenerValorDefecto(fechaDesde);
	}

	public ZonedDateTime getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(final ZonedDateTime fechaHasta) {
		this.fechaHasta = UtilFecha.obtenerValorDefecto(fechaHasta);
	}
}