package co.edu.uco.vapomanager.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class InventarioEntity {

	private UUID id;
	private int cantidadDisponible;
	private int cantidadMinima;
	private ZonedDateTime fechaIngreso;
	private ProductoEntity producto;

	public InventarioEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setCantidadDisponible(0);
		setCantidadMinima(0);
		setFechaIngreso(UtilFecha.obtenerFechaHoraActualZona());
		setProducto(new ProductoEntity());
	}

	public InventarioEntity(final UUID id, final int cantidadDisponible, final int cantidadMinima,
	                        final ZonedDateTime fechaIngreso, final ProductoEntity producto) {
		setId(id);
		setCantidadDisponible(cantidadDisponible);
		setCantidadMinima(cantidadMinima);
		setFechaIngreso(fechaIngreso);
		setProducto(producto);
	}

	public static InventarioEntity obtenerValorDefecto(final InventarioEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new InventarioEntity());
	}

	public static InventarioEntity obtenerValorDefecto() {
		return new InventarioEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(final int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(final int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public ZonedDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(final ZonedDateTime fechaIngreso) {
		this.fechaIngreso = UtilFecha.obtenerValorDefecto(fechaIngreso);
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(final ProductoEntity producto) {
		this.producto = ProductoEntity.obtenerValorDefecto(producto);
	}
}