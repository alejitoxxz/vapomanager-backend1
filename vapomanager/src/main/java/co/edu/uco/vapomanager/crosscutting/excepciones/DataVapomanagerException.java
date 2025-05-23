package co.edu.uco.vapomanager.crosscutting.excepciones;

public class DataVapomanagerException extends VapomanagerException {

	private static final long serialVersionUID = 6432642782535270277L;

	private DataVapomanagerException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.DATA);

	}

	public static VapomanagerException reportar(String mensajeUsuario) {
		return new DataVapomanagerException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	
	public static VapomanagerException reportar(String mensajeUsuario, String mensajeTecnico) {
		return new DataVapomanagerException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static VapomanagerException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new DataVapomanagerException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
	
}
