package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class PedidoProductoEntity {

    public static final PedidoProductoEntity DEFAULT_OBJECT = new PedidoProductoEntity();

    private UUID id;
    private ProductoEntity producto;
    private int cantidad;
    private PedidoEntity pedido;
    private BigDecimal precioVenta;
    private BigDecimal gananciaUnidad;
    private BigDecimal subtotal;

    public PedidoProductoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setProducto(ProductoEntity.DEFAULT_OBJECT);
        setCantidad(0);
        setPedido(PedidoEntity.DEFAULT_OBJECT);
        setPrecioVenta(BigDecimal.ZERO);
        setGananciaUnidad(BigDecimal.ZERO);
        setSubtotal(BigDecimal.ZERO);
    }

    public PedidoProductoEntity(UUID id, ProductoEntity producto, int cantidad, PedidoEntity pedido, BigDecimal precioVenta, BigDecimal gananciaUnidad, BigDecimal subtotal) {
        setId(id);
        setProducto(producto);
        setCantidad(cantidad);
        setPedido(pedido);
        setPrecioVenta(precioVenta);
        setGananciaUnidad(gananciaUnidad);
        setSubtotal(subtotal);
    }

    public static PedidoProductoEntity create(UUID id, ProductoEntity producto, int cantidad, PedidoEntity pedido, BigDecimal precioVenta, BigDecimal gananciaUnidad, BigDecimal subtotal) {
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
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, ProductoEntity.DEFAULT_OBJECT);
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
        this.pedido = UtilObjeto.getInstance().obtenerValorDefecto(pedido, PedidoEntity.DEFAULT_OBJECT);
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