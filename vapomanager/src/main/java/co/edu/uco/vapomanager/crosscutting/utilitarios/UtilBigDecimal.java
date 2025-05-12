package co.edu.uco.vapomanager.crosscutting.utilitarios;

import java.math.BigDecimal;

public final class UtilBigDecimal {

    private UtilBigDecimal() {
        super();
    }

    public static BigDecimal obtenerValorDefecto(final BigDecimal valor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valor, BigDecimal.ZERO);
    }

    public static boolean esMayorQueCero(final BigDecimal valor) {
        return obtenerValorDefecto(valor).compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean esNegativo(final BigDecimal valor) {
        return obtenerValorDefecto(valor).compareTo(BigDecimal.ZERO) < 0;
    }

    public static BigDecimal sumar(final BigDecimal a, final BigDecimal b) {
        return obtenerValorDefecto(a).add(obtenerValorDefecto(b));
    }

    public static BigDecimal restar(final BigDecimal a, final BigDecimal b) {
        return obtenerValorDefecto(a).subtract(obtenerValorDefecto(b));
    }
}
