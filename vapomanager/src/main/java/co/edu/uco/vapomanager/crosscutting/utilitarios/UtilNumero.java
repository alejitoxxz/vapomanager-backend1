package co.edu.uco.vapomanager.crosscutting.utilitarios;

public final class UtilNumero {

    private UtilNumero() {
        super();
    }

    public static int obtenerValorDefecto(final Integer numero) {
        return UtilObjeto.getInstance().obtenerValorDefecto(numero, 0);
    }

    public static boolean esPositivo(final int numero) {
        return numero > 0;
    }

    public static boolean esNegativo(final int numero) {
        return numero < 0;
    }

    public static boolean esCero(final int numero) {
        return numero == 0;
    }

    public static boolean estaEnRango(final int valor, final int min, final int max) {
        return valor >= min && valor <= max;
    }
}
