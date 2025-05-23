package co.edu.uco.vapomanager.crosscutting.excepciones;

import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.vapomanager.crosscutting.utilitarios.UtilTexto;

public class VapomanagerException extends Exception {

	
	private static final long serialVersionUID = 172685827469497747L;
	
	private String mensajeUsuario;
	private LayerException capa;

	protected VapomanagerException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz, LayerException capa) {
		super(mensajeTecnico, exceptionRaiz);
		setMensajeUsuario(mensajeUsuario);
		setCapa(capa);
	}
	
	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	private void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario =UtilTexto.getInstance().quitarEspaciosBlancoInicioFin(mensajeUsuario);
	}
	
	public String getMensajeTecnico() {
		return UtilTexto.getInstance().obtenerValorDefecto(getMessage());
	}
	
	public Throwable getExcepcionRaiz() {
		return UtilObjeto.getInstance().obtenerValorDefecto(getCause(), new Exception(getMensajeUsuario()));
	}

	public LayerException getCapa() {
		return capa;
	}

	private void setCapa(LayerException capa) {
		this.capa = UtilObjeto.getInstance().obtenerValorDefecto(capa, LayerException.GENERAL);
	}
	
	
}
