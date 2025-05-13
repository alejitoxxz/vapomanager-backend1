package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class PedidoProductoDTO {

    public static final PedidoProductoDTO DEFAULT_OBJECT = new PedidoProductoDTO();

    private UUID id;
    private ProductoDTO producto;
    private int cantidad;
    private PedidoDTO pedido;
    private BigDecimal precioVenta;
    private BigDecimal gananciaUnidad;
    private BigDecimal subtotal;

    public static PedidoProductoDTO obtenerValorDefecto(final PedidoProductoDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public PedidoProductoDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public PedidoProductoDTO setProducto(ProductoDTO producto) {
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, ProductoDTO.DEFAULT_OBJECT);
        return this;
    }

    public int getCantidad() {
        return cantidad;
    }

    public PedidoProductoDTO setCantidad(int cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public PedidoProductoDTO setPedido(PedidoDTO pedido) {
        this.pedido = UtilObjeto.getInstance().obtenerValorDefecto(pedido, PedidoDTO.DEFAULT_OBJECT);
        return this;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public PedidoProductoDTO setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getGananciaUnidad() {
        return gananciaUnidad;
    }

    public PedidoProductoDTO setGananciaUnidad(BigDecimal gananciaUnidad) {
        this.gananciaUnidad = UtilObjeto.getInstance().obtenerValorDefecto(gananciaUnidad, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public PedidoProductoDTO setSubtotal(BigDecimal subtotal) {
        this.subtotal = UtilObjeto.getInstance().obtenerValorDefecto(subtotal, BigDecimal.ZERO);
        return this;
    }
}