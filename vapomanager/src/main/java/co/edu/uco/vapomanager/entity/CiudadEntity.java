package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public class CiudadEntity {

    public static final CiudadEntity DEFAULT_OBJECT = new CiudadEntity();

    private UUID id;
    private String nombre;
    private DepartamentoEntity departamento;

    public CiudadEntity() {
        setId(UtilUUID.obtenerValorDefecto())
        .setNombre(UtilTexto.getInstance().obtenerValorDefecto())
        .setDepartamento(new DepartamentoEntity());
    }

    public CiudadEntity(final UUID id) {
        setId(id)
        .setNombre(UtilTexto.getInstance().obtenerValorDefecto())
        .setDepartamento(new DepartamentoEntity());
    }

    public CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
        setId(id)
        .setNombre(nombre)
        .setDepartamento(departamento);
    }

    public static CiudadEntity obtenerValorDefecto(final CiudadEntity ciudad) {
        return UtilObjeto.getInstance().obtenerValorDefecto(ciudad, new CiudadEntity());
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
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public CiudadEntity setDepartamento(final DepartamentoEntity departamento) {
        this.departamento = DepartamentoEntity.obtenerValorDefecto(departamento);
        return this;
    }
}
