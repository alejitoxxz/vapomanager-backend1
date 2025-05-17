package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;

public final class ProveedorProductoEntity {

    public static final ProveedorProductoEntity DEFAULT_OBJECT = new ProveedorProductoEntity();

    private UUID id;
    private UUID proveedor;
    private UUID producto;

    public ProveedorProductoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setProveedor(UtilUUID.obtenerValorDefecto());
        setProducto(UtilUUID.obtenerValorDefecto());
    }

    public ProveedorProductoEntity(UUID id, UUID proveedor, UUID producto) {
        setId(id);
        setProveedor(proveedor);
        setProducto(producto);
    }

    public static ProveedorProductoEntity create(UUID id, UUID proveedor, UUID producto) {
        return new ProveedorProductoEntity(id, proveedor, producto);
    }

    public static ProveedorProductoEntity obtenerValorDefecto(final ProveedorProductoEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public ProveedorProductoEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public UUID getProveedor() {
        return proveedor;
    }

    public ProveedorProductoEntity setProveedor(UUID proveedor) {
        this.proveedor = UtilUUID.obtenerValorDefecto(proveedor);
        return this;
    }

    public UUID getProducto() {
        return producto;
    }

    public ProveedorProductoEntity setProducto(UUID producto) {
        this.producto = UtilUUID.obtenerValorDefecto(producto);
        return this;
    }
}