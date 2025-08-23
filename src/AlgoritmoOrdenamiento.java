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
            case "q": ordenamientoRapidoNumeros(numeros); break;
            case "h": ordenamientoMontonNumeros(numeros); break;
            case "c": ordenamientoConteoNumeros(numeros); break;
            case "r": ordenamientoRadixNumeros(numeros); break;
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
            case "q": ordenamientoRapidoCaracteres(caracteres); break;
            case "h": ordenamientoMontonCaracteres(caracteres); break;
            case "c": ordenamientoConteoCaracteres(caracteres); break;
            case "r": ordenamientoRadixCaracteres(caracteres); break;
        }
    }
    
    // ========== ALGORITMOS DE ORDENAMIENTO NUMÉRICOS ==========
    
    // Selection Sort for numbers
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
    
    // Bubble Sort for numbers
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
    
    // Insertion Sort for numbers (existing)
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
    
    // Merge Sort for numbers
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
    
    // Quick Sort for numbers
    private static void ordenamientoRapidoNumeros(List<Integer> arr) {
        ordenamientoRapidoNumeros(arr, 0, arr.size() - 1);
    }
    
    private static void ordenamientoRapidoNumeros(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = particionRapidaNumeros(arr, low, high);
            ordenamientoRapidoNumeros(arr, low, pi - 1);
            ordenamientoRapidoNumeros(arr, pi + 1, high);
        }
    }
    
    private static int particionRapidaNumeros(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }
    
    // Heap Sort for numbers
    private static void ordenamientoMontonNumeros(List<Integer> arr) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyNumeros(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);
            heapifyNumeros(arr, i, 0);
        }
    }
    
    private static void heapifyNumeros(List<Integer> arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr.get(left) > arr.get(largest)) {
            largest = left;
        }
        if (right < n && arr.get(right) > arr.get(largest)) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, temp);
            heapifyNumeros(arr, n, largest);
        }
    }
    
    // Counting Sort for numbers
    private static void ordenamientoConteoNumeros(List<Integer> arr) {
        if (arr.isEmpty()) return;
        
        int max = Collections.max(arr);
        int min = Collections.min(arr);
        int range = max - min + 1;
        
        int[] count = new int[range];
        List<Integer> output = new ArrayList<>(Collections.nCopies(arr.size(), 0));
        
        for (int i = 0; i < arr.size(); i++) {
            count[arr.get(i) - min]++;
        }
        
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = arr.size() - 1; i >= 0; i--) {
            output.set(count[arr.get(i) - min] - 1, arr.get(i));
            count[arr.get(i) - min]--;
        }
        
        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, output.get(i));
        }
    }
    
    // Radix Sort for numbers
    private static void ordenamientoRadixNumeros(List<Integer> arr) {
        if (arr.isEmpty()) return;
        
        int max = Collections.max(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            ordenamientoConteoPorDigito(arr, exp);
        }
    }
    
    private static void ordenamientoConteoPorDigito(List<Integer> arr, int exp) {
        int n = arr.size();
        int[] output = new int[n];
        int[] count = new int[10];
        
        for (int i = 0; i < n; i++) {
            count[(arr.get(i) / exp) % 10]++;
        }
        
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr.get(i) / exp) % 10] - 1] = arr.get(i);
            count[(arr.get(i) / exp) % 10]--;
        }
        
        for (int i = 0; i < n; i++) {
            arr.set(i, output[i]);
        }
    }
    
    // ========== ALGORITMOS DE ORDENAMIENTO DE CARACTERES ==========
    
    // Selection Sort for characters
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
    
    // Bubble Sort for characters
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
    
    // Insertion Sort for characters (existing)
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
    
    // Merge Sort for characters
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
    
    // Quick Sort for characters
    private static void ordenamientoRapidoCaracteres(List<String> arr) {
        ordenamientoRapidoCaracteres(arr, 0, arr.size() - 1);
    }
    
    private static void ordenamientoRapidoCaracteres(List<String> arr, int low, int high) {
        if (low < high) {
            int pi = particionRapidaCaracteres(arr, low, high);
            ordenamientoRapidoCaracteres(arr, low, pi - 1);
            ordenamientoRapidoCaracteres(arr, pi + 1, high);
        }
    }
    
    private static int particionRapidaCaracteres(List<String> arr, int low, int high) {
        String pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j).compareTo(pivot) < 0) {
                i++;
                String temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        String temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }
    
    // Heap Sort for characters
    private static void ordenamientoMontonCaracteres(List<String> arr) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyCaracteres(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            String temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);
            heapifyCaracteres(arr, i, 0);
        }
    }
    
    private static void heapifyCaracteres(List<String> arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr.get(left).compareTo(arr.get(largest)) > 0) {
            largest = left;
        }
        if (right < n && arr.get(right).compareTo(arr.get(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            String temp = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, temp);
            heapifyCaracteres(arr, n, largest);
        }
    }
    
    // Counting Sort for characters
    private static void ordenamientoConteoCaracteres(List<String> arr) {
        if (arr.isEmpty()) return;
        
        // Encuentra el rango de caracteres
        char min = arr.get(0).charAt(0);
        char max = arr.get(0).charAt(0);
        for (String s : arr) {
            if (s.length() > 0) {
                char c = s.charAt(0);
                if (c < min) min = c;
                if (c > max) max = c;
            }
        }
        
        int range = max - min + 1;
        int[] count = new int[range];
        List<String> output = new ArrayList<>(Collections.nCopies(arr.size(), ""));
        
        for (String s : arr) {
            if (s.length() > 0) {
                count[s.charAt(0) - min]++;
            }
        }
        
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i).length() > 0) {
                char c = arr.get(i).charAt(0);
                output.set(count[c - min] - 1, arr.get(i));
                count[c - min]--;
            }
        }
        
        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, output.get(i));
        }
    }
    
    // Radix Sort for characters
    private static void ordenamientoRadixCaracteres(List<String> arr) {
        if (arr.isEmpty()) return;
        
        // Find the maximum length of strings
        int maxLength = 0;
        for (String s : arr) {
            maxLength = Math.max(maxLength, s.length());
        }
        
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            ordenamientoConteoPorCaracter(arr, pos);
        }
    }
    
    private static void ordenamientoConteoPorCaracter(List<String> arr, int pos) {
        int n = arr.size();
        String[] output = new String[n];
        int[] count = new int[128]; // ASCII characters
        
        for (String s : arr) {
            char c = (pos < s.length()) ? s.charAt(pos) : 0;
            count[c]++;
        }
        
        for (int i = 1; i < 128; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            char c = (pos < arr.get(i).length()) ? arr.get(i).charAt(pos) : 0;
            output[count[c] - 1] = arr.get(i);
            count[c]--;
        }
        
        for (int i = 0; i < n; i++) {
            arr.set(i, output[i]);
        }
    }
} 