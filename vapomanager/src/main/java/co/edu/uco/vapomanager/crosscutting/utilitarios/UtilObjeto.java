package co.edu.uco.vapomanager.crosscutting.utilitarios;

public class UtilObjeto {
    
    private static UtilObjeto instancia;
    
    private UtilObjeto() {
        // Constructor privado para patrón Singleton
    }
    
    public static synchronized UtilObjeto getInstance() {
        if (instancia == null) {
            instancia = new UtilObjeto();
        }
        return instancia;
    }
    
    public <O> boolean esNulo(final O objeto) {
        return objeto == null;
    }
    
    public <O> O obtenerValorDefecto(final O valorOriginal, final O valorDefecto) {
        return esNulo(valorOriginal) ? valorDefecto : valorOriginal;
    }
    
    public static void main(String[] args) {
        System.out.println(UtilObjeto.getInstance().obtenerValorDefecto(null, "Valor por defecto"));
        System.out.println(UtilObjeto.getInstance().esNulo("23"));
    }

	public static UtilObjeto getIntance() {
		// TODO Auto-generated method stub
		return null;
	}
}

