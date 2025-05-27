package co.edu.uco.vapomanager.entity;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public final class CiudadEntity {

	private UUID id;
	private String nombre;
	private DepartamentoEntity departamento;

	public CiudadEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setDepartamento(new DepartamentoEntity());
	}

	public CiudadEntity(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setDepartamento(new DepartamentoEntity());
	}

	public CiudadEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}

	public static CiudadEntity obtenerValorDefecto(final CiudadEntity entity) {
		return UtilObjeto.getInstance().obtenerValorDefecto(entity, new CiudadEntity());
	}

	public static CiudadEntity obtenerValorDefecto() {
		return new CiudadEntity();
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

	public DepartamentoEntity getDepartamento() {
		return departamento;
	}

	public void setDepartamento(final DepartamentoEntity departamento) {
		this.departamento = DepartamentoEntity.obtenerValorDefecto(departamento);
	}
}