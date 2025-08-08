import java.util.*;

public class AlgoritmoOrdenamiento {
    
    public static List<String> ordenarUnidades(List<String> unidades, String tipo, String algoritmo) {
        List<String> unidadesCopia = new ArrayList<>(unidades);
        
        if (tipo.equals("n")) {
            return ordenarNumeros(unidadesCopia, algoritmo);
        } else {
            ordenarCaracteres(unidadesCopia, algoritmo);
            return unidadesCopia;
        }
    }
    
    private static List<String> ordenarNumeros(List<String> unidades, String algoritmo) {
        List<Integer> numeros = new ArrayList<>();
        for (String unidad : unidades) {
            try {
                numeros.add(Integer.parseInt(unidad));
            } catch (NumberFormatException e) {
                numeros.add(0);
            }
        }
        
        if (algoritmo.equals("i")) {
            ordenamientoInsercionNumeros(numeros);
        }
        
        List<String> resultado = new ArrayList<>();
        for (Integer num : numeros) {
            resultado.add(num.toString());
        }
        return resultado;
    }
    
    private static void ordenarCaracteres(List<String> caracteres, String algoritmo) {
        if (algoritmo.equals("i")) {
            ordenamientoInsercionCaracteres(caracteres);
        }
    }
    
    private static void ordenamientoInsercionNumeros(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            int clave = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > clave) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, clave);
        }
    }
    
    private static void ordenamientoInsercionCaracteres(List<String> arr) {
        for (int i = 1; i < arr.size(); i++) {
            String clave = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j).compareTo(clave) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, clave);
        }
    }
} 