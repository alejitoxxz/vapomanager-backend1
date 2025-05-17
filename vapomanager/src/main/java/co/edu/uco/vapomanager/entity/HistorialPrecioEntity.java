package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBigDecimal;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class HistorialPrecioEntity {

    public static final HistorialPrecioEntity DEFAULT_OBJECT = new HistorialPrecioEntity();

    private UUID id;
    private UUID proveedorProducto;
    private BigDecimal cantidadComprada;
    private BigDecimal precioUnidad;
    private ZonedDateTime fechaDesde;
    private ZonedDateTime fechaHasta;

    public HistorialPrecioEntity() {
        setId(UtilUUID.obtenerValorDefecto(null));
        setProveedorProducto(UtilUUID.obtenerValorDefecto(null));
        setCantidadComprada(UtilBigDecimal.obtenerValorDefecto(null));
        setPrecioUnidad(UtilBigDecimal.obtenerValorDefecto(null));
        setFechaDesde(UtilFecha.obtenerFechaHoraActualZona());
        setFechaHasta(UtilFecha.obtenerFechaHoraActualZona());
    }

    public HistorialPrecioEntity(UUID id, UUID proveedorProducto, BigDecimal cantidadComprada,
                                BigDecimal precioUnidad, ZonedDateTime fechaDesde, ZonedDateTime fechaHasta) {
        setId(id);
        setProveedorProducto(proveedorProducto);
        setCantidadComprada(cantidadComprada);
        setPrecioUnidad(precioUnidad);
        setFechaDesde(fechaDesde);
        setFechaHasta(fechaHasta);
    }

    public static HistorialPrecioEntity create(UUID id, UUID proveedorProducto, BigDecimal cantidadComprada,
                                               BigDecimal precioUnidad, ZonedDateTime fechaDesde, ZonedDateTime fechaHasta) {
        return new HistorialPrecioEntity(id, proveedorProducto, cantidadComprada, precioUnidad, fechaDesde, fechaHasta);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public UUID getProveedorProducto() {
        return proveedorProducto;
    }

    public void setProveedorProducto(UUID proveedorProducto) {
        this.proveedorProducto = UtilUUID.obtenerValorDefecto(proveedorProducto);
    }

    public BigDecimal getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(BigDecimal cantidadComprada) {
        this.cantidadComprada = UtilBigDecimal.obtenerValorDefecto(cantidadComprada);
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = UtilBigDecimal.obtenerValorDefecto(precioUnidad);
    }

    public ZonedDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(ZonedDateTime fechaDesde) {
        this.fechaDesde = UtilFecha.obtenerValorDefecto(fechaDesde);
    }

    public ZonedDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(ZonedDateTime fechaHasta) {
        this.fechaHasta = UtilFecha.obtenerValorDefecto(fechaHasta);
    }
}