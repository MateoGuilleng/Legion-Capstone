import java.util.*;

public class CampoBatalla {
    private String[][] campoBatalla;
    private int tamanoTablero;
    
    public CampoBatalla(int tamanoTablero) {
        this.tamanoTablero = tamanoTablero;
        campoBatalla = new String[tamanoTablero][tamanoTablero];
        inicializarCampo();
    }
    
    private void inicializarCampo() {
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                campoBatalla[i][j] = "*";
            }
        }
    }
    
    public void colocarUnidadesAleatoriamente(List<String> unidades) {
        inicializarCampo();
        Random aleatorio = new Random();
        List<String> unidadesCopia = new ArrayList<>(unidades);
        
        while (!unidadesCopia.isEmpty()) {
            int fila = aleatorio.nextInt(tamanoTablero);
            int columna = aleatorio.nextInt(tamanoTablero);
            
            if (campoBatalla[fila][columna].equals("*")) {
                campoBatalla[fila][columna] = unidadesCopia.remove(0);
            }
        }
    }
    
    public void colocarUnidadesOrdenadas(List<String> unidadesOrdenadas, String orientacion) {
        inicializarCampo();
        
        // Agrupa las unidades por tipo y cuenta cuántas hay
        Map<String, Integer> contadorUnidades = new HashMap<>();
        for (String unidad : unidadesOrdenadas) {
            contadorUnidades.put(unidad, contadorUnidades.getOrDefault(unidad, 0) + 1);
        }
        
        // Ordena las unidades por prioridad (Comandante, Medico, Tanque, Sniper, Infanteria)
        List<String> tiposOrdenados = new ArrayList<>(contadorUnidades.keySet());
        tiposOrdenados.sort(Comparator.comparingInt(GestorTropas::obtenerPrioridadTropa));
        
        switch (orientacion.toLowerCase()) {
            case "n": // Sur a Norte (abajo a arriba)
                colocarUnidadesNorte(tiposOrdenados, contadorUnidades);
                break;
            case "s": // Norte a Sur (arriba a abajo)
                colocarUnidadesSur(tiposOrdenados, contadorUnidades);
                break;
            case "e": // Oeste a Este (izquierda a derecha)
                colocarUnidadesEste(tiposOrdenados, contadorUnidades);
                break;
            case "w": // Este a Oeste (derecha a izquierda)
                colocarUnidadesOeste(tiposOrdenados, contadorUnidades);
                break;
        }
    }
    
    private void colocarUnidadesSur(List<String> tiposOrdenados, Map<String, Integer> contadorUnidades) {
        // Coloca las unidades de arriba a abajo, de izquierda a derecha
        int columna = 0;
        for (String tipoUnidad : tiposOrdenados) {
            int cantidad = contadorUnidades.get(tipoUnidad);
            
            while (cantidad > 0 && columna < tamanoTablero) {
                int unidadesEnEstaColumna = Math.min(cantidad, tamanoTablero);
                
                for (int fila = 0; fila < tamanoTablero && unidadesEnEstaColumna > 0; fila++) {
                    campoBatalla[fila][columna] = tipoUnidad;
                    unidadesEnEstaColumna--;
                }
                
                cantidad -= Math.min(cantidad, tamanoTablero);
                columna++;
            }
        }
    }
    
    private void colocarUnidadesNorte(List<String> tiposOrdenados, Map<String, Integer> contadorUnidades) {
        // Coloca las unidades de abajo a arriba, de izquierda a derecha
        int columna = 0;
        for (String tipoUnidad : tiposOrdenados) {
            int cantidad = contadorUnidades.get(tipoUnidad);
            
            while (cantidad > 0 && columna < tamanoTablero) {
                int unidadesEnEstaColumna = Math.min(cantidad, tamanoTablero);
                
                for (int fila = tamanoTablero - 1; fila >= 0 && unidadesEnEstaColumna > 0; fila--) {
                    campoBatalla[fila][columna] = tipoUnidad;
                    unidadesEnEstaColumna--;
                }
                
                cantidad -= Math.min(cantidad, tamanoTablero);
                columna++;
            }
        }
    }
    
    private void colocarUnidadesEste(List<String> tiposOrdenados, Map<String, Integer> contadorUnidades) {
        // Coloca las unidades de izquierda a derecha, de arriba a abajo
        int fila = 0;
        for (String tipoUnidad : tiposOrdenados) {
            int cantidad = contadorUnidades.get(tipoUnidad);
            
            while (cantidad > 0 && fila < tamanoTablero) {
                int unidadesEnEstaFila = Math.min(cantidad, tamanoTablero);
                
                for (int columna = 0; columna < tamanoTablero && unidadesEnEstaFila > 0; columna++) {
                    campoBatalla[fila][columna] = tipoUnidad;
                    unidadesEnEstaFila--;
                }
                
                cantidad -= Math.min(cantidad, tamanoTablero);
                fila++;
            }
        }
    }
    
    private void colocarUnidadesOeste(List<String> tiposOrdenados, Map<String, Integer> contadorUnidades) {
        // Coloca las unidades de derecha a izquierda, de abajo a arriba
        int fila = tamanoTablero - 1;
        for (String tipoUnidad : tiposOrdenados) {
            int cantidad = contadorUnidades.get(tipoUnidad);
            
            while (cantidad > 0 && fila >= 0) {
                int unidadesEnEstaFila = Math.min(cantidad, tamanoTablero);
                
                for (int columna = tamanoTablero - 1; columna >= 0 && unidadesEnEstaFila > 0; columna--) {
                    campoBatalla[fila][columna] = tipoUnidad;
                    unidadesEnEstaFila--;
                }
                
                cantidad -= Math.min(cantidad, tamanoTablero);
                fila--;
            }
        }
    }
    
    public void mostrarCampo() {
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                System.out.print(campoBatalla[i][j]);
                if (j < tamanoTablero - 1) {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
    }
    
    public int getTamanoTablero() {
        return tamanoTablero;
    }
} 