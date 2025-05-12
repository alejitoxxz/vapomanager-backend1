package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CategoriaEntity {

    private UUID id;
    private String nombreCategoria;
    private String descripcion;

    public static final CategoriaEntity DEFAULT_OBJECT = new CategoriaEntity();

    public CategoriaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public CategoriaEntity(final UUID id, final String nombreCategoria, final String descripcion) {
        setId(id);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }

    public static CategoriaEntity obtenerValorDefecto(final CategoriaEntity entidad) {
        return entidad != null ? entidad : DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public CategoriaEntity setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public CategoriaEntity setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreCategoria);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CategoriaEntity setDescripcion(final String descripcion) {
        this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
        return this;
    }
}
