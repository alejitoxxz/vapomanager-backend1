package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ClienteEntity {

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
        // Aquí puedes inicializar valores por defecto si quieres, o dejar nulls.
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public void setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = confirmacionCorreo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public void setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = confirmacionTelefono;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getTotalComprado() {
        return totalComprado;
    }

    public void setTotalComprado(BigDecimal totalComprado) {
        this.totalComprado = totalComprado;
    }
}