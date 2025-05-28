package co.edu.uco.vapomanager.crosscutting.utilitarios;

public final class UtilNumero {

    private UtilNumero() {
        super();
    }

    public static int obtenerValorDefecto(final Integer numero) {
        return UtilObjeto.getInstance().obtenerValorDefecto(numero, 0);
    }

    public static Long obtenerValorDefecto(final Long numero) {
        return UtilObjeto.getInstance().obtenerValorDefecto(numero, 0L);
    }

    public static boolean esPositivo(final Long numero) {
        return numero != null && numero > 0;
    }

    public static boolean esNegativo(final Long numero) {
        return numero != null && numero < 0;
    }

    public static boolean esCero(final Long numero) {
        return numero != null && numero == 0;
    }

    public static boolean estaEnRango(final Long valor, final Long min, final Long max) {
        return valor != null && valor >= min && valor <= max;
    }

    // (si lo necesitas también para int)
    public static boolean estaEnRango(final int valor, final int min, final int max) {
        return valor >= min && valor <= max;
    }
}

