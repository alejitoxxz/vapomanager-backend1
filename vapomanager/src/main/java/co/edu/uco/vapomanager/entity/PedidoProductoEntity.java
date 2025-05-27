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
		setId(UtilUUID.obtenerValorDefecto());
		setProducto(new ProductoEntity());
		setCantidad(0);
		setPedido(new PedidoEntity());
		setPrecioVenta(BigDecimal.ZERO);
		setGananciaUnidad(BigDecimal.ZERO);
		setSubtotal(BigDecimal.ZERO);
	}

	public PedidoProductoEntity(final UUID id, final ProductoEntity producto, final int cantidad,
	                            final PedidoEntity pedido, final BigDecimal precioVenta,
	                            final BigDecimal gananciaUnidad, final BigDecimal subtotal) {
		setId(id);
		setProducto(producto);
		setCantidad(cantidad);
		setPedido(pedido);
		setPrecioVenta(precioVenta);
		setGananciaUnidad(gananciaUnidad);
		setSubtotal(subtotal);
	}

	public static PedidoProductoEntity obtenerValorDefecto(final PedidoProductoEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new PedidoProductoEntity());
	}

	public static PedidoProductoEntity obtenerValorDefecto() {
		return new PedidoProductoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(final ProductoEntity producto) {
		this.producto = ProductoEntity.obtenerValorDefecto(producto);
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(final int cantidad) {
		this.cantidad = cantidad;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(final PedidoEntity pedido) {
		this.pedido = PedidoEntity.obtenerValorDefecto(pedido);
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(final BigDecimal precioVenta) {
		this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
	}

	public BigDecimal getGananciaUnidad() {
		return gananciaUnidad;
	}

	public void setGananciaUnidad(final BigDecimal gananciaUnidad) {
		this.gananciaUnidad = UtilObjeto.getInstance().obtenerValorDefecto(gananciaUnidad, BigDecimal.ZERO);
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(final BigDecimal subtotal) {
		this.subtotal = UtilObjeto.getInstance().obtenerValorDefecto(subtotal, BigDecimal.ZERO);
	}
}