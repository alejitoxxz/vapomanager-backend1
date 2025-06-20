package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilCorreo;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProveedorDTO {

    private UUID id;
    private String nombreEmpresa;
    private boolean confirmacionTelefono;
    private boolean confirmacionCorreo;
    private String correoElectronico;
    private boolean estadoCuenta;
    private Long numeroTelefono;
    private String direccion;
    private CiudadDTO ciudad;
    private String descripcionDireccion;
    private TipoDocumentoDTO tipoDocumento;
    private Long numeroDocumento;

    public ProveedorDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreEmpresa(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setConfirmacionCorreo(false);
        setCorreoElectronico(UtilTexto.getInstance().obtenerValorDefecto());
        setEstadoCuenta(false);
        setNumeroTelefono(0L);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(new CiudadDTO());
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(new TipoDocumentoDTO());
        setNumeroDocumento(0L);
    }

    public ProveedorDTO(final UUID id,
                       final String nombreEmpresa,
                       final boolean confirmacionTelefono,
                       final boolean confirmacionCorreo,
                       final String correoElectronico,
                       final boolean estadoCuenta,
                       final Long numeroTelefono,
                       final String direccion,
                       final CiudadDTO ciudad,
                       final String descripcionDireccion,
                       final TipoDocumentoDTO tipoDocumento,
                       final Long numeroDocumento) {
        setId(id);
        setNombreEmpresa(nombreEmpresa);
        setConfirmacionTelefono(confirmacionTelefono);
        setConfirmacionCorreo(confirmacionCorreo);
        setCorreoElectronico(correoElectronico);
        setEstadoCuenta(estadoCuenta);
        setNumeroTelefono(numeroTelefono);
        setDireccion(direccion);
        setCiudad(ciudad);
        setDescripcionDireccion(descripcionDireccion);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
    }

    public static ProveedorDTO obtenerValorDefecto(final ProveedorDTO proveedor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(proveedor, new ProveedorDTO());
    }

    public UUID getId() {
        return id;
    }

    public ProveedorDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public ProveedorDTO setNombreEmpresa(final String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreEmpresa);
        return this;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public ProveedorDTO setConfirmacionTelefono(final boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
        return this;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public ProveedorDTO setConfirmacionCorreo(final boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ProveedorDTO setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = UtilCorreo.obtenerValorDefecto(correoElectronico);
        return this;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public ProveedorDTO setEstadoCuenta(final boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
        return this;
    }

    public Long getNumeroTelefono() {
        return numeroTelefono;
    }

    public ProveedorDTO setNumeroTelefono(final Long numeroTelefono) {
        this.numeroTelefono = UtilNumero.obtenerValorDefecto(numeroTelefono);
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public ProveedorDTO setDireccion(final String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(direccion);
        return this;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public ProveedorDTO setCiudad(final CiudadDTO ciudad) {
        this.ciudad = CiudadDTO.obtenerValorDefecto(ciudad);
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ProveedorDTO setDescripcionDireccion(final String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionDireccion);
        return this;
    }

    public TipoDocumentoDTO getTipoDocumento() {
        return tipoDocumento;
    }

    public ProveedorDTO setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
        this.tipoDocumento = TipoDocumentoDTO.obtenerValorDefecto(tipoDocumento);
        return this;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public ProveedorDTO setNumeroDocumento(final Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }
}
