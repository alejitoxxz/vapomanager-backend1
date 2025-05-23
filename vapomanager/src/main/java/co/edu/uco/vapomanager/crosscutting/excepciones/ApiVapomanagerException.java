package co.edu.uco.vapomanager.crosscutting.excepciones;

public class ApiVapomanagerException extends VapomanagerException {

	private static final long serialVersionUID = 6432642782535270277L;

	public ApiVapomanagerException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.API);

	}

	public static VapomanagerException reportar(String mensajeUsuario) {
		return new ApiVapomanagerException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static VapomanagerException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new ApiVapomanagerException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
