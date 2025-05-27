package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class DepartamentoEntity {

	private UUID id;
	private String nombre;

	public DepartamentoEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoEntity(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public DepartamentoEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity departamento) {
		return UtilObjeto.getInstance().obtenerValorDefecto(departamento, new DepartamentoEntity());
	}

	public static DepartamentoEntity obtenerValorDefecto() {
		return new DepartamentoEntity();
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
	}
}