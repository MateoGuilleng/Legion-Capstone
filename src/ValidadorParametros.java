import java.util.*;

public class ValidadorParametros {
    private static final String[] ALGORITMOS_VALIDOS = {"i"};
    private static final String[] TIPOS_VALIDOS = {"c", "n"};
    private static final int TAMANO_TABLERO = 6;
    
    private String algoritmo;
    private String tipo;
    private List<String> unidades;
    
    public ValidadorParametros(String[] args) {
        analizarArgumentos(args);
    }
    
    private void analizarArgumentos(String[] args) {
        algoritmo = "Invalido";
        tipo = "Invalido";
        unidades = new ArrayList<>();
        
        if (args.length == 0) {
            return;
        }
        
        for (String arg : args) {
            if (arg.startsWith("a=")) {
                algoritmo = validarAlgoritmo(arg.substring(2));
            } else if (arg.startsWith("t=")) {
                tipo = validarTipo(arg.substring(2));
            } else if (arg.startsWith("u=") || arg.startsWith("r=")) {
                unidades = analizarUnidades(arg.substring(2));
            }
        }
    }
    
    private String validarAlgoritmo(String alg) {
        for (String algValido : ALGORITMOS_VALIDOS) {
            if (algValido.equals(alg)) {
                return alg;
            }
        }
        return "Invalido";
    }
    
    private String validarTipo(String t) {
        for (String tipoValido : TIPOS_VALIDOS) {
            if (tipoValido.equals(t)) {
                return t;
            }
        }
        return "Invalido";
    }
    
    private List<String> analizarUnidades(String unidadesStr) {
        List<String> resultado = new ArrayList<>();
        if (unidadesStr == null || unidadesStr.trim().isEmpty()) {
            return resultado;
        }
        
        String[] partes = unidadesStr.split(",");
        for (String parte : partes) {
            parte = parte.trim();
            if (!parte.isEmpty()) {
                resultado.add(parte);
            }
        }
        
        if (resultado.size() > TAMANO_TABLERO * TAMANO_TABLERO) {
            return new ArrayList<>();
        }
        
        return resultado;
    }
    
    public boolean sonParametrosValidos() {
        return !algoritmo.equals("Invalido") && !tipo.equals("Invalido") && !unidades.isEmpty();
    }
    
    public String getAlgoritmo() {
        return algoritmo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public List<String> getUnidades() {
        return new ArrayList<>(unidades);
    }
    
    public String obtenerNombreAlgoritmo() {
        switch (algoritmo) {
            case "i": return "Insertion sort";
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
} 