package co.edu.uco.vapomanager.crosscutting.excepciones;

public class DataOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6432642782535270277L;

	private DataOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.DATA);

	}

	public static OnlineTestException reportar(String mensajeUsuario) {
		return new DataOnlineTestException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
		return new DataOnlineTestException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new DataOnlineTestException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
	
}
