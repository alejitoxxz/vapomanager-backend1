package co.edu.uco.vapomanager.crosscutting.excepciones;

public class ApiOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6432642782535270277L;

	public ApiOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.API);

	}

	public static OnlineTestException reportar(String mensajeUsuario) {
		return new ApiOnlineTestException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new ApiOnlineTestException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
