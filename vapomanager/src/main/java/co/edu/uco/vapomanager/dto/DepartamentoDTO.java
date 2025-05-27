package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class DepartamentoDTO {

	private UUID id;
	private String nombre;

	public DepartamentoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoDTO(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoDTO(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static DepartamentoDTO obtenerValorDefecto(final DepartamentoDTO departamento) {
		return UtilObjeto.getInstance().obtenerValorDefecto(departamento, new DepartamentoDTO());
	}

	public UUID getId() {
		return id;
	}

	public DepartamentoDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public DepartamentoDTO setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
		return this;
	}
}