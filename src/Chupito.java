/**
 * Clase Chupito - Sistema de Fortuna Temporal
 * 
 * Mecánica del juego:
 * - El jugador puede beber hasta 3 chupitos en una partida
 * - Al beber 3 chupitos, se activa el efecto "Fortuna" (mayor probabilidad de
 * ganar)
 * - El efecto dura solo UNA partida
 * - Si bebe en DOS partidas consecutivas, se desmaya y pierde el 30% del dinero
 * 
 * @author Luis Miguel
 * @version 1.0
 */
public class Chupito {
    // Atributos privados
    private int contadorChupitos; // Contador de chupitos bebidos en la ronda actual
    private int rondasConEfectoSeguidas; // Contador de rondas consecutivas con efecto
    private boolean efectoFortuna; // Estado del efecto fortuna

    /**
     * Constructor por defecto
     */
    public Chupito() {
        this.contadorChupitos = 0;
        this.rondasConEfectoSeguidas = 0;
        this.efectoFortuna = false;
    }

    /**
     * Permite al jugador beber un chupito
     * Al llegar a 3, se activa el efecto fortuna
     * 
     * @return true si se pudo beber, false si ya bebió 3
     */
    public boolean beber() {
        if (contadorChupitos < 3) {
            contadorChupitos++;
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│ ¡Has bebido un chupito!            │");
            System.out.println("│ Chupitos: " + contadorChupitos + "/3                    │");
            System.out.println("└────────────────────────────────────┘");

            // Al beber el tercer chupito, se activa la fortuna
            if (contadorChupitos == 3) {
                activarFortuna();
            }
            return true;
        } else {
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│    Ya has bebido suficiente        │");
            System.out.println("│    por ahora...                    │");
            System.out.println("└────────────────────────────────────┘");
            return false;
        }
    }

    /**
     * Activa el efecto fortuna (solo uso interno)
     */
    private void activarFortuna() {
        efectoFortuna = true;
        rondasConEfectoSeguidas++;
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│       ¡FORTUNA ACTIVADA!           │");
        System.out.println("│ Tu suerte mejora esta ronda        │");
        System.out.println("└────────────────────────────────────┘");
    }

    /**
     * Comprueba si el jugador se desmaya al final de la ronda
     * Debe llamarse desde JuegoBlackjack.java al finalizar cada ronda
     * 
     * @param dineroActual el dinero actual del jugador
     * @return el nuevo dinero del jugador (puede ser menor si se desmayó)
     */
    public double comprobarDesmayo(double dineroActual) {
        // Si bebió en dos rondas consecutivas, se desmaya
        if (efectoFortuna && rondasConEfectoSeguidas >= 2) {
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│       ¡TE HAS DESMAYADO!           │");
            System.out.println("│ Te han robado mientras dormías...  │");
            System.out.println("│ Pierdes el 30% de tu dinero        │");
            System.out.println("└────────────────────────────────────┘");

            rondasConEfectoSeguidas = 0;
            return dineroActual * 0.7; // Pierde el 30%
        }

        // Si terminó la ronda sin activar fortuna, se rompe la racha
        if (!efectoFortuna) {
            rondasConEfectoSeguidas = 0;
        }

        return dineroActual; // No hay cambios en el dinero
    }

    /**
     * Resetea el estado al finalizar la ronda
     * IMPORTANTE: Debe llamarse desde JuegoBlackjack.java al final de cada ronda
     */
    public void finalizarRonda() {
        // El efecto solo dura una ronda
        this.efectoFortuna = false;
        this.contadorChupitos = 0;
    }

    /**
     * Verifica si el efecto fortuna está activo
     * Puede usarse para modificar probabilidades en el juego
     * 
     * @return true si la fortuna está activa
     */
    public boolean tieneFortuna() {
        return efectoFortuna;
    }

    /**
     * Obtiene el número de chupitos bebidos en la partida actual
     * 
     * @return número de chupitos (0-3)
     */
    public int getContadorChupitos() {
        return contadorChupitos;
    }

    /**
     * Obtiene el número de rondas consecutivas con efecto
     * 
     * @return número de rondas consecutivas
     */
    public int getRondasConEfectoSeguidas() {
        return rondasConEfectoSeguidas;
    }

    /**
     * Método toString para debugging
     */
    @Override
    public String toString() {
        return String.format("Chupito[chupitos=%d/3, fortuna=%s, racha=%d]",
                contadorChupitos,
                efectoFortuna ? "ACTIVA" : "inactiva",
                rondasConEfectoSeguidas);
    }
}
