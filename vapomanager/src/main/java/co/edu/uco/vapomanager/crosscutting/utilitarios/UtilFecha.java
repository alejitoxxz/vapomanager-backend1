package co.edu.uco.vapomanager.crosscutting.utilitarios;

import java.time.LocalDateTime;

public final class UtilFecha {

    private UtilFecha() {
        super();
    }

    public static LocalDateTime obtenerFechaHoraActual() {
        return LocalDateTime.now();
    }

    public static boolean esFechaPasada(final LocalDateTime fecha) {
        return fecha.isBefore(LocalDateTime.now());
    }

    public static boolean esFechaFutura(final LocalDateTime fecha) {
        return fecha.isAfter(LocalDateTime.now());
    }

    public static boolean esNula(final LocalDateTime fecha) {
        return fecha == null;
    }

    public static LocalDateTime obtenerValorDefecto(final LocalDateTime fecha) {
        return UtilObjeto.getInstance().obtenerValorDefecto(fecha, LocalDateTime.now());
    }
}
