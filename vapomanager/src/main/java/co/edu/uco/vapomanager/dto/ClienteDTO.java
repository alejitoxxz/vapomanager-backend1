package co.edu.uco.vapomanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ClienteDTO {

    public static final ClienteDTO DEFAULT_OBJECT = new ClienteDTO();

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

    private ClienteDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombres(UtilTexto.getInstance().obtenerValorDefecto());
        setApellidos(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoElectronico(UtilCorreo.obtenerValorDefecto(null));
        setConfirmacionCorreo(false);
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setEstadoCuenta(false);
        setTipoDocumento(TipoDocumentoDTO.DEFAULT_OBJECT);
        setNumeroDocumento(0);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(CiudadDTO.DEFAULT_OBJECT);
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setFechaRegistro(UtilFecha.obtenerFechaHoraActual());
        setTotalComprado(BigDecimal.ZERO);
    }

    public static ClienteDTO crear() {
        return new ClienteDTO();
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
        this.nombres = UtilTexto.getInstance().obtenerValorDefecto(nombres);
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public ClienteDTO setApellidos(final String apellidos) {
        this.apellidos = UtilTexto.getInstance().obtenerValorDefecto(apellidos);
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
        this.telefono = UtilTexto.getInstance().obtenerValorDefecto(telefono);
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
        this.tipoDocumento = UtilObjeto.getInstance().obtenerValorDefecto(tipoDocumento, TipoDocumentoDTO.DEFAULT_OBJECT);
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
        this.direccion = UtilTexto.getInstance().obtenerValorDefecto(direccion);
        return this;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public ClienteDTO setCiudad(final CiudadDTO ciudad) {
        this.ciudad = UtilObjeto.getInstance().obtenerValorDefecto(ciudad, CiudadDTO.DEFAULT_OBJECT);
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ClienteDTO setDescripcionDireccion(final String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().obtenerValorDefecto(descripcionDireccion);
        return this;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public ClienteDTO setFechaRegistro(final LocalDateTime fechaRegistro) {
        this.fechaRegistro = UtilFecha.obtenerFechaHoraActual();
        return this;
    }

    public BigDecimal getTotalComprado() {
        return totalComprado;
    }

    public ClienteDTO setTotalComprado(final BigDecimal totalComprado) {
        this.totalComprado = UtilBigDecimal.obtenerValorDefecto(totalComprado);
        return this;
    }
}