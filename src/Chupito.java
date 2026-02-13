/**
 * Chupito: fortuna temporal.
 * Hasta 3 por partida; al 3 se activa fortuna.
 * Dos partidas seguidas bebiendo: desmayo.
 */
public class Chupito {
    private int contadorChupitos;
    private int rondasConChupitoSeguidas;
    private boolean efectoFortuna;
    private boolean bebioEnRonda;
    private double costoChupito;
    private double bonusFortuna;

    private static final double BONUS_FORTUNA_DEF = 0.15;

    public Chupito() {
        this(0.0, BONUS_FORTUNA_DEF);
    }

    public Chupito(double costoChupito) {
        this(costoChupito, BONUS_FORTUNA_DEF);
    }

    public Chupito(double costoChupito, double bonusFortuna) {
        this.contadorChupitos = 0;
        this.rondasConChupitoSeguidas = 0;
        this.efectoFortuna = false;
        this.bebioEnRonda = false;
        this.costoChupito = Math.max(0.0, costoChupito);
        this.bonusFortuna = clamp01(bonusFortuna);
    }

    public boolean beber() {
        if (contadorChupitos < 3) {
            contadorChupitos++;
            bebioEnRonda = true;
            System.out.println("+------------------------------------+");
            System.out.println("| Has bebido un chupito!             |");
            System.out.println("| Chupitos: " + contadorChupitos + "/3                    |");
            System.out.println("+------------------------------------+");
            if (contadorChupitos == 3) {
                activarFortuna();
            }
            return true;
        } else {
            System.out.println("+------------------------------------+");
            System.out.println("|    Ya has bebido suficiente        |");
            System.out.println("|    por ahora...                    |");
            System.out.println("+------------------------------------+");
            return false;
        }
    }

    public double beber(double dineroActual) {
        if (costoChupito <= 0.0) {
            beber();
            return dineroActual;
        }
        if (dineroActual < costoChupito) {
            System.out.println("No tienes dinero suficiente para beber.");
            return dineroActual;
        }
        if (beber()) {
            return dineroActual - costoChupito;
        }
        return dineroActual;
    }

    private void activarFortuna() {
        efectoFortuna = true;
        System.out.println("+------------------------------------+");
        System.out.println("|       FORTUNA ACTIVADA!            |");
        System.out.println("| Tu suerte mejora esta ronda        |");
        System.out.println("+------------------------------------+");
    }

    public double comprobarDesmayo(double dineroActual) {
        if (bebioEnRonda) {
            rondasConChupitoSeguidas++;
        } else {
            rondasConChupitoSeguidas = 0;
        }

        if (rondasConChupitoSeguidas >= 2) {
            System.out.println("+------------------------------------+");
            System.out.println("|       TE HAS DESMAYADO!            |");
            System.out.println("| Te han robado mientras dormias...  |");
            System.out.println("| Pierdes el 30% de tu dinero        |");
            System.out.println("+------------------------------------+");

            rondasConChupitoSeguidas = 0;
            return dineroActual * 0.7;
        }
        return dineroActual;
    }

    public double finalizarRonda(double dineroActual) {
        double nuevoDinero = comprobarDesmayo(dineroActual);
        finalizarRonda();
        return nuevoDinero;
    }

    public void finalizarRonda() {
        this.efectoFortuna = false;
        this.contadorChupitos = 0;
        this.bebioEnRonda = false;
    }

    public boolean tieneFortuna() {
        return efectoFortuna;
    }

    public double ajustarProbabilidad(double base) {
        double normalizada = clamp01(base);
        if (!efectoFortuna) {
            return normalizada;
        }
        return clamp01(normalizada + bonusFortuna);
    }

    public double getBonusFortuna() {
        return bonusFortuna;
    }

    public void setBonusFortuna(double bonusFortuna) {
        this.bonusFortuna = clamp01(bonusFortuna);
    }

    public int getContadorChupitos() {
        return contadorChupitos;
    }

    public int getRondasConChupitoSeguidas() {
        return rondasConChupitoSeguidas;
    }

    public int getRondasConEfectoSeguidas() {
        return rondasConChupitoSeguidas;
    }

    public double getCostoChupito() {
        return costoChupito;
    }

    public void setCostoChupito(double costoChupito) {
        this.costoChupito = Math.max(0.0, costoChupito);
    }

    private static double clamp01(double valor) {
        if (valor < 0.0) {
            return 0.0;
        }
        if (valor > 1.0) {
            return 1.0;
        }
        return valor;
    }

    @Override
    public String toString() {
        return String.format("Chupito[chupitos=%d/3, fortuna=%s, racha=%d]",
                contadorChupitos,
                efectoFortuna ? "ACTIVA" : "inactiva",
                rondasConChupitoSeguidas);
    }
}
