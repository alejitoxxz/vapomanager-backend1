package co.edu.uco.vapomanager.crosscutting.utilitarios;

public final class UtilBooleano {

    private UtilBooleano() {
        super();
    }

    public static boolean obtenerValorDefecto(final Boolean valor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valor, false);
    }
}
