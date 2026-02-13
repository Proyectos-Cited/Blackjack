/**
 * Seguro Ludópata a Todo Riesgo.
 * Se activa tras 2 victorias seguidas.
 * Anula 1 pérdida por pasarse de 21.
 */
public class Seguro {
    private int victoriasConsecutivas;
    private boolean seguroActivo;

    public Seguro() {
        this.victoriasConsecutivas = 0;
        this.seguroActivo = false;
    }

    public void registrarVictoria() {
        victoriasConsecutivas++;

        System.out.println("+------------------------------------+");
        System.out.println("|  Victoria registrada               |");
        System.out.println("|   Racha: " + victoriasConsecutivas + " victoria(s)             |");
        System.out.println("+------------------------------------+");

        if (victoriasConsecutivas >= 2 && !seguroActivo) {
            activarSeguro();
        }
    }

    private void activarSeguro() {
        seguroActivo = true;
        System.out.println("+------------------------------------+");
        System.out.println("|   ¡SEGURO OBTENIDO!                |");
        System.out.println("|                                    |");
        System.out.println("| SEGURO LUDÓPATA A TODO RIESGO      |");
        System.out.println("|                                    |");
        System.out.println("| Protege contra 1 pérdida por       |");
        System.out.println("| pasarse de 21                      |");
        System.out.println("+------------------------------------+");
    }

    public void registrarDerrota() {
        if (victoriasConsecutivas > 0) {
            System.out.println("+------------------------------------+");
            System.out.println("| Racha de victorias perdida         |");
            System.out.println("+------------------------------------+");
        }
        victoriasConsecutivas = 0;
    }

    public boolean usarSeguro() {
        if (seguroActivo) {
            System.out.println("+------------------------------------+");
            System.out.println("|   ¡SEGURO ACTIVADO!                |");
            System.out.println("|                                    |");
            System.out.println("| Se anula tu pérdida por pasarte    |");
            System.out.println("| de 21                              |");
            System.out.println("|                                    |");
            System.out.println("| El seguro se ha consumido          |");
            System.out.println("+------------------------------------+");

            seguroActivo = false;
            victoriasConsecutivas = 0;
            return true;
        }

        return false;
    }

    public boolean usarSeguro(boolean usarAhora) {
        if (!usarAhora) {
            return false;
        }
        return usarSeguro();
    }

    public boolean isSeguroActivo() {
        return seguroActivo;
    }

    public int getVictoriasConsecutivas() {
        return victoriasConsecutivas;
    }

    public void resetear() {
        this.victoriasConsecutivas = 0;
        this.seguroActivo = false;
    }

    public void mostrarEstado() {
        System.out.println("+------------------------------------+");
        System.out.println("| ESTADO DEL SEGURO                  |");
        System.out.println("|------------------------------------|");
        System.out.printf("| Seguro activo: %-19s |%n",
                seguroActivo ? "SÍ" : "NO");
        System.out.printf("| Victorias consecutivas: %-10d |%n",
                victoriasConsecutivas);
        if (!seguroActivo && victoriasConsecutivas == 1) {
            System.out.println("| ¡Una victoria más para el seguro!  |");
        }
        System.out.println("+------------------------------------+");
    }

    @Override
    public String toString() {
        return String.format("Seguro[victorias=%d, activo=%s]",
                victoriasConsecutivas,
                seguroActivo ? "SÍ" : "NO");
    }
}
