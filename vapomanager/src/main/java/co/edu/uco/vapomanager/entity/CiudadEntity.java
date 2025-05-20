package co.edu.uco.vapomanager.entity;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class CiudadEntity {

    public static final CiudadEntity DEFAULT_OBJECT = new CiudadEntity();

    private UUID id;
    private String nombre;
    private DepartamentoEntity departamento;

    public CiudadEntity() {
        this(UtilUUID.obtenerValorDefecto(),
             UtilTexto.getInstance().obtenerValorDefecto(),
             DepartamentoEntity.obtenerValorDefecto(null));
    }

    private CiudadEntity(UUID id, String nombre, DepartamentoEntity departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static CiudadEntity create(UUID id, String nombre, DepartamentoEntity departamento) {
        return new CiudadEntity(id, nombre, departamento);
    }

    public static CiudadEntity obtenerValorDefecto(final CiudadEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public CiudadEntity setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadEntity setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombre);
        return this;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
        this.departamento = UtilObjeto.getInstance()
            .obtenerValorDefecto(departamento, DepartamentoEntity.DEFAULT_OBJECT);
        return this;
    }
}
