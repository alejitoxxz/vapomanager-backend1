package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProveedorProductoDTO {

	private UUID id;
	private UUID proveedor;
	private UUID producto;

	public ProveedorProductoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setProveedor(UtilUUID.obtenerValorDefecto());
		setProducto(UtilUUID.obtenerValorDefecto());
	}

	public ProveedorProductoDTO(final UUID id, final UUID proveedor, final UUID producto) {
		setId(id);
		setProveedor(proveedor);
		setProducto(producto);
	}

	public static ProveedorProductoDTO obtenerValorDefecto(final ProveedorProductoDTO dto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(dto, new ProveedorProductoDTO());
	}

	public UUID getId() {
		return id;
	}

	public ProveedorProductoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public UUID getProveedor() {
		return proveedor;
	}

	public ProveedorProductoDTO setProveedor(final UUID proveedor) {
		this.proveedor = UtilUUID.obtenerValorDefecto(proveedor);
		return this;
	}

	public UUID getProducto() {
		return producto;
	}

	public ProveedorProductoDTO setProducto(final UUID producto) {
		this.producto = UtilUUID.obtenerValorDefecto(producto);
		return this;
	}
}