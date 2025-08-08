import java.util.*;

public class Troops {
    private static final int TAMANO_TABLERO = 6;
    
    private ValidadorParametros validador;
    private CampoBatalla campoBatalla;
    
    public Troops(String[] args) {
        this.validador = new ValidadorParametros(args);
        this.campoBatalla = new CampoBatalla();
    }
    
    public void ejecutar() {
        if (!validador.sonParametrosValidos()) {
            mostrarError();
            return;
        }
        
        mostrarInformacion();
        
        campoBatalla.colocarUnidadesAleatoriamente(validador.getUnidades());
        System.out.println("Initial Position:");
        campoBatalla.mostrarCampo();
        
        List<String> unidadesOrdenadas = AlgoritmoOrdenamiento.ordenarUnidades(
            validador.getUnidades(), 
            validador.getTipo(), 
            validador.getAlgoritmo()
        );
        
        campoBatalla.colocarUnidadesOrdenadas(unidadesOrdenadas);
        System.out.println("Final Position:");
        campoBatalla.mostrarCampo();
    }
    
    private void mostrarError() {
        System.out.println("Algorithm: [" + validador.getAlgoritmo() + "]");
        System.out.println("Type: [" + validador.getTipo() + "]");
        System.out.println("Troops: [" + validador.getUnidades().size() + "]");
        System.out.println("Valores Invalidos");
    }
    
    private void mostrarInformacion() {
        System.out.println("Algorithm: [" + validador.obtenerNombreAlgoritmo() + "]");
        System.out.println("Type: [" + validador.obtenerNombreTipo() + "]");
        System.out.println("Troops: [" + validador.getUnidades().size() + "]");
        System.out.println("Battlefield: [" + TAMANO_TABLERO + " x " + TAMANO_TABLERO + "]");
    }
    
    public static void main(String[] args) {
        Troops tropas = new Troops(args);
        tropas.ejecutar();
    }
} 