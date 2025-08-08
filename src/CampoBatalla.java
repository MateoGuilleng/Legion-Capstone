import java.util.*;

public class CampoBatalla {
    private static final int TAMANO_TABLERO = 6;
    private String[][] campoBatalla;
    
    public CampoBatalla() {
        campoBatalla = new String[TAMANO_TABLERO][TAMANO_TABLERO];
        inicializarCampo();
    }
    
    private void inicializarCampo() {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                campoBatalla[i][j] = "*";
            }
        }
    }
    
    public void colocarUnidadesAleatoriamente(List<String> unidades) {
        inicializarCampo();
        Random aleatorio = new Random();
        List<String> unidadesCopia = new ArrayList<>(unidades);
        
        while (!unidadesCopia.isEmpty()) {
            int fila = aleatorio.nextInt(TAMANO_TABLERO);
            int columna = aleatorio.nextInt(TAMANO_TABLERO);
            
            if (campoBatalla[fila][columna].equals("*")) {
                campoBatalla[fila][columna] = unidadesCopia.remove(0);
            }
        }
    }
    
    public void colocarUnidadesOrdenadas(List<String> unidadesOrdenadas) {
        inicializarCampo();
        
        Map<String, Integer> contadorUnidades = new HashMap<>();
        for (String unidad : unidadesOrdenadas) {
            contadorUnidades.put(unidad, contadorUnidades.getOrDefault(unidad, 0) + 1);
        }
        
        int columna = 0;
        for (Map.Entry<String, Integer> entrada : contadorUnidades.entrySet()) {
            String tipoUnidad = entrada.getKey();
            int cantidad = entrada.getValue();
            
            while (cantidad > 0 && columna < TAMANO_TABLERO) {
                int unidadesEnEstaColumna = Math.min(cantidad, TAMANO_TABLERO);
                
                for (int fila = TAMANO_TABLERO - 1; fila >= 0 && unidadesEnEstaColumna > 0; fila--) {
                    campoBatalla[fila][columna] = tipoUnidad;
                    unidadesEnEstaColumna--;
                }
                
                cantidad -= Math.min(cantidad, TAMANO_TABLERO);
                columna++;
            }
        }
    }
    
    public void mostrarCampo() {
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            for (int j = 0; j < TAMANO_TABLERO; j++) {
                System.out.print(campoBatalla[i][j]);
                if (j < TAMANO_TABLERO - 1) {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
    }
} 