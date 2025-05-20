package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;

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

    public PedidoEntity(UUID id, String numeroPedido, ClienteEntity cliente, ZonedDateTime fechaPedido, BigDecimal valorTotalPedido, BigDecimal gananciaPedido) {
        setId(id);
        setNumeroPedido(numeroPedido);
        setCliente(cliente);
        setFechaPedido(fechaPedido);
        setValorTotalPedido(valorTotalPedido);
        setGananciaPedido(gananciaPedido);
    }

    public static PedidoEntity create(UUID id, String numeroPedido, ClienteEntity cliente, ZonedDateTime fechaPedido, BigDecimal valorTotalPedido, BigDecimal gananciaPedido) {
        return new PedidoEntity(id, numeroPedido, cliente, fechaPedido, valorTotalPedido, gananciaPedido);
    }

    public UUID getId() {
        return id;
    }

    public PedidoEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public PedidoEntity setNumeroPedido(String numeroPedido) {
        this.numeroPedido = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(numeroPedido);
        return this;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public PedidoEntity setCliente(ClienteEntity cliente) {
        this.cliente = cliente != null ? cliente : new ClienteEntity();
        return this;
    }

    public ZonedDateTime getFechaPedido() {
        return fechaPedido;
    }

    public PedidoEntity setFechaPedido(ZonedDateTime fechaPedido) {
        this.fechaPedido = UtilFecha.obtenerValorDefecto(fechaPedido);
        return this;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public PedidoEntity setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = UtilObjeto.getInstance().obtenerValorDefecto(valorTotalPedido, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getGananciaPedido() {
        return gananciaPedido;
    }

    public PedidoEntity setGananciaPedido(BigDecimal gananciaPedido) {
        this.gananciaPedido = UtilObjeto.getInstance().obtenerValorDefecto(gananciaPedido, BigDecimal.ZERO);
        return this;
    }
}