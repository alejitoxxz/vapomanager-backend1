package co.edu.uco.vapomanager.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class InventarioDTO {

    public static final InventarioDTO DEFAULT_OBJECT = new InventarioDTO();

    private UUID id;
    private int cantidadDisponible;
    private int cantidadMinima;
    private ZonedDateTime fechaIngreso;
    private ProductoDTO producto;

    public static InventarioDTO obtenerValorDefecto(final InventarioDTO inventario) {
        return UtilObjeto.getInstance().obtenerValorDefecto(inventario, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public InventarioDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public InventarioDTO setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public InventarioDTO setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
        return this;
    }

    public ZonedDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public InventarioDTO setFechaIngreso(ZonedDateTime fechaIngreso) {
        this.fechaIngreso = UtilFecha.obtenerValorDefecto(fechaIngreso);
        return this;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public InventarioDTO setProducto(ProductoDTO producto) {
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, ProductoDTO.DEFAULT_OBJECT);
        return this;
    }
}