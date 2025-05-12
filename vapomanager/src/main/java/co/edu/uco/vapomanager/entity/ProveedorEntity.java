package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.*;

public final class ProveedorEntity {

    public static final ProveedorEntity DEFAULT_OBJECT = new ProveedorEntity();

    private UUID id;
    private String nombreEmpresa;
    private boolean confirmacionTelefono;
    private boolean confirmacionCorreo;
    private String correoElectronico;
    private boolean estadoCuenta;
    private String direccion;
    private CiudadEntity ciudad;
    private String descripcionDireccion;
    private TipoDocumentoEntity tipoDocumento;
    private int numeroDocumento;

    public ProveedorEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreEmpresa(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setConfirmacionCorreo(false);
        setCorreoElectronico(UtilCorreo.obtenerValorDefecto(correoElectronico));
        setEstadoCuenta(false);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(CiudadEntity.DEFAULT_OBJECT);
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(TipoDocumentoEntity.DEFAULT_OBJECT);
        setNumeroDocumento(0);
    }

    public ProveedorEntity(UUID id, String nombreEmpresa, boolean confirmacionTelefono, boolean confirmacionCorreo,
                           String correoElectronico, boolean estadoCuenta, String direccion, CiudadEntity ciudad,
                           String descripcionDireccion, TipoDocumentoEntity tipoDocumento, int numeroDocumento) {

        setId(id);
        setNombreEmpresa(nombreEmpresa);
        setConfirmacionTelefono(confirmacionTelefono);
        setConfirmacionCorreo(confirmacionCorreo);
        setCorreoElectronico(correoElectronico);
        setEstadoCuenta(estadoCuenta);
        setDireccion(direccion);
        setCiudad(ciudad);
        setDescripcionDireccion(descripcionDireccion);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
    }

    public static ProveedorEntity create(UUID id, String nombreEmpresa, boolean confirmacionTelefono, boolean confirmacionCorreo,
                                         String correoElectronico, boolean estadoCuenta, String direccion, CiudadEntity ciudad,
                                         String descripcionDireccion, TipoDocumentoEntity tipoDocumento, int numeroDocumento) {

        return new ProveedorEntity(id, nombreEmpresa, confirmacionTelefono, confirmacionCorreo, correoElectronico,
                estadoCuenta, direccion, ciudad, descripcionDireccion, tipoDocumento, numeroDocumento);
    }

    public UUID getId() {
        return id;
    }

    public ProveedorEntity setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public ProveedorEntity setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getInstance().obtenerValorDefecto(nombreEmpresa);
        return this;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public ProveedorEntity setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
        return this;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public ProveedorEntity setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ProveedorEntity setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
        return this;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public ProveedorEntity setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public ProveedorEntity setDireccion(String direccion) {
        this.direccion = UtilTexto.getInstance().obtenerValorDefecto(direccion);
        return this;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public ProveedorEntity setCiudad(CiudadEntity ciudad) {
        this.ciudad = UtilObjeto.getInstance().obtenerValorDefecto(ciudad, CiudadEntity.DEFAULT_OBJECT);
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ProveedorEntity setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().obtenerValorDefecto(descripcionDireccion);
        return this;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public ProveedorEntity setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = UtilObjeto.getInstance().obtenerValorDefecto(tipoDocumento, TipoDocumentoEntity.DEFAULT_OBJECT);
        return this;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public ProveedorEntity setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }
}
