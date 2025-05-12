package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;

public class DepartamentoEntity {

	private UUID id;
	private String nombre;

	public DepartamentoEntity() {
		setId(UtilUUID.obtenerValorDefecto())
		.setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoEntity(final UUID id) {
		setId(id)
		.setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoEntity(final UUID id, final String nombre) {
		setId(id)
		.setNombre(nombre);
	}

	public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity departamento) {
		return UtilObjeto.getInstance().obtenerValorDefecto(departamento, new DepartamentoEntity());
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
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
		return this;
	}
}