import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<Carta> cartas;

    // Constructor: crea el mazo completo y lo baraja
    public Mazo() {
        this.cartas = new ArrayList<>();
        generarMazo();
        barajar();
    }

    // Genera las 52 cartas automáticamente
    private void generarMazo() {
        cartas.clear();

        String[] palos = {"Picas", "Corazones", "Diamantes", "Treboles"};
        String[] simbolos = {"♠", "♥", "♦", "♣"};

        for (int i = 0; i < palos.length; i++) {
            String palo = palos[i];
            String simbolo = simbolos[i];

            for (int valor = 1; valor <= 13; valor++) {
                String nombre = nombrePorValor(valor);
                String representacion = nombre + simbolo;

                cartas.add(new Carta(palo, valor, nombre, representacion));
            }
        }
    }

    // Baraja el mazo
    public void barajar() {
        Collections.shuffle(cartas);
    }

    // Roba una carta del mazo (y reinicia si se acaba)
    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            reiniciarMazo();
        }
        return cartas.remove(cartas.size() - 1);
    }

    // Reinicia el mazo (cuando se agota)
    public void reiniciarMazo() {
        generarMazo();
        barajar();
    }

    // Opcional: ver cuántas cartas quedan
    public int cartasRestantes() {
        return cartas.size();
    }

    // Convierte 1..13 en "A", "2"... "10", "J", "Q", "K"
    private String nombrePorValor(int valor) {
        return switch (valor) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(valor);
        };
    }
}

