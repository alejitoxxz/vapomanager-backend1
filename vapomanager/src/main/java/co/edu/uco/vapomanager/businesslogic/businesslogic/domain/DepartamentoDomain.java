package co.edu.uco.vapomanager.businesslogic.businesslogic.domain;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class DepartamentoDomain {

    public static final DepartamentoDomain DEFAULT_OBJECT = new DepartamentoDomain();

    private UUID id;
    private String nombre;

    public DepartamentoDomain() {
        this(UtilUUID.obtenerValorDefecto(), UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoDomain(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static DepartamentoDomain create(UUID id, String nombre) {
        return new DepartamentoDomain(id, nombre);
    }

    public static DepartamentoDomain obtenerValorDefecto(final DepartamentoDomain entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public DepartamentoDomain setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDomain setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }
}
