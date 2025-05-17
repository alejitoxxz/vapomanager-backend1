package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CategoriaEntity {

    public static final CategoriaEntity DEFAULT_OBJECT = new CategoriaEntity();

    private UUID id;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public CategoriaEntity(final UUID id) {
        setId(id);
        setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public CategoriaEntity(final UUID id, final String nombreCategoria, final String descripcion) {
        setId(id);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }

    public static CategoriaEntity obtenerValorDefecto() {
        return DEFAULT_OBJECT;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(nombreCategoria);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(descripcion);
    }
}