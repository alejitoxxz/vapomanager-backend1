package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProveedorProductoEntity {

	private UUID id;
	private UUID proveedor;
	private UUID producto;

	public ProveedorProductoEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setProveedor(UtilUUID.obtenerValorDefecto());
		setProducto(UtilUUID.obtenerValorDefecto());
	}

	public ProveedorProductoEntity(final UUID id, final UUID proveedor, final UUID producto) {
		setId(id);
		setProveedor(proveedor);
		setProducto(producto);
	}

	public static ProveedorProductoEntity obtenerValorDefecto(final ProveedorProductoEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new ProveedorProductoEntity());
	}

	public static ProveedorProductoEntity obtenerValorDefecto() {
		return new ProveedorProductoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public UUID getProveedor() {
		return proveedor;
	}

	public void setProveedor(final UUID proveedor) {
		this.proveedor = UtilUUID.obtenerValorDefecto(proveedor);
	}

	public UUID getProducto() {
		return producto;
	}

	public void setProducto(final UUID producto) {
		this.producto = UtilUUID.obtenerValorDefecto(producto);
	}
}