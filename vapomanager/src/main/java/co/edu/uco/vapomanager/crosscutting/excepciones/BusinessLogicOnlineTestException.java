package co.edu.uco.vapomanager.crosscutting.excepciones;

public class BusinessLogicOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6432642782535270277L;

	public BusinessLogicOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.BUSINESS_LOGIC);

	}

	public static OnlineTestException reportar(String mensajeUsuario) {
		return new BusinessLogicOnlineTestException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new BusinessLogicOnlineTestException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
