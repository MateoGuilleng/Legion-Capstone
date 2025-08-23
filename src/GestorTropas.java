import java.util.*;


public class GestorTropas {
    
    // constantes
    public static final String COMANDANTE = "Comandante";
    public static final String MEDICO = "Medico";
    public static final String TANQUE = "Tanque";
    public static final String SNIPER = "Sniper";
    public static final String INFANTERIA = "Infanteria";
    
    // Troop type hierarchy (index order determines priority)
    private static final String[] TIPOS_TROPAS = {
        COMANDANTE, MEDICO, TANQUE, SNIPER, INFANTERIA
    };
    
    /**
     * Convierte valores numéricos en tipos de tropas según la especificación
     * n>0 and n<11 -> Comandante
     * n>10 and n<21 -> Medico
     * n>20 and n<31 -> Tanque
     * n>30 and n<41 -> Sniper
     * n>40 and n<51 -> Infanteria
     */
    public static String convertirNumeroATipoTropa(int numero) {
        if (numero > 0 && numero < 11) {
            return COMANDANTE;
        } else if (numero > 10 && numero < 21) {
            return MEDICO;
        } else if (numero > 20 && numero < 31) {
            return TANQUE;
        } else if (numero > 30 && numero < 41) {
            return SNIPER;
        } else if (numero > 40 && numero < 51) {
            return INFANTERIA;
        } else {
            return INFANTERIA; // Default fallback
        }
    }
    
    /**
     * Convierte los valores de los personajes en tipos de tropas según la especificación.
     * a-j -> Comandante
     * k-t -> Medico
     * u-z -> Tanque
     * K-N -> Sniper
     * O-X -> Infanteria
     */
    public static String convertirCaracterATipoTropa(char caracter) {
        char c = Character.toLowerCase(caracter);
        
        if (c >= 'a' && c <= 'j') {
            return COMANDANTE;
        } else if (c >= 'k' && c <= 't') {
            return MEDICO;
        } else if (c >= 'u' && c <= 'z') {
            return TANQUE;
        } else if (c >= 'o' && c <= 'x') {
            return INFANTERIA;
        } else {
            return INFANTERIA; // Default fallback
        }
    }
    
    /**
     * Obtiene el símbolo de tipo de tropa para fines de visualización.
     */
    public static String obtenerSimboloTropa(String tipoTropa) {
        switch (tipoTropa) {
            case COMANDANTE: return "c";
            case MEDICO: return "M";
            case TANQUE: return "T";
            case SNIPER: return "S";
            case INFANTERIA: return "I";
            default: return "*";
        }
    }
    
    /**
     * Obtiene el símbolo de tipo de tropa para valores numéricos
     */
    public static String obtenerSimboloTropaNumerica(int numero) {
        String tipo = convertirNumeroATipoTropa(numero);
        return obtenerSimboloTropa(tipo);
    }
    
    /**
     * Obtiene el símbolo de tipo de tropa para valores de caracteres
     */
    public static String obtenerSimboloTropaCaracter(char caracter) {
        String tipo = convertirCaracterATipoTropa(caracter);
        return obtenerSimboloTropa(tipo);
    }
    
    /**
     * Crea una lista de unidades de tropa basadas en el array de distribución
     * El array representa: [Comandantes, Médicos, Tanques, Snipers, Infantería]
     */
    public static List<String> crearUnidadesDesdeDistribucion(int[] distribucion) {
        List<String> unidades = new ArrayList<>();
        
        for (int i = 0; i < distribucion.length && i < TIPOS_TROPAS.length; i++) {
            String tipoTropa = TIPOS_TROPAS[i];
            int cantidad = distribucion[i];
            
            for (int j = 0; j < cantidad; j++) {
                unidades.add(tipoTropa);
            }
        }
        
        return unidades;
    }
    
    /**
     * Convierte una lista de tipos de tropa a sus símbolos de visualización
     */
    public static List<String> convertirTiposASimbolos(List<String> tiposTropas) {
        List<String> simbolos = new ArrayList<>();
        for (String tipo : tiposTropas) {
            simbolos.add(obtenerSimboloTropa(tipo));
        }
        return simbolos;
    }
    
    /**
     * Obtiene el índice de prioridad de un tipo de tropa (índice menor = prioridad mayor)
     */
    public static int obtenerPrioridadTropa(String tipoTropa) {
        for (int i = 0; i < TIPOS_TROPAS.length; i++) {
            if (TIPOS_TROPAS[i].equals(tipoTropa)) {
                return i;
            }
        }
        return TIPOS_TROPAS.length; // Lowest priority if not found
    }
    
    /**
     * Ordena las tropas según su prioridad jerárquica
     */
    public static List<String> ordenarPorPrioridad(List<String> tiposTropas) {
        List<String> ordenados = new ArrayList<>(tiposTropas);
        ordenados.sort(Comparator.comparingInt(GestorTropas::obtenerPrioridadTropa));
        return ordenados;
    }
    
    /**
     * Obtiene el número total de tropas desde un array de distribución
     */
    public static int obtenerTotalTropas(int[] distribucion) {
        int total = 0;
        for (int cantidad : distribucion) {
            total += cantidad;
        }
        return total;
    }
    
    /**
     * Valida si un array de distribución es válido
     */
    public static boolean esDistribucionValida(int[] distribucion) {
        if (distribucion == null || distribucion.length == 0) {
            return false;
        }
        
        for (int cantidad : distribucion) {
            if (cantidad < 0) {
                return false;
            }
        }
        
        return true;
    }
}
