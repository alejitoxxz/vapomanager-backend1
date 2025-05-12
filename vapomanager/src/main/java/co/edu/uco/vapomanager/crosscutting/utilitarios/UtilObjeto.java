package co.edu.uco.vapomanager.crosscutting.utilitarios;

public final class UtilObjeto {

    private static final UtilObjeto INSTANCIA = new UtilObjeto();

    private UtilObjeto() {
        // Constructor privado para Singleton
    }

    public static UtilObjeto getInstance() {
        return INSTANCIA;
    }

    public <O> boolean esNulo(final O objeto) {
        return objeto == null;
    }

    public <O> O obtenerValorDefecto(final O valorOriginal, final O valorDefecto) {
        return esNulo(valorOriginal) ? valorDefecto : valorOriginal;
    }
}
