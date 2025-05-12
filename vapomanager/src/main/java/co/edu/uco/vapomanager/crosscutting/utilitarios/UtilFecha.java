package co.edu.uco.vapomanager.crosscutting.utilitarios;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class UtilFecha {

    private UtilFecha() {
        super();
    }

   
    public static LocalDateTime obtenerFechaHoraActual() {
        return LocalDateTime.now();
    }

    
    public static ZonedDateTime obtenerFechaHoraActualZona() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }

    
    public static ZonedDateTime obtenerValorDefecto(final ZonedDateTime fecha) {
        return fecha != null ? fecha : obtenerFechaHoraActualZona();
    }
}
