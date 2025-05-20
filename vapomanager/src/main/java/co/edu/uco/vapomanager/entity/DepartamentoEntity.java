package co.edu.uco.vapomanager.entity;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class DepartamentoEntity {

    public static final DepartamentoEntity DEFAULT_OBJECT = new DepartamentoEntity();

    private UUID id;
    private String nombre;

    public DepartamentoEntity() {
        this(UtilUUID.obtenerValorDefecto(), UtilTexto.getInstance().obtenerValorDefecto());
    }

    private DepartamentoEntity(UUID id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static DepartamentoEntity create(UUID id, String nombre) {
        return new DepartamentoEntity(id, nombre);
    }

    public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public DepartamentoEntity setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoEntity setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombre);
        return this;
    }
}
