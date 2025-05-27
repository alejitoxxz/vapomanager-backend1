package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;
import co.edu.uco.vapomanager.dto.*;

public final class ProductoDTO {

	private UUID id;
	private MarcaDTO marca;
	private SaborDTO sabor;
	private EdicionDTO edicion;
	private BigDecimal precioCompra;
	private String descripcion;
	private CategoriaDTO categoria;
	private EstadoProductoDTO estadoProducto;

	public ProductoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setMarca(new MarcaDTO());
		setSabor(new SaborDTO());
		setEdicion(new EdicionDTO());
		setPrecioCompra(BigDecimal.ZERO);
		setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
		setCategoria(new CategoriaDTO());
		setEstadoProducto(new EstadoProductoDTO());
	}

	public ProductoDTO(final UUID id, final MarcaDTO marca, final SaborDTO sabor, final EdicionDTO edicion,
	                   final BigDecimal precioCompra, final String descripcion,
	                   final CategoriaDTO categoria, final EstadoProductoDTO estadoProducto) {
		setId(id);
		setMarca(marca);
		setSabor(sabor);
		setEdicion(edicion);
		setPrecioCompra(precioCompra);
		setDescripcion(descripcion);
		setCategoria(categoria);
		setEstadoProducto(estadoProducto);
	}

	public static ProductoDTO obtenerValorDefecto(final ProductoDTO producto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(producto, new ProductoDTO());
	}

	public UUID getId() {
		return id;
	}

	public ProductoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public MarcaDTO getMarca() {
		return marca;
	}

	public ProductoDTO setMarca(final MarcaDTO marca) {
		this.marca = MarcaDTO.obtenerValorDefecto(marca);
		return this;
	}

	public SaborDTO getSabor() {
		return sabor;
	}

	public ProductoDTO setSabor(final SaborDTO sabor) {
		this.sabor = SaborDTO.obtenerValorDefecto(sabor);
		return this;
	}

	public EdicionDTO getEdicion() {
		return edicion;
	}

	public ProductoDTO setEdicion(final EdicionDTO edicion) {
		this.edicion = EdicionDTO.obtenerValorDefecto(edicion);
		return this;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public ProductoDTO setPrecioCompra(final BigDecimal precioCompra) {
		this.precioCompra = UtilBigDecimal.obtenerValorDefecto(precioCompra);
		return this;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ProductoDTO setDescripcion(final String descripcion) {
		this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
		return this;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public ProductoDTO setCategoria(final CategoriaDTO categoria) {
		this.categoria = CategoriaDTO.obtenerValorDefecto(categoria);
		return this;
	}

	public EstadoProductoDTO getEstadoProducto() {
		return estadoProducto;
	}

	public ProductoDTO setEstadoProducto(final EstadoProductoDTO estadoProducto) {
		this.estadoProducto = EstadoProductoDTO.obtenerValorDefecto(estadoProducto);
		return this;
	}
}