package co.edu.uco.vapomanager.crosscutting.excepciones;

public class BusinessLogicVapomanagerException extends VapomanagerException {

	private static final long serialVersionUID = 6432642782535270277L;

	public BusinessLogicVapomanagerException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.BUSINESS_LOGIC);

	}

	public static VapomanagerException reportar(String mensajeUsuario) {
		return new BusinessLogicVapomanagerException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static VapomanagerException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new BusinessLogicVapomanagerException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
