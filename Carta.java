public class Carta {

    // Atributos privados
    private String palo;        // "Picas", "Corazones", "Diamantes", "Treboles"
    private int valor;          // 1..13 (1=As, 11=J, 12=Q, 13=K)
    private String nombre;      // "A", "2"... "10", "J", "Q", "K"
    private String representacion; // Ej: "A♠"


// Constructor completo
public Carta(String palo, int valor, String nombre, String representacion) {

    // ✅ VALIDACIÓN (1.1): el valor debe estar entre 1 y 13
    if (valor < 1 || valor > 13) {
        throw new IllegalArgumentException("El valor de la carta debe estar entre 1 y 13. Valor recibido: " + valor);
    }

    this.palo = palo;
    this.valor = valor;
    this.nombre = nombre;
    this.representacion = representacion;
}



    // Getters y setters
    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
    // ✅ VALIDACIÓN (1.1)
    if (valor < 1 || valor > 13) {
        throw new IllegalArgumentException("El valor de la carta debe estar entre 1 y 13. Valor recibido: " + valor);
    }
    this.valor = valor;
}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepresentacion() {
        return representacion;
    }

    public void setRepresentacion(String representacion) {
        this.representacion = representacion;
    }

    // ✅ (1.2) Valor base para Blackjack (As = 1; J/Q/K = 10; resto = su número)
    public int getValorBlackjackBase() {
    if (valor >= 11 && valor <= 13) return 10; // J, Q, K
    if (valor == 1) return 1;                  // As (la Mano decidirá si 11)
    return valor;                              // 2..10
}


    // toString para mostrar la carta en pantalla
    @Override
    public String toString() {
        return representacion; // o si prefieres: nombre + " de " + palo
    }
}
