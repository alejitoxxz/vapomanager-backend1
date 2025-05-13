package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CategoriaDTO {

    public static final CategoriaDTO DEFAULT_OBJECT = new CategoriaDTO();

    private UUID id;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCategoria(UtilTexto.getInstance().obtenerValorDefecto());
        setDescripcion(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public CategoriaDTO(final UUID id, final String nombreCategoria, final String descripcion) {
        setId(id);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }

    public static CategoriaDTO obtenerValorDefecto(final CategoriaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, DEFAULT_OBJECT);
    }

    public UUID getId() {
        return id;
    }

    public CategoriaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public CategoriaDTO setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombreCategoria);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CategoriaDTO setDescripcion(final String descripcion) {
        this.descripcion = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(descripcion);
        return this;
    }
}
