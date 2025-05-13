package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;

public final class ProveedorProductoDTO {

    public static final ProveedorProductoDTO DEFAULT_OBJECT = new ProveedorProductoDTO();

    private UUID id;
    private UUID proveedor;
    private UUID producto;

    public ProveedorProductoDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setProveedor(UtilUUID.obtenerValorDefecto());
        setProducto(UtilUUID.obtenerValorDefecto());
    }

    public static ProveedorProductoDTO obtenerValorDefecto(final ProveedorProductoDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public ProveedorProductoDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public UUID getProveedor() {
        return proveedor;
    }

    public ProveedorProductoDTO setProveedor(UUID proveedor) {
        this.proveedor = UtilUUID.obtenerValorDefecto(proveedor);
        return this;
    }

    public UUID getProducto() {
        return producto;
    }

    public ProveedorProductoDTO setProducto(UUID producto) {
        this.producto = UtilUUID.obtenerValorDefecto(producto);
        return this;
    }
}