package co.edu.uco.vapomanager.businesslogic.businesslogic.domain;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import java.util.UUID;

public final class CiudadDomain {

    public static final CiudadDomain DEFAULT_OBJECT = new CiudadDomain();

    private UUID id;
    private String nombre;
    private DepartamentoDomain departamento;

    public CiudadDomain() {
        this(UtilUUID.obtenerValorDefecto(),
             UtilTexto.getInstance().obtenerValorDefecto(),
             DepartamentoDomain.obtenerValorDefecto(null));
    }

    private CiudadDomain(UUID id, String nombre, DepartamentoDomain departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static CiudadDomain create(UUID id, String nombre, DepartamentoDomain departamento) {
        return new CiudadDomain(id, nombre, departamento);
    }

    public static CiudadDomain obtenerValorDefecto(final CiudadDomain entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public CiudadDomain setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadDomain setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }

    public DepartamentoDomain getDepartamento() {
        return departamento;
    }

    public CiudadDomain setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = UtilObjeto.getInstance()
            .obtenerValorDefecto(departamento, DepartamentoDomain.DEFAULT_OBJECT);
        return this;
    }
}
