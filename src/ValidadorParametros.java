import java.util.*;

public class ValidadorParametros {
    // Valid algorithms: Selection, Bubble, Insertion, Merge
    private static final String[] ALGORITMOS_VALIDOS = {"s", "b", "i", "m"};
    // Valid types: Numeric or Character
    private static final String[] TIPOS_VALIDOS = {"n", "c"};
    // Valid orientations: North, South, East, West
    private static final String[] ORIENTACIONES_VALIDAS = {"n", "s", "e", "w"};
    // Default battlefield size
    private static final int TAMANO_TABLERO_DEFAULT = 10;
    private static final int TAMANO_TABLERO_MIN = 5;
    private static final int TAMANO_TABLERO_MAX = 1000;
    
    private String algoritmo;
    private String tipo;
    private String orientacion;
    private int[] distribucionTropas;
    private List<String> unidades;
    private int tamanoTablero;
    
    public ValidadorParametros(String[] args) {
        analizarArgumentos(args);
    }
    
    private void analizarArgumentos(String[] args) {
        // Initialize with default values
        algoritmo = "Invalido";
        tipo = "Invalido";
        orientacion = "Invalido";
        distribucionTropas = new int[0];
        unidades = new ArrayList<>();
        tamanoTablero = TAMANO_TABLERO_DEFAULT;
        
        if (args.length == 0) {
            return;
        }
        
        for (String arg : args) {
            if (arg.startsWith("a=")) {
                algoritmo = validarAlgoritmo(arg.substring(2));
            } else if (arg.startsWith("t=")) {
                tipo = validarTipo(arg.substring(2));
            } else if (arg.startsWith("o=")) {
                orientacion = validarOrientacion(arg.substring(2));
            } else if (arg.startsWith("u=") || arg.startsWith("r=")) {
                distribucionTropas = analizarDistribucionTropas(arg.substring(2));
                unidades = GestorTropas.crearUnidadesDesdeDistribucion(distribucionTropas);
            } else if (arg.startsWith("f=")) {
                tamanoTablero = validarTamanoTablero(arg.substring(2));
            }
        }
    }
    
    private String validarAlgoritmo(String alg) {
        for (String algValido : ALGORITMOS_VALIDOS) {
            if (algValido.equalsIgnoreCase(alg)) {
                return alg.toLowerCase();
            }
        }
        return "Invalido";
    }
    
    private String validarTipo(String t) {
        for (String tipoValido : TIPOS_VALIDOS) {
            if (tipoValido.equalsIgnoreCase(t)) {
                return t.toLowerCase();
            }
        }
        return "Invalido";
    }
    
    private String validarOrientacion(String o) {
        for (String orientacionValida : ORIENTACIONES_VALIDAS) {
            if (orientacionValida.equalsIgnoreCase(o)) {
                return o.toLowerCase();
            }
        }
        return "Invalido";
    }
    
    private int validarTamanoTablero(String f) {
        try {
            int tamano = Integer.parseInt(f);
            if (tamano >= TAMANO_TABLERO_MIN && tamano <= TAMANO_TABLERO_MAX) {
                return tamano;
            }
        } catch (NumberFormatException e) {
            // Formato invalido de numeros
        }
        return TAMANO_TABLERO_DEFAULT;
    }
    
    private int[] analizarDistribucionTropas(String distribucionStr) {
        if (distribucionStr == null || distribucionStr.trim().isEmpty()) {
            return new int[0];
        }
        
        String[] partes = distribucionStr.split(",");
        int[] distribucion = new int[partes.length];
        
        for (int i = 0; i < partes.length; i++) {
            try {
                distribucion[i] = Integer.parseInt(partes[i].trim());
            } catch (NumberFormatException e) {
                distribucion[i] = 0;
            }
        }
        
        return distribucion;
    }
    
    public boolean sonParametrosValidos() {
        return !algoritmo.equals("Invalido") && 
               !tipo.equals("Invalido") && 
               !orientacion.equals("Invalido") && 
               GestorTropas.esDistribucionValida(distribucionTropas) &&
               !unidades.isEmpty() &&
               unidades.size() <= tamanoTablero * tamanoTablero;
    }
    
    public String getAlgoritmo() {
        return algoritmo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getOrientacion() {
        return orientacion;
    }
    
    public int[] getDistribucionTropas() {
        return distribucionTropas.clone();
    }
    
    public List<String> getUnidades() {
        return new ArrayList<>(unidades);
    }
    
    public int getTamanoTablero() {
        return tamanoTablero;
    }
    
    public String obtenerNombreAlgoritmo() {
        switch (algoritmo) {
            case "s": return "Selection sort";
            case "b": return "Bubble sort";
            case "i": return "Insertion sort";
            case "m": return "Merge sort";
            default: return "Invalido";
        }
    }
    
    public String obtenerNombreTipo() {
        switch (tipo) {
            case "c": return "Character";
            case "n": return "Number";
            default: return "Invalido";
        }
    }
    
    public String obtenerNombreOrientacion() {
        switch (orientacion) {
            case "n": return "Norte";
            case "s": return "Sud";
            case "e": return "Este";
            case "w": return "Oeste";
            default: return "Invalido";
        }
    }
    
    public int obtenerTotalTropas() {
        return GestorTropas.obtenerTotalTropas(distribucionTropas);
    }
}