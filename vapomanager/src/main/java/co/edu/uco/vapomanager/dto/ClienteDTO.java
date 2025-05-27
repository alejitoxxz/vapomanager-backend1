package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ClienteDTO {

	private UUID id;
	private String nombres;
	private String apellidos;
	private String correoElectronico;
	private boolean confirmacionCorreo;
	private String telefono;
	private boolean confirmacionTelefono;
	private boolean estadoCuenta;
	private TipoDocumentoDTO tipoDocumento;
	private int numeroDocumento;
	private String direccion;
	private CiudadDTO ciudad;
	private String descripcionDireccion;
	private LocalDateTime fechaRegistro;
	private BigDecimal totalComprado;

	public ClienteDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombres(UtilTexto.getInstance().obtenerValorDefecto());
		setApellidos(UtilTexto.getInstance().obtenerValorDefecto());
		setCorreoElectronico(UtilCorreo.obtenerValorDefecto(null));
		setConfirmacionCorreo(false);
		setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
		setConfirmacionTelefono(false);
		setEstadoCuenta(false);
		setTipoDocumento(new TipoDocumentoDTO());
		setNumeroDocumento(0);
		setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
		setCiudad(new CiudadDTO());
		setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
		setFechaRegistro(LocalDateTime.now());
		setTotalComprado(BigDecimal.ZERO);
	}

	public ClienteDTO(final UUID id, final String nombres, final String apellidos, final String correoElectronico,
	                  final boolean confirmacionCorreo, final String telefono, final boolean confirmacionTelefono,
	                  final boolean estadoCuenta, final TipoDocumentoDTO tipoDocumento, final int numeroDocumento,
	                  final String direccion, final CiudadDTO ciudad, final String descripcionDireccion,
	                  final LocalDateTime fechaRegistro, final BigDecimal totalComprado) {

		setId(id);
		setNombres(nombres);
		setApellidos(apellidos);
		setCorreoElectronico(correoElectronico);
		setConfirmacionCorreo(confirmacionCorreo);
		setTelefono(telefono);
		setConfirmacionTelefono(confirmacionTelefono);
		setEstadoCuenta(estadoCuenta);
		setTipoDocumento(tipoDocumento);
		setNumeroDocumento(numeroDocumento);
		setDireccion(direccion);
		setCiudad(ciudad);
		setDescripcionDireccion(descripcionDireccion);
		setFechaRegistro(fechaRegistro);
		setTotalComprado(totalComprado);
	}

	public static ClienteDTO obtenerValorDefecto(final ClienteDTO cliente) {
		return UtilObjeto.getInstance().obtenerValorDefecto(cliente, new ClienteDTO());
	}

	public UUID getId() {
		return id;
	}

	public ClienteDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getNombres() {
		return nombres;
	}

	public ClienteDTO setNombres(final String nombres) {
		this.nombres = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombres);
		return this;
	}

	public String getApellidos() {
		return apellidos;
	}

	public ClienteDTO setApellidos(final String apellidos) {
		this.apellidos = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(apellidos);
		return this;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public ClienteDTO setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
		return this;
	}

	public boolean isConfirmacionCorreo() {
		return confirmacionCorreo;
	}

	public ClienteDTO setConfirmacionCorreo(final boolean confirmacionCorreo) {
		this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
		return this;
	}

	public String getTelefono() {
		return telefono;
	}

	public ClienteDTO setTelefono(final String telefono) {
		this.telefono = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(telefono);
		return this;
	}

	public boolean isConfirmacionTelefono() {
		return confirmacionTelefono;
	}

	public ClienteDTO setConfirmacionTelefono(final boolean confirmacionTelefono) {
		this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
		return this;
	}

	public boolean isEstadoCuenta() {
		return estadoCuenta;
	}

	public ClienteDTO setEstadoCuenta(final boolean estadoCuenta) {
		this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
		return this;
	}

	public TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}

	public ClienteDTO setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = TipoDocumentoDTO.obtenerValorDefecto(tipoDocumento);
		return this;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public ClienteDTO setNumeroDocumento(final int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
		return this;
	}

	public String getDireccion() {
		return direccion;
	}

	public ClienteDTO setDireccion(final String direccion) {
		this.direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(direccion);
		return this;
	}

	public CiudadDTO getCiudad() {
		return ciudad;
	}

	public ClienteDTO setCiudad(final CiudadDTO ciudad) {
		this.ciudad = CiudadDTO.obtenerValorDefecto(ciudad);
		return this;
	}

	public String getDescripcionDireccion() {
		return descripcionDireccion;
	}

	public ClienteDTO setDescripcionDireccion(final String descripcionDireccion) {
		this.descripcionDireccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionDireccion);
		return this;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public ClienteDTO setFechaRegistro(final LocalDateTime fechaRegistro) {
		this.fechaRegistro = UtilObjeto.getInstance().obtenerValorDefecto(fechaRegistro, LocalDateTime.now());
		return this;
	}

	public BigDecimal getTotalComprado() {
		return totalComprado;
	}

	public ClienteDTO setTotalComprado(final BigDecimal totalComprado) {
		this.totalComprado = UtilObjeto.getInstance().obtenerValorDefecto(totalComprado, BigDecimal.ZERO);
		return this;
	}
}