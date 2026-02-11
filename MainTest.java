public class MainTest {
    public static void main(String[] args) {

        // 1) Probar el mazo (robar 5 cartas)
        Mazo mazo = new Mazo();
        System.out.println("Robando 5 cartas del mazo:");
        for (int i = 0; i < 5; i++) {
            Carta c = mazo.robarCarta();
            System.out.println("- " + c + " | valor blackjack base: " + c.getValorBlackjackBase());
        }
        System.out.println("Cartas restantes: " + mazo.cartasRestantes());

        // 2) Probar la validación (debería dar error)
        System.out.println("\nProbando validación con valor inválido (14):");
        Carta cartaMala = new Carta("Picas", 14, "??", "??"); // Esto debería explotar
        System.out.println(cartaMala);
    }
}

