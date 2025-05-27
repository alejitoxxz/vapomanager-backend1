package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class PedidoEntity {

	private UUID id;
	private String numeroPedido;
	private ClienteEntity cliente;
	private ZonedDateTime fechaPedido;
	private BigDecimal valorTotalPedido;
	private BigDecimal gananciaPedido;

	public PedidoEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNumeroPedido(UtilTexto.getInstance().obtenerValorDefecto());
		setCliente(new ClienteEntity());
		setFechaPedido(UtilFecha.obtenerFechaHoraActualZona());
		setValorTotalPedido(BigDecimal.ZERO);
		setGananciaPedido(BigDecimal.ZERO);
	}

	public PedidoEntity(final UUID id, final String numeroPedido, final ClienteEntity cliente,
	                    final ZonedDateTime fechaPedido, final BigDecimal valorTotalPedido,
	                    final BigDecimal gananciaPedido) {
		setId(id);
		setNumeroPedido(numeroPedido);
		setCliente(cliente);
		setFechaPedido(fechaPedido);
		setValorTotalPedido(valorTotalPedido);
		setGananciaPedido(gananciaPedido);
	}

	public static PedidoEntity obtenerValorDefecto(final PedidoEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new PedidoEntity());
	}

	public static PedidoEntity obtenerValorDefecto() {
		return new PedidoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(final String numeroPedido) {
		this.numeroPedido = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(numeroPedido);
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(final ClienteEntity cliente) {
		this.cliente = ClienteEntity.obtenerValorDefecto(cliente);
	}

	public ZonedDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(final ZonedDateTime fechaPedido) {
		this.fechaPedido = UtilFecha.obtenerValorDefecto(fechaPedido);
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(final BigDecimal valorTotalPedido) {
		this.valorTotalPedido = UtilObjeto.getInstance().obtenerValorDefecto(valorTotalPedido, BigDecimal.ZERO);
	}

	public BigDecimal getGananciaPedido() {
		return gananciaPedido;
	}

	public void setGananciaPedido(final BigDecimal gananciaPedido) {
		this.gananciaPedido = UtilObjeto.getInstance().obtenerValorDefecto(gananciaPedido, BigDecimal.ZERO);
	}
}