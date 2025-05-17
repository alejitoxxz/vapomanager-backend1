package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class PedidoProductoEntity {

    private UUID id;
    private ProductoEntity producto;
    private int cantidad;
    private PedidoEntity pedido;
    private BigDecimal precioVenta;
    private BigDecimal gananciaUnidad;
    private BigDecimal subtotal;

    public PedidoProductoEntity() {
        this.id = UtilUUID.obtenerValorDefecto();
        this.producto = null; // se evita DEFAULT_OBJECT
        this.cantidad = 0;
        this.pedido = null; // se evita DEFAULT_OBJECT
        this.precioVenta = BigDecimal.ZERO;
        this.gananciaUnidad = BigDecimal.ZERO;
        this.subtotal = BigDecimal.ZERO;
    }

    public PedidoProductoEntity(UUID id, ProductoEntity producto, int cantidad, PedidoEntity pedido,
                                 BigDecimal precioVenta, BigDecimal gananciaUnidad, BigDecimal subtotal) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, null);
        this.cantidad = cantidad;
        this.pedido = UtilObjeto.getInstance().obtenerValorDefecto(pedido, null);
        this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
        this.gananciaUnidad = UtilObjeto.getInstance().obtenerValorDefecto(gananciaUnidad, BigDecimal.ZERO);
        this.subtotal = UtilObjeto.getInstance().obtenerValorDefecto(subtotal, BigDecimal.ZERO);
    }

    public static PedidoProductoEntity create(UUID id, ProductoEntity producto, int cantidad, PedidoEntity pedido,
                                               BigDecimal precioVenta, BigDecimal gananciaUnidad, BigDecimal subtotal) {
        return new PedidoProductoEntity(id, producto, cantidad, pedido, precioVenta, gananciaUnidad, subtotal);
    }

    public UUID getId() {
        return id;
    }

    public PedidoProductoEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public PedidoProductoEntity setProducto(ProductoEntity producto) {
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, null);
        return this;
    }

    public int getCantidad() {
        return cantidad;
    }

    public PedidoProductoEntity setCantidad(int cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public PedidoProductoEntity setPedido(PedidoEntity pedido) {
        this.pedido = UtilObjeto.getInstance().obtenerValorDefecto(pedido, null);
        return this;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public PedidoProductoEntity setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getGananciaUnidad() {
        return gananciaUnidad;
    }

    public PedidoProductoEntity setGananciaUnidad(BigDecimal gananciaUnidad) {
        this.gananciaUnidad = UtilObjeto.getInstance().obtenerValorDefecto(gananciaUnidad, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public PedidoProductoEntity setSubtotal(BigDecimal subtotal) {
        this.subtotal = UtilObjeto.getInstance().obtenerValorDefecto(subtotal, BigDecimal.ZERO);
        return this;
    }
}