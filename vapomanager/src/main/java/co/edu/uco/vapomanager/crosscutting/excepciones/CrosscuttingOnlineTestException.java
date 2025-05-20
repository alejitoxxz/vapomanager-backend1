package co.edu.uco.vapomanager.crosscutting.excepciones;

public class CrosscuttingOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6432642782535270277L;

	public CrosscuttingOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.CROSSCUTTING);

	}

	public static OnlineTestException reportar(String mensajeUsuario) {
		return new CrosscuttingOnlineTestException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new CrosscuttingOnlineTestException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
