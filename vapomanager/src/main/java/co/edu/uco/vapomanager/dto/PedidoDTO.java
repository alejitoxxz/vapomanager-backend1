package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;
import co.edu.uco.vapomanager.dto.ClienteDTO;

public final class PedidoDTO {

	private UUID id;
	private String numeroPedido;
	private ClienteDTO cliente;
	private ZonedDateTime fechaPedido;
	private BigDecimal valorTotalPedido;
	private BigDecimal gananciaPedido;

	public PedidoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNumeroPedido(UtilTexto.getInstance().obtenerValorDefecto());
		setCliente(new ClienteDTO());
		setFechaPedido(UtilFecha.obtenerFechaHoraActualZona());
		setValorTotalPedido(BigDecimal.ZERO);
		setGananciaPedido(BigDecimal.ZERO);
	}

	public PedidoDTO(final UUID id, final String numeroPedido, final ClienteDTO cliente,
	                 final ZonedDateTime fechaPedido, final BigDecimal valorTotalPedido,
	                 final BigDecimal gananciaPedido) {
		setId(id);
		setNumeroPedido(numeroPedido);
		setCliente(cliente);
		setFechaPedido(fechaPedido);
		setValorTotalPedido(valorTotalPedido);
		setGananciaPedido(gananciaPedido);
	}

	public static PedidoDTO obtenerValorDefecto(final PedidoDTO pedido) {
		return UtilObjeto.getInstance().obtenerValorDefecto(pedido, new PedidoDTO());
	}

	public UUID getId() {
		return id;
	}

	public PedidoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public PedidoDTO setNumeroPedido(final String numeroPedido) {
		this.numeroPedido = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(numeroPedido);
		return this;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public PedidoDTO setCliente(final ClienteDTO cliente) {
		this.cliente = ClienteDTO.obtenerValorDefecto(cliente);
		return this;
	}

	public ZonedDateTime getFechaPedido() {
		return fechaPedido;
	}

	public PedidoDTO setFechaPedido(final ZonedDateTime fechaPedido) {
		this.fechaPedido = UtilFecha.obtenerValorDefecto(fechaPedido);
		return this;
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public PedidoDTO setValorTotalPedido(final BigDecimal valorTotalPedido) {
		this.valorTotalPedido = UtilObjeto.getInstance().obtenerValorDefecto(valorTotalPedido, BigDecimal.ZERO);
		return this;
	}

	public BigDecimal getGananciaPedido() {
		return gananciaPedido;
	}

	public PedidoDTO setGananciaPedido(final BigDecimal gananciaPedido) {
		this.gananciaPedido = UtilObjeto.getInstance().obtenerValorDefecto(gananciaPedido, BigDecimal.ZERO);
		return this;
	}
}