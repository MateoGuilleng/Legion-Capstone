import java.util.*;

public class Troops {
    private ValidadorParametros validador;
    private CampoBatalla campoBatalla;
    
    public Troops(String[] args) {
        this.validador = new ValidadorParametros(args);
        this.campoBatalla = new CampoBatalla(validador.getTamanoTablero());
    }
    
    public void ejecutar() {
        if (!validador.sonParametrosValidos()) {
            mostrarError();
            return;
        }
        
        mostrarInformacion();
        
        // Convertir tipos de tropas en símbolos para su visualización
        List<String> unidadesSimbolos = GestorTropas.convertirTiposASimbolos(validador.getUnidades());
        
        campoBatalla.colocarUnidadesAleatoriamente(unidadesSimbolos);
        System.out.println("Initial Position:");
        campoBatalla.mostrarCampo();
        
        List<String> unidadesOrdenadas = AlgoritmoOrdenamiento.ordenarUnidades(
            validador.getUnidades(), 
            validador.getTipo(), 
            validador.getAlgoritmo()
        );
        
        // Convertir tipos de tropas ordenados en símbolos
        List<String> unidadesOrdenadasSimbolos = GestorTropas.convertirTiposASimbolos(unidadesOrdenadas);
        
        campoBatalla.colocarUnidadesOrdenadas(unidadesOrdenadasSimbolos, validador.getOrientacion());
        System.out.println("Final Position:");
        campoBatalla.mostrarCampo();
    }
    
    private void mostrarError() {
        System.out.println("Algorithm: [" + validador.getAlgoritmo() + "]");
        System.out.println("Type: [" + validador.getTipo() + "]");
        System.out.println("Orientation: [" + validador.getOrientacion() + "]");
        System.out.println("Troops: [" + validador.obtenerTotalTropas() + "]");
        System.out.println("Valores Invalidos");
    }
    
    private void mostrarInformacion() {
        System.out.println("Algorithm: [" + validador.obtenerNombreAlgoritmo() + "]");
        System.out.println("Type: [" + validador.obtenerNombreTipo() + "]");
        System.out.println("Orientation: [" + validador.obtenerNombreOrientacion() + "]");
        System.out.println("Troops: [" + validador.obtenerTotalTropas() + "]");
        System.out.println("Battlefield: [" + validador.getTamanoTablero() + " x " + validador.getTamanoTablero() + "]");
    }
    
    public static void main(String[] args) {
        Troops tropas = new Troops(args);
        tropas.ejecutar();
    }
} 