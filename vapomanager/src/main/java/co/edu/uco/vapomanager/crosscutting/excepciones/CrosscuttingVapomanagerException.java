package co.edu.uco.vapomanager.crosscutting.excepciones;

public class CrosscuttingVapomanagerException extends VapomanagerException {

	private static final long serialVersionUID = 6432642782535270277L;

	public CrosscuttingVapomanagerException(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz) {
		super(mensajeUsuario, mensajeTecnico, exceptionRaiz, LayerException.CROSSCUTTING);

	}

	public static VapomanagerException reportar(String mensajeUsuario) {
		return new CrosscuttingVapomanagerException(mensajeUsuario, mensajeUsuario, new  Exception());
	}
	
	public static VapomanagerException reportar(String mensajeUsuario, String mensajeTecnico, Exception exceptionRaiz ) {
		return new CrosscuttingVapomanagerException(mensajeUsuario, mensajeTecnico, exceptionRaiz);
	}
}
