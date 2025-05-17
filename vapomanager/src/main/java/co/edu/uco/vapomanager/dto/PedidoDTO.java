package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;

public final class PedidoDTO {

    public static final PedidoDTO DEFAULT_OBJECT = new PedidoDTO();

    private UUID id;
    private String numeroPedido;
    private ClienteDTO cliente;
    private ZonedDateTime fechaPedido;
    private BigDecimal valorTotalPedido;
    private BigDecimal gananciaPedido;

    public PedidoDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNumeroPedido(UtilTexto.getInstance().obtenerValorDefecto());
        setCliente(ClienteDTO.DEFAULT_OBJECT);
        setFechaPedido(UtilFecha.obtenerFechaHoraActualZona());
        setValorTotalPedido(BigDecimal.ZERO);
        setGananciaPedido(BigDecimal.ZERO);
    }

    public PedidoDTO(UUID id, String numeroPedido, ClienteDTO cliente, ZonedDateTime fechaPedido, BigDecimal valorTotalPedido, BigDecimal gananciaPedido) {
        setId(id);
        setNumeroPedido(numeroPedido);
        setCliente(cliente);
        setFechaPedido(fechaPedido);
        setValorTotalPedido(valorTotalPedido);
        setGananciaPedido(gananciaPedido);
    }

    public static PedidoDTO obtenerValorDefecto(final PedidoDTO pedido) {
        return UtilObjeto.getInstance().obtenerValorDefecto(pedido, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public PedidoDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public PedidoDTO setNumeroPedido(String numeroPedido) {
        this.numeroPedido = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(numeroPedido);
        return this;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public PedidoDTO setCliente(ClienteDTO cliente) {
        this.cliente = UtilObjeto.getInstance().obtenerValorDefecto(cliente, ClienteDTO.DEFAULT_OBJECT);
        return this;
    }

    public ZonedDateTime getFechaPedido() {
        return fechaPedido;
    }

    public PedidoDTO setFechaPedido(ZonedDateTime fechaPedido) {
        this.fechaPedido = UtilFecha.obtenerValorDefecto(fechaPedido);
        return this;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public PedidoDTO setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = UtilObjeto.getInstance().obtenerValorDefecto(valorTotalPedido, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getGananciaPedido() {
        return gananciaPedido;
    }

    public PedidoDTO setGananciaPedido(BigDecimal gananciaPedido) {
        this.gananciaPedido = UtilObjeto.getInstance().obtenerValorDefecto(gananciaPedido, BigDecimal.ZERO);
        return this;
    }
}