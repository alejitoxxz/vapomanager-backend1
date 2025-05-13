package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class HistorialPrecioDTO {

    public static final HistorialPrecioDTO DEFAULT_OBJECT = new HistorialPrecioDTO();

    private UUID id;
    private UUID proveedorProducto;
    private BigDecimal cantidadComprada;
    private BigDecimal precioUnidad;
    private ZonedDateTime fechaDesde;
    private ZonedDateTime fechaHasta;

    public static HistorialPrecioDTO obtenerValorDefecto(final HistorialPrecioDTO historialPrecio) {
        return UtilObjeto.getInstance().obtenerValorDefecto(historialPrecio, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public HistorialPrecioDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public UUID getProveedorProducto() {
        return proveedorProducto;
    }

    public HistorialPrecioDTO setProveedorProducto(UUID proveedorProducto) {
        this.proveedorProducto = UtilUUID.obtenerValorDefecto(proveedorProducto);
        return this;
    }

    public BigDecimal getCantidadComprada() {
        return cantidadComprada;
    }

    public HistorialPrecioDTO setCantidadComprada(BigDecimal cantidadComprada) {
        this.cantidadComprada = UtilBigDecimal.obtenerValorDefecto(cantidadComprada);
        return this;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public HistorialPrecioDTO setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = UtilBigDecimal.obtenerValorDefecto(precioUnidad);
        return this;
    }

    public ZonedDateTime getFechaDesde() {
        return fechaDesde;
    }

    public HistorialPrecioDTO setFechaDesde(ZonedDateTime fechaDesde) {
        this.fechaDesde = UtilFecha.obtenerValorDefecto(fechaDesde);
        return this;
    }

    public ZonedDateTime getFechaHasta() {
        return fechaHasta;
    }

    public HistorialPrecioDTO setFechaHasta(ZonedDateTime fechaHasta) {
        this.fechaHasta = UtilFecha.obtenerValorDefecto(fechaHasta);
        return this;
    }
}