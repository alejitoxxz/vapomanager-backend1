package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ClienteEntity {

	private UUID id;
	private String nombres;
	private String apellidos;
	private String correoElectronico;
	private boolean confirmacionCorreo;
	private String telefono;
	private boolean confirmacionTelefono;
	private boolean estadoCuenta;
	private TipoDocumentoEntity tipoDocumento;
	private int numeroDocumento;
	private String direccion;
	private CiudadEntity ciudad;
	private String descripcionDireccion;
	private LocalDateTime fechaRegistro;
	private BigDecimal totalComprado;

	public ClienteEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombres(UtilTexto.getInstance().obtenerValorDefecto());
		setApellidos(UtilTexto.getInstance().obtenerValorDefecto());
		setCorreoElectronico(UtilCorreo.obtenerValorDefecto(null));
		setConfirmacionCorreo(false);
		setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
		setConfirmacionTelefono(false);
		setEstadoCuenta(false);
		setTipoDocumento(new TipoDocumentoEntity());
		setNumeroDocumento(0);
		setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
		setCiudad(new CiudadEntity());
		setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
		setFechaRegistro(LocalDateTime.now());
		setTotalComprado(BigDecimal.ZERO);
	}

	public ClienteEntity(final UUID id, final String nombres, final String apellidos, final String correoElectronico,
	                     final boolean confirmacionCorreo, final String telefono, final boolean confirmacionTelefono,
	                     final boolean estadoCuenta, final TipoDocumentoEntity tipoDocumento, final int numeroDocumento,
	                     final String direccion, final CiudadEntity ciudad, final String descripcionDireccion,
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

	public static ClienteEntity obtenerValorDefecto(final ClienteEntity cliente) {
		return UtilObjeto.getInstance().obtenerValorDefecto(cliente, new ClienteEntity());
	}

	public static ClienteEntity obtenerValorDefecto() {
		return new ClienteEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(final String nombres) {
		this.nombres = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombres);
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(apellidos);
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
	}

	public boolean isConfirmacionCorreo() {
		return confirmacionCorreo;
	}

	public void setConfirmacionCorreo(final boolean confirmacionCorreo) {
		this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(final String telefono) {
		this.telefono = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(telefono);
	}

	public boolean isConfirmacionTelefono() {
		return confirmacionTelefono;
	}

	public void setConfirmacionTelefono(final boolean confirmacionTelefono) {
		this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
	}

	public boolean isEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(final boolean estadoCuenta) {
		this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
	}

	public TipoDocumentoEntity getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final TipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = TipoDocumentoEntity.obtenerValorDefecto(tipoDocumento);
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(final int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(direccion);
	}

	public CiudadEntity getCiudad() {
		return ciudad;
	}

	public void setCiudad(final CiudadEntity ciudad) {
		this.ciudad = CiudadEntity.obtenerValorDefecto(ciudad);
	}

	public String getDescripcionDireccion() {
		return descripcionDireccion;
	}

	public void setDescripcionDireccion(final String descripcionDireccion) {
		this.descripcionDireccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionDireccion);
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(final LocalDateTime fechaRegistro) {
		this.fechaRegistro = UtilObjeto.getInstance().obtenerValorDefecto(fechaRegistro, LocalDateTime.now());
	}

	public BigDecimal getTotalComprado() {
		return totalComprado;
	}

	public void setTotalComprado(final BigDecimal totalComprado) {
		this.totalComprado = UtilObjeto.getInstance().obtenerValorDefecto(totalComprado, BigDecimal.ZERO);
	}
}