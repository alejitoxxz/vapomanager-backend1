package co.edu.uco.vapomanager.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBigDecimal;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilCorreo;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ClienteEntity {

    public static final ClienteEntity DEFAULT_OBJECT = new ClienteEntity();

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
        setCorreoElectronico(UtilCorreo.obtenerValorDefecto(correoElectronico));
        setConfirmacionCorreo(false);
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setEstadoCuenta(false);
        setTipoDocumento(TipoDocumentoEntity.DEFAULT_OBJECT);
        setNumeroDocumento(0);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(CiudadEntity.DEFAULT_OBJECT);
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setFechaRegistro(UtilFecha.obtenerFechaHoraActual());
        setTotalComprado(BigDecimal.ZERO);
    }

    public ClienteEntity(UUID id, String nombres, String apellidos, String correoElectronico, boolean confirmacionCorreo,
                         String telefono, boolean confirmacionTelefono, boolean estadoCuenta,
                         TipoDocumentoEntity tipoDocumento, int numeroDocumento, String direccion,
                         CiudadEntity ciudad, String descripcionDireccion,
                         LocalDateTime fechaRegistro, BigDecimal totalComprado) {

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

    public static ClienteEntity create(UUID id, String nombres, String apellidos, String correoElectronico, boolean confirmacionCorreo,
                                       String telefono, boolean confirmacionTelefono, boolean estadoCuenta,
                                       TipoDocumentoEntity tipoDocumento, int numeroDocumento, String direccion,
                                       CiudadEntity ciudad, String descripcionDireccion,
                                       LocalDateTime fechaRegistro, BigDecimal totalComprado) {
        return new ClienteEntity(id, nombres, apellidos, correoElectronico, confirmacionCorreo,
                telefono, confirmacionTelefono, estadoCuenta,
                tipoDocumento, numeroDocumento, direccion, ciudad,
                descripcionDireccion, fechaRegistro, totalComprado);
    }

    public UUID getId() {
        return id;
    }

    public ClienteEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombres() {
        return nombres;
    }

    public ClienteEntity setNombres(String nombres) {
        this.nombres = UtilTexto.getInstance().obtenerValorDefecto(nombres);
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public ClienteEntity setApellidos(String apellidos) {
        this.apellidos = UtilTexto.getInstance().obtenerValorDefecto(apellidos);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ClienteEntity setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
        return this;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public ClienteEntity setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public ClienteEntity setTelefono(String telefono) {
        this.telefono = UtilTexto.getInstance().obtenerValorDefecto(telefono);
        return this;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public ClienteEntity setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
        return this;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public ClienteEntity setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
        return this;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public ClienteEntity setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = UtilObjeto.getInstance().obtenerValorDefecto(tipoDocumento, TipoDocumentoEntity.DEFAULT_OBJECT);
        return this;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public ClienteEntity setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public ClienteEntity setDireccion(String direccion) {
        this.direccion = UtilTexto.getInstance().obtenerValorDefecto(direccion);
        return this;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public ClienteEntity setCiudad(CiudadEntity ciudad) {
        this.ciudad = UtilObjeto.getInstance().obtenerValorDefecto(ciudad, CiudadEntity.DEFAULT_OBJECT);
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ClienteEntity setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().obtenerValorDefecto(descripcionDireccion);
        return this;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public ClienteEntity setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = UtilFecha.obtenerValorDefecto(fechaRegistro);
        return this;
    }

    public BigDecimal getTotalComprado() {
        return totalComprado;
    }

    public ClienteEntity setTotalComprado(BigDecimal totalComprado) {
        this.totalComprado = UtilBigDecimal.obtenerValorDefecto(totalComprado);
        return this;
    }
}
