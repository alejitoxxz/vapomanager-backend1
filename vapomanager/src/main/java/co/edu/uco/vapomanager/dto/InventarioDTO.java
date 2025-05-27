package co.edu.uco.vapomanager.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class InventarioDTO {

	private UUID id;
	private int cantidadDisponible;
	private int cantidadMinima;
	private ZonedDateTime fechaIngreso;
	private ProductoDTO producto;

	public InventarioDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setCantidadDisponible(0);
		setCantidadMinima(0);
		setFechaIngreso(UtilFecha.obtenerFechaHoraActualZona());
		setProducto(new ProductoDTO());
	}

	public InventarioDTO(final UUID id, final int cantidadDisponible, final int cantidadMinima,
	                     final ZonedDateTime fechaIngreso, final ProductoDTO producto) {
		setId(id);
		setCantidadDisponible(cantidadDisponible);
		setCantidadMinima(cantidadMinima);
		setFechaIngreso(fechaIngreso);
		setProducto(producto);
	}

	public static InventarioDTO obtenerValorDefecto(final InventarioDTO inventario) {
		return UtilObjeto.getInstance().obtenerValorDefecto(inventario, new InventarioDTO());
	}

	public UUID getId() {
		return id;
	}

	public InventarioDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public InventarioDTO setCantidadDisponible(final int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
		return this;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public InventarioDTO setCantidadMinima(final int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
		return this;
	}

	public ZonedDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public InventarioDTO setFechaIngreso(final ZonedDateTime fechaIngreso) {
		this.fechaIngreso = UtilFecha.obtenerValorDefecto(fechaIngreso);
		return this;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public InventarioDTO setProducto(final ProductoDTO producto) {
		this.producto = ProductoDTO.obtenerValorDefecto(producto);
		return this;
	}
}