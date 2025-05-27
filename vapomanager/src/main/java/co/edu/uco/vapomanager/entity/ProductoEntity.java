package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ProductoEntity {

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
		setMarca(new MarcaEntity());
		setSabor(new SaborEntity());
		setEdicion(new EdicionEntity());
		setPrecioCompra(BigDecimal.ZERO);
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
		setCategoria(new CategoriaEntity());
		setEstadoProducto(new EstadoProductoEntity());
	}

	public ProductoEntity(final UUID id, final MarcaEntity marca, final SaborEntity sabor, final EdicionEntity edicion,
	                      final BigDecimal precioCompra, final String descripcion, final CategoriaEntity categoria,
	                      final EstadoProductoEntity estadoProducto) {
		setId(id);
		setMarca(marca);
		setSabor(sabor);
		setEdicion(edicion);
		setPrecioCompra(precioCompra);
		setDescripcion(descripcion);
		setCategoria(categoria);
		setEstadoProducto(estadoProducto);
	}

	public static ProductoEntity obtenerValorDefecto(final ProductoEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new ProductoEntity());
	}

	public static ProductoEntity obtenerValorDefecto() {
		return new ProductoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(final MarcaEntity marca) {
		this.marca = MarcaEntity.obtenerValorDefecto(marca);
	}

	public SaborEntity getSabor() {
		return sabor;
	}

	public void setSabor(final SaborEntity sabor) {
		this.sabor = SaborEntity.obtenerValorDefecto(sabor);
	}

	public EdicionEntity getEdicion() {
		return edicion;
	}

	public void setEdicion(final EdicionEntity edicion) {
		this.edicion = EdicionEntity.obtenerValorDefecto(edicion);
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(final BigDecimal precioCompra) {
		this.precioCompra = UtilBigDecimal.obtenerValorDefecto(precioCompra);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(final CategoriaEntity categoria) {
		this.categoria = CategoriaEntity.obtenerValorDefecto(categoria);
	}

	public EstadoProductoEntity getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(final EstadoProductoEntity estadoProducto) {
		this.estadoProducto = EstadoProductoEntity.obtenerValorDefecto(estadoProducto);
	}
}