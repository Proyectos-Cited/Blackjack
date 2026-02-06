/**
 * Clase Seguro - Seguro Ludópata a Todo Riesgo
 * 
 * Mecánica del juego:
 * - Se obtiene automáticamente al ganar 2 rondas consecutivas
 * - Permite anular UNA pérdida por pasarse de 21
 * - Al usarse, se consume y el jugador debe volver a ganar 2 seguidas
 * 
 * @author Luis Miguel
 * @version 1.0
 */
public class Seguro {
    // Atributos privados
    private int victoriasConsecutivas; // Contador de victorias seguidas
    private boolean seguroActivo; // Estado del seguro

    /**
     * Constructor por defecto
     */
    public Seguro() {
        this.victoriasConsecutivas = 0;
        this.seguroActivo = false;
    }

    /**
     * Registra una victoria del jugador
     * Al alcanzar 2 victorias consecutivas, el seguro se activa automáticamente
     * IMPORTANTE: Debe llamarse desde JuegoBlackjack.java cuando el jugador gane
     */
    public void registrarVictoria() {
        victoriasConsecutivas++;

        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│ ✓ Victoria registrada              │");
        System.out.println("│   Racha: " + victoriasConsecutivas + " victoria(s)            │");
        System.out.println("└────────────────────────────────────┘");

        // Se activa al ganar 2 seguidas
        if (victoriasConsecutivas >= 2 && !seguroActivo) {
            activarSeguro();
        }
    }

    /**
     * Activa el seguro (solo uso interno)
     */
    private void activarSeguro() {
        seguroActivo = true;
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│ 🛡️  ¡SEGURO OBTENIDO! 🛡️           │");
        System.out.println("│                                    │");
        System.out.println("│ SEGURO LUDÓPATA A TODO RIESGO      │");
        System.out.println("│                                    │");
        System.out.println("│ Protege contra 1 pérdida por       │");
        System.out.println("│ pasarse de 21                      │");
        System.out.println("└────────────────────────────────────┘");
    }

    /**
     * Registra una derrota del jugador
     * Si NO tiene seguro activo, pierde la racha de victorias
     * IMPORTANTE: Debe llamarse desde JuegoBlackjack.java cuando el jugador pierda
     */
    public void registrarDerrota() {
        // Si no tiene seguro, pierde la racha
        if (!seguroActivo) {
            if (victoriasConsecutivas > 0) {
                System.out.println("┌────────────────────────────────────┐");
                System.out.println("│ ✗ Racha de victorias perdida       │");
                System.out.println("└────────────────────────────────────┘");
            }
            victoriasConsecutivas = 0;
        }
    }

    /**
     * Intenta usar el seguro cuando el jugador se pasa de 21
     * IMPORTANTE: Debe llamarse desde Mano.java o JuegoBlackjack.java
     * cuando calcularPuntos() > 21
     * 
     * @return true si el seguro se activó (anula la pérdida), false si no tiene
     *         seguro
     */
    public boolean usarSeguro() {
        if (seguroActivo) {
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│ 🛡️  ¡SEGURO ACTIVADO! 🛡️            │");
            System.out.println("│                                    │");
            System.out.println("│ Se anula tu pérdida por pasarte    │");
            System.out.println("│ de 21                              │");
            System.out.println("│                                    │");
            System.out.println("│ El seguro se ha consumido          │");
            System.out.println("└────────────────────────────────────┘");

            // El seguro se consume al usarse
            seguroActivo = false;
            victoriasConsecutivas = 0;
            return true;
        }

        // No tiene seguro disponible
        return false;
    }

    /**
     * Verifica si el seguro está activo
     * 
     * @return true si el seguro está disponible
     */
    public boolean isSeguroActivo() {
        return seguroActivo;
    }

    /**
     * Obtiene el número de victorias consecutivas
     * 
     * @return número de victorias seguidas
     */
    public int getVictoriasConsecutivas() {
        return victoriasConsecutivas;
    }

    /**
     * Resetea completamente el estado del seguro
     * Útil para pruebas o reinicio del juego
     */
    public void resetear() {
        this.victoriasConsecutivas = 0;
        this.seguroActivo = false;
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│ Seguro reseteado                   │");
        System.out.println("└────────────────────────────────────┘");
    }

    /**
     * Muestra el estado actual del seguro
     */
    public void mostrarEstado() {
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│ ESTADO DEL SEGURO                  │");
        System.out.println("│────────────────────────────────────│");
        System.out.printf("│ Seguro activo: %-19s │%n",
                seguroActivo ? "SÍ" : "NO");
        System.out.printf("│ Victorias consecutivas: %-10d │%n",
                victoriasConsecutivas);
        if (!seguroActivo && victoriasConsecutivas == 1) {
            System.out.println("│ ¡Una victoria más para el seguro!  │");
        }
        System.out.println("└────────────────────────────────────┘");
    }

    /**
     * Método toString para debugging
     */
    @Override
    public String toString() {
        return String.format("Seguro[victorias=%d, activo=%s]",
                victoriasConsecutivas,
                seguroActivo ? "SÍ" : "NO");
    }
}
