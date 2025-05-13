package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProductoDTO {

    public static final ProductoDTO DEFAULT_OBJECT = new ProductoDTO();

    private UUID id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private BigDecimal ganancia;

    public static ProductoDTO obtenerValorDefecto(final ProductoDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public ProductoDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public ProductoDTO setNombre(String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ProductoDTO setDescripcion(String descripcion) {
        this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
        return this;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public ProductoDTO setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = UtilObjeto.getInstance().obtenerValorDefecto(precioCompra, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public ProductoDTO setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = UtilObjeto.getInstance().obtenerValorDefecto(precioVenta, BigDecimal.ZERO);
        return this;
    }

    public BigDecimal getGanancia() {
        return ganancia;
    }

    public ProductoDTO setGanancia(BigDecimal ganancia) {
        this.ganancia = UtilObjeto.getInstance().obtenerValorDefecto(ganancia, BigDecimal.ZERO);
        return this;
    }
}