package co.edu.uco.vapomanager.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class InventarioEntity {

    public static final InventarioEntity DEFAULT_OBJECT = new InventarioEntity();

    private UUID id;
    private int cantidadDisponible;
    private int cantidadMinima;
    private ZonedDateTime fechaIngreso;
    private ProductoEntity producto;

    public InventarioEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setCantidadDisponible(0);
        setCantidadMinima(0);
        setFechaIngreso(UtilFecha.obtenerFechaHoraActualZona()); 
        setProducto(ProductoEntity.DEFAULT_OBJECT);
    }

    public InventarioEntity(UUID id, int cantidadDisponible, int cantidadMinima, ZonedDateTime fechaIngreso, ProductoEntity producto) {
        setId(id);
        setCantidadDisponible(cantidadDisponible);
        setCantidadMinima(cantidadMinima);
        setFechaIngreso(fechaIngreso);
        setProducto(producto);
    }

    public static InventarioEntity create(UUID id, int cantidadDisponible, int cantidadMinima, ZonedDateTime fechaIngreso, ProductoEntity producto) {
        return new InventarioEntity(id, cantidadDisponible, cantidadMinima, fechaIngreso, producto);
    }

    public UUID getId() {
        return id;
    }

    public InventarioEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public InventarioEntity setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public InventarioEntity setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
        return this;
    }

    public ZonedDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public InventarioEntity setFechaIngreso(ZonedDateTime fechaIngreso) {
        this.fechaIngreso = UtilFecha.obtenerValorDefecto(fechaIngreso);
        return this;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public InventarioEntity setProducto(ProductoEntity producto) {
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, ProductoEntity.DEFAULT_OBJECT);
        return this;
    }
}
