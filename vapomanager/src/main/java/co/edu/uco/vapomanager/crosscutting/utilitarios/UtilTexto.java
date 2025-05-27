package co.edu.uco.vapomanager.crosscutting.utilitarios;

public final class UtilTexto {

	private static final UtilTexto instancia = new UtilTexto();
	private static final String PATRON_SOLO_LETRAS_ESPACIOS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ ]+$";
	public static final String VACIO = "";
	

	private UtilTexto() {
		
	}

	public static UtilTexto getInstance() {
		return instancia;
	}
	
	public boolean patronValido(String valor, final String patron) {
		return obtenerValorDefecto(valor).matches(obtenerValorDefecto(patron));
	}
	
	public boolean contieneSoloLetrasEspacios(final String valor) {
		return patronValido(quitarEspacioBlancoInicioFin(valor), PATRON_SOLO_LETRAS_ESPACIOS);
	}

	public boolean esNula(final String valor) {
		return UtilObjeto.getInstance().esNulo(valor);
	}
	
	public boolean estaVacia(final String valor) {
		return VACIO.equals(quitarEspacioBlancoInicioFin(valor));
	}

	public String obtenerValorDefecto(final String valorOriginal, final String valorDefecto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(valorOriginal, valorDefecto);
	}

	public String obtenerValorDefecto(final String valor) {
		return obtenerValorDefecto(valor, VACIO);
	}

	public String obtenerValorDefecto() {
		return VACIO;
	}

	public String quitarEspacioBlancoInicioFin(final String valor) {
		return obtenerValorDefecto(valor).trim();
	}
}