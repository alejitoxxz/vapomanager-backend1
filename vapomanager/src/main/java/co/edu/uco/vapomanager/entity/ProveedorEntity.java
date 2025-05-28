package co.edu.uco.vapomanager.entity;

import java.util.UUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilNumero;

public final class ProveedorEntity {

    private UUID id;
    private String nombreEmpresa;
    private boolean confirmacionTelefono;
    private boolean confirmacionCorreo;
    private String correoElectronico;
    private boolean estadoCuenta;
    private int numeroTelefono;
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
        setCorreoElectronico(UtilTexto.getInstance().obtenerValorDefecto());
        setEstadoCuenta(false);
        setNumeroTelefono(0);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setCiudad(new CiudadEntity());
        setDescripcionDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(new TipoDocumentoEntity());
        setNumeroDocumento(0);
    }

    public ProveedorEntity(UUID id,
                           String nombreEmpresa,
                           boolean confirmacionTelefono,
                           boolean confirmacionCorreo,
                           String correoElectronico,
                           boolean estadoCuenta,
                           int numeroTelefono,
                           String direccion,
                           CiudadEntity ciudad,
                           String descripcionDireccion,
                           TipoDocumentoEntity tipoDocumento,
                           int numeroDocumento) {
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

    public static ProveedorEntity obtenerValorDefecto(final ProveedorEntity proveedor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(proveedor, new ProveedorEntity());
    }

    public static ProveedorEntity obtenerValorDefecto() {
        return new ProveedorEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreEmpresa);
    }

    public boolean isConfirmacionTelefono() {
        return confirmacionTelefono;
    }

    public void setConfirmacionTelefono(boolean confirmacionTelefono) {
        this.confirmacionTelefono = UtilBooleano.obtenerValorDefecto(confirmacionTelefono);
    }

    public boolean isConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public void setConfirmacionCorreo(boolean confirmacionCorreo) {
        this.confirmacionCorreo = UtilBooleano.obtenerValorDefecto(confirmacionCorreo);
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(correoElectronico);
    }

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = UtilBooleano.obtenerValorDefecto(estadoCuenta);
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = UtilNumero.obtenerValorDefecto(numeroTelefono);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(direccion);
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = CiudadEntity.obtenerValorDefecto(ciudad);
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcionDireccion);
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = TipoDocumentoEntity.obtenerValorDefecto(tipoDocumento);
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
