package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class PedidoProductoDTO {

	private UUID id;
	private ProductoDTO producto;
	private int cantidad;
	private PedidoDTO pedido;
	private BigDecimal precioVenta;
	private BigDecimal gananciaUnidad;
	private BigDecimal subtotal;

	public PedidoProductoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setProducto(new ProductoDTO());
		setCantidad(0);
		setPedido(new PedidoDTO());
		setPrecioVenta(BigDecimal.ZERO);
		setGananciaUnidad(BigDecimal.ZERO);
		setSubtotal(BigDecimal.ZERO);
	}

	public PedidoProductoDTO(final UUID id, final ProductoDTO producto, final int cantidad,
	                         final PedidoDTO pedido, final BigDecimal precioVenta,
	                         final BigDecimal gananciaUnidad, final BigDecimal subtotal) {
		setId(id);
		setProducto(producto);
		setCantidad(cantidad);
		setPedido(pedido);
		setPrecioVenta(precioVenta);
		setGananciaUnidad(gananciaUnidad);
		setSubtotal(subtotal);
	}

	public static PedidoProductoDTO obtenerValorDefecto(final PedidoProductoDTO pedidoProducto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(pedidoProducto, new PedidoProductoDTO());
	}

	public UUID getId() {
		return id;
	}

	public PedidoProductoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public PedidoProductoDTO setProducto(final ProductoDTO producto) {
		this.producto = ProductoDTO.obtenerValorDefecto(producto);
		return this;
	}

	public int getCantidad() {
		return cantidad;
	}

	public PedidoProductoDTO setCantidad(final int cantidad) {
		this.cantidad = cantidad;
		return this;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public PedidoProductoDTO setPedido(final PedidoDTO pedido) {
		this.pedido = PedidoDTO.obtenerValorDefecto(pedido);
		return this;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public PedidoProductoDTO setPrecioVenta(final BigDecimal precioVenta) {
		this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
		return this;
	}

	public BigDecimal getGananciaUnidad() {
		return gananciaUnidad;
	}

	public PedidoProductoDTO setGananciaUnidad(final BigDecimal gananciaUnidad) {
		this.gananciaUnidad = UtilObjeto.getInstance().obtenerValorDefecto(gananciaUnidad, BigDecimal.ZERO);
		return this;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public PedidoProductoDTO setSubtotal(final BigDecimal subtotal) {
		this.subtotal = UtilObjeto.getInstance().obtenerValorDefecto(subtotal, BigDecimal.ZERO);
		return this;
	}
}