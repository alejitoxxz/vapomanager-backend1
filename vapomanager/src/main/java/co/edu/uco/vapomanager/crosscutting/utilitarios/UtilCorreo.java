package co.edu.uco.vapomanager.crosscutting.utilitarios;

import java.util.regex.Pattern;

public final class UtilCorreo {

    
    private static final String REGEX_CORREO_VALIDO = "^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.(com|co|net|edu|org)$";

    private UtilCorreo() {
        
    }

    public static boolean esCorreoValido(final String correo) {
        final String correoDepurado = UtilTexto.getInstance().obtenerValorDefecto(correo).trim();
        return Pattern.matches(REGEX_CORREO_VALIDO, correoDepurado);
    }

    public static String obtenerValorDefecto(final String correo, final String correoDefecto) {
        return UtilTexto.getInstance().obtenerValorDefecto(correo, correoDefecto);
    }

    public static String obtenerValorDefecto(final String correo) {
        return obtenerValorDefecto(correo, UtilTexto.getInstance().obtenerValorDefecto());
    }
}
