package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class MarcaEntity {

	private UUID id;
	private String marca;

	public MarcaEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setMarca(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public MarcaEntity(final UUID id, final String marca) {
		setId(id);
		setMarca(marca);
	}

	public static MarcaEntity obtenerValorDefecto(final MarcaEntity entidad) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entidad, new MarcaEntity());
	}

	public static MarcaEntity obtenerValorDefecto() {
		return new MarcaEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(final String marca) {
		this.marca = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(marca);
	}
}