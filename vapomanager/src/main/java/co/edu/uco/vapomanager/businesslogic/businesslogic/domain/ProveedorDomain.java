package co.edu.uco.vapomanager.businesslogic.businesslogic.domain;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class ProveedorDomain {

    public static final ProveedorDomain DEFAULT_OBJECT = new ProveedorDomain();

    private UUID id;
    private String nombreEmpresa;
    private boolean confirmacionTelefono;
    private boolean confirmacionCorreo;
    private String correoElectronico;
    private boolean estadoCuenta;
    private Long numeroTelefono;
    private String direccion;
    private CiudadDomain ciudad;
    private String descripcionDireccion;
    private TipoDocumentoDomain tipoDocumento;
    private Long numeroDocumento;

    public ProveedorDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreEmpresa(UtilTexto.getInstance().obtenerValorDefecto());
        setConfirmacionTelefono(false);
        setConfirmacionCorreo(false);
        setCorreoElectronico(UtilTexto.getInstance().obtenerValorDefecto());
        setEstadoCuenta(false);
        setNumeroTelefono(0L);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(new CiudadDomain());
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(new TipoDocumentoDomain());
        setNumeroDocumento(0L);
    }

    public ProveedorDomain(
        UUID id,
        String nombreEmpresa,
        boolean confirmacionTelefono,
        boolean confirmacionCorreo,
        String correoElectronico,
        boolean estadoCuenta,
        Long numeroTelefono,
        String direccion,
        CiudadDomain ciudad,
        String descripcionDireccion,
        TipoDocumentoDomain tipoDocumento,
        Long numeroDocumento
    ) {
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

    public static ProveedorDomain create(
        UUID id,
        String nombreEmpresa,
        boolean confirmacionTelefono,
        boolean confirmacionCorreo,
        String correoElectronico,
        boolean estadoCuenta,
        Long numeroTelefono,
        String direccion,
        CiudadDomain ciudad,
        String descripcionDireccion,
        TipoDocumentoDomain tipoDocumento,
        Long numeroDocumento
    ) {
        return new ProveedorDomain(
            id, nombreEmpresa, confirmacionTelefono, confirmacionCorreo,
            correoElectronico, estadoCuenta, numeroTelefono, direccion,
            ciudad, descripcionDireccion, tipoDocumento, numeroDocumento
        );
    }

    public static ProveedorDomain obtenerValorDefecto(final ProveedorDomain proveedor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(proveedor, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public ProveedorDomain setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public ProveedorDomain setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreEmpresa);
        return this;
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public ProveedorDomain setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
        return this;
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public ProveedorDomain setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ProveedorDomain setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(correoElectronico);
        return this;
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public ProveedorDomain setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
        return this;
    }

    public Long getNumeroTelefono() {
        return numeroTelefono;
    }

    public ProveedorDomain setNumeroTelefono(Long numeroTelefono) {
        this.numeroTelefono = UtilNumero.obtenerValorDefecto(numeroTelefono);
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public ProveedorDomain setDireccion(String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(direccion);
        return this;
    }

    public CiudadDomain getCiudad() {
        return ciudad;
    }

    public ProveedorDomain setCiudad(CiudadDomain ciudad) {
        this.ciudad = UtilObjeto.getInstance().obtenerValorDefecto(ciudad, new CiudadDomain());
        return this;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public ProveedorDomain setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionDireccion);
        return this;
    }

    public TipoDocumentoDomain getTipoDocumento() {
        return tipoDocumento;
    }

    public ProveedorDomain setTipoDocumento(TipoDocumentoDomain tipoDocumento) {
        this.tipoDocumento = UtilObjeto.getInstance().obtenerValorDefecto(tipoDocumento, new TipoDocumentoDomain());
        return this;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public ProveedorDomain setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = UtilNumero.obtenerValorDefecto(numeroDocumento);
        return this;
    }
}
