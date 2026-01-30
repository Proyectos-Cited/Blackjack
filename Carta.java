public class Carta {

    // Atributos privados
    private String palo;        // "Picas", "Corazones", "Diamantes", "Treboles"
    private int valor;          // 1..13 (1=As, 11=J, 12=Q, 13=K)
    private String nombre;      // "A", "2"... "10", "J", "Q", "K"
    private String representacion; // Ej: "A♠"


    // Constructor completo
    public Carta(String palo, int valor, String nombre, String representacion) {
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


    // toString para mostrar la carta en pantalla
    @Override
    public String toString() {
        return representacion; // o si prefieres: nombre + " de " + palo
    }
}
