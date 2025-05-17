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
        setId(UtilUUID.obtenerValorDefecto(null));
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

    public void setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public ZonedDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(ZonedDateTime fechaIngreso) {
        this.fechaIngreso = UtilFecha.obtenerValorDefecto(fechaIngreso);
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = UtilObjeto.getInstance().obtenerValorDefecto(producto, ProductoEntity.DEFAULT_OBJECT);
    }
}