package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ProveedorDTO {

    public static final ProveedorDTO DEFAULT_OBJECT = new ProveedorDTO();

    private UUID id;
    private String nombreEmpresa;
    private boolean confirmacionTelefono;
    private boolean confirmacionCorreo;
    private String correoElectronico;
    private boolean estadoCuenta;
    private String direccion;
    private CiudadDTO ciudad;
    private String descripcionDireccion;
    private TipoDocumentoDTO tipoDocumento;
    private int numeroDocumento;

    public ProveedorDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreEmpresa(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setConfirmacionCorreo(false);
        setCorreoElectronico(UtilCorreo.obtenerValorDefecto(correoElectronico));
        setEstadoCuenta(false);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(CiudadDTO.DEFAULT_OBJECT);
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(TipoDocumentoDTO.DEFAULT_OBJECT);
        setNumeroDocumento(0);
    }

    public static ProveedorDTO obtenerValorDefecto(final ProveedorDTO proveedor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(proveedor, ProveedorDTO.DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public ProveedorDTO setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public ProveedorDTO setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getInstance().obtenerValorDefecto(nombreEmpresa);
        return this;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public ProveedorDTO setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
        return this;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public ProveedorDTO setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ProveedorDTO setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
        return this;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public ProveedorDTO setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public ProveedorDTO setDireccion(String direccion) {
        this.direccion = UtilTexto.getInstance().obtenerValorDefecto(direccion);
        return this;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public ProveedorDTO setCiudad(CiudadDTO ciudad) {
        this.ciudad = UtilObjeto.getInstance().obtenerValorDefecto(ciudad, CiudadDTO.DEFAULT_OBJECT);
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ProveedorDTO setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().obtenerValorDefecto(descripcionDireccion);
        return this;
    }

    public TipoDocumentoDTO getTipoDocumento() {
        return tipoDocumento;
    }

    public ProveedorDTO setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
        this.tipoDocumento = UtilObjeto.getInstance().obtenerValorDefecto(tipoDocumento, TipoDocumentoDTO.DEFAULT_OBJECT);
        return this;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public ProveedorDTO setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }
}