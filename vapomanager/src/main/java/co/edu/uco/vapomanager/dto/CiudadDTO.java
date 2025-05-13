package co.edu.uco.vapomanager.dto;

import java.util.UUID;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilUUID;

public class CiudadDTO {

	public static final CiudadDTO DEFAULT_OBJECT = new CiudadDTO();

	private UUID id;
	private String nombre;
	private DepartamentoDTO departamento;

	

	public static CiudadDTO obtenerValorDefecto(final CiudadDTO ciudad) {
		return UtilObjeto.getInstance().obtenerValorDefecto(ciudad, new CiudadDTO());
	}

	public UUID getId() {
		return id;
	}

	public CiudadDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public CiudadDTO setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
		return this;
	}

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
		this.departamento = DepartamentoDTO.obtenerValorDefecto(departamento);
		return this;
	}
}
