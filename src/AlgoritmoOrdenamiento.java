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
        
        switch (algoritmo) {
            case "s": ordenamientoSeleccionNumeros(numeros); break;
            case "b": ordenamientoBurbujaNumeros(numeros); break;
            case "i": ordenamientoInsercionNumeros(numeros); break;
            case "m": ordenamientoCombinacionNumeros(numeros); break;
            default:
                throw new IllegalArgumentException("Algoritmo de ordenamiento numérico no válido: " + algoritmo);
        }
        
        List<String> resultado = new ArrayList<>();
        for (Integer num : numeros) {
            resultado.add(num.toString());
        }
        return resultado;
    }
    
    private static void ordenarCaracteres(List<String> caracteres, String algoritmo) {
        switch (algoritmo) {
            case "s": ordenamientoSeleccionCaracteres(caracteres); break;
            case "b": ordenamientoBurbujaCaracteres(caracteres); break;
            case "i": ordenamientoInsercionCaracteres(caracteres); break;
            case "m": ordenamientoCombinacionCaracteres(caracteres); break;
            default:
                throw new IllegalArgumentException("Algoritmo de ordenamiento de caracteres no válido: " + algoritmo);
        }
    }
    
    // ========== =========
    

    private static void ordenamientoSeleccionNumeros(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIdx)) {
                    minIdx = j;
                }
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(minIdx));
            arr.set(minIdx, temp);
        }
    }
    

    private static void ordenamientoBurbujaNumeros(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
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
    

    private static void ordenamientoCombinacionNumeros(List<Integer> arr) {
        if (arr.size() > 1) {
            int mid = arr.size() / 2;
            List<Integer> left = new ArrayList<>(arr.subList(0, mid));
            List<Integer> right = new ArrayList<>(arr.subList(mid, arr.size()));
            
            ordenamientoCombinacionNumeros(left);
            ordenamientoCombinacionNumeros(right);
            
            combinarNumeros(arr, left, right);
        }
    }
    
    private static void combinarNumeros(List<Integer> arr, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k++, left.get(i++));
            } else {
                arr.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            arr.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            arr.set(k++, right.get(j++));
        }
    }
    
    // ========== ==========
    

    private static void ordenamientoSeleccionCaracteres(List<String> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).compareTo(arr.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            String temp = arr.get(i);
            arr.set(i, arr.get(minIdx));
            arr.set(minIdx, temp);
        }
    }

    private static void ordenamientoBurbujaCaracteres(List<String> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    String temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
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
    

    private static void ordenamientoCombinacionCaracteres(List<String> arr) {
        if (arr.size() > 1) {
            int mid = arr.size() / 2;
            List<String> left = new ArrayList<>(arr.subList(0, mid));
            List<String> right = new ArrayList<>(arr.subList(mid, arr.size()));
            
            ordenamientoCombinacionCaracteres(left);
            ordenamientoCombinacionCaracteres(right);
            
            combinarCaracteres(arr, left, right);
        }
    }
    
    private static void combinarCaracteres(List<String> arr, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                arr.set(k++, left.get(i++));
            } else {
                arr.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            arr.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            arr.set(k++, right.get(j++));
        }
    }
} 