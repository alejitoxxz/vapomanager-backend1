package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBigDecimal;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProductoEntity {

    public static final ProductoEntity DEFAULT_OBJECT = new ProductoEntity();

    private UUID id;
    private MarcaEntity marca;
    private SaborEntity sabor;
    private EdicionEntity edicion;
    private BigDecimal precioCompra;
    private String descripcion;
    private CategoriaEntity categoria;
    private EstadoProductoEntity estadoProducto;

    public ProductoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setMarca(MarcaEntity.DEFAULT_OBJECT);
        setSabor(SaborEntity.DEFAULT_OBJECT);
        setEdicion(EdicionEntity.DEFAULT_OBJECT);
        setPrecioCompra(BigDecimal.ZERO);
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
        setCategoria(CategoriaEntity.DEFAULT_OBJECT);
        setEstadoProducto(EstadoProductoEntity.DEFAULT_OBJECT);
    }

    public ProductoEntity(UUID id, MarcaEntity marca, SaborEntity sabor, EdicionEntity edicion, BigDecimal precioCompra, 
                          String descripcion, CategoriaEntity categoria, EstadoProductoEntity estadoProducto) {
        setId(id);
        setMarca(marca);
        setSabor(sabor);
        setEdicion(edicion);
        setPrecioCompra(precioCompra);
        setDescripcion(descripcion);
        setCategoria(categoria);
        setEstadoProducto(estadoProducto);
    }

    public static ProductoEntity create(UUID id, MarcaEntity marca, SaborEntity sabor, EdicionEntity edicion, 
                                        BigDecimal precioCompra, String descripcion, CategoriaEntity categoria, 
                                        EstadoProductoEntity estadoProducto) {
        return new ProductoEntity(id, marca, sabor, edicion, precioCompra, descripcion, categoria, estadoProducto);
    }

    public UUID getId() {
        return id;
    }

    public ProductoEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public MarcaEntity getMarca() {
        return marca;
    }

    public ProductoEntity setMarca(MarcaEntity marca) {
        this.marca = UtilObjeto.getInstance().obtenerValorDefecto(marca, MarcaEntity.DEFAULT_OBJECT);
        return this;
    }

    public SaborEntity getSabor() {
        return sabor;
    }

    public ProductoEntity setSabor(SaborEntity sabor) {
        this.sabor = UtilObjeto.getInstance().obtenerValorDefecto(sabor, SaborEntity.DEFAULT_OBJECT);
        return this;
    }

    public EdicionEntity getEdicion() {
        return edicion;
    }

    public ProductoEntity setEdicion(EdicionEntity edicion) {
        this.edicion = UtilObjeto.getInstance().obtenerValorDefecto(edicion, EdicionEntity.DEFAULT_OBJECT);
        return this;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public ProductoEntity setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = UtilBigDecimal.obtenerValorDefecto(precioCompra);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ProductoEntity setDescripcion(String descripcion) {
        this.descripcion = UtilTexto.getInstance().obtenerValorDefecto(descripcion);
        return this;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public ProductoEntity setCategoria(CategoriaEntity categoria) {
        this.categoria = UtilObjeto.getInstance().obtenerValorDefecto(categoria, CategoriaEntity.DEFAULT_OBJECT);
        return this;
    }

    public EstadoProductoEntity getEstadoProducto() {
        return estadoProducto;
    }

    public ProductoEntity setEstadoProducto(EstadoProductoEntity estadoProducto) {
        this.estadoProducto = UtilObjeto.getInstance().obtenerValorDefecto(estadoProducto, EstadoProductoEntity.DEFAULT_OBJECT);
        return this;
    }
}
