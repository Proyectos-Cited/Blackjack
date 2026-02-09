import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Interfaz Gráfica Profesional - Blackjack 2D
 * Vista (View) preparada para ser conectada al Motor del Juego
 * 
 * @author Luis Miguel
 * @version 8.0 - Pure View
 */
public class InterfazGrafica extends JFrame {

    // Dimensiones
    private static final int ANCHO = 1000;
    private static final int ALTO = 700;

    // Componentes Gráficos Publicos (para que el Motor los actualice)
    public JLabel lblPuntosJuego;
    public JLabel lblDinero;
    public JLabel lblEstadoChupito;
    public JLabel lblEstadoSeguro;
    private JTextArea areaMensajes;
    private JPanel panelTablero;

    // Botones de Accion (con Listeners Listos)
    public JButton btnBeberChupito;
    public JButton btnUsarSeguro;
    public JButton btnPedir;
    public JButton btnPlantarse;
    public JButton btnNuevaRonda;

    // Estado Visual (Solo Almacena Datos para Dibujar)
    private List<String> cartasJugador;
    private List<String> cartasCrupier;

    public InterfazGrafica() {
        cartasJugador = new ArrayList<>();
        cartasCrupier = new ArrayList<>();

        configurarVentana();
        inicializarComponentes();
        setVisible(true);
    }

    // --- CONFIGURACIÓN UI (SIN LÓGICA DE JUEGO) ---

    private void configurarVentana() {
        setTitle("♠ BLACKJACK - Vista del Juego ♦");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {
        // ... (Creación de componentes visuales igual que antes) ...
        // Panel Tablero
        panelTablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarTablero(g);
            }
        };
        panelTablero.setBackground(new Color(34, 139, 34)); // Verde Tapete
        panelTablero.setLayout(null);

        // Panel Info
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInfo.setBackground(new Color(20, 20, 20));
        lblPuntosJuego = crearEtiquetaInfo("PUNTOS: -/5");
        lblDinero = crearEtiquetaInfo("DINERO: $0");
        lblEstadoChupito = crearEtiquetaInfo("CHUPITOS: -");
        lblEstadoSeguro = crearEtiquetaInfo("SEGURO: -");
        panelInfo.add(lblPuntosJuego);
        panelInfo.add(lblDinero);
        panelInfo.add(lblEstadoChupito);
        panelInfo.add(lblEstadoSeguro);

        // Panel Controles
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelControles.setBackground(new Color(40, 40, 40));
        btnPedir = crearBoton("PEDIR CARTA", new Color(0, 128, 0));
        btnPlantarse = crearBoton("PLANTARSE", new Color(178, 34, 34));
        btnBeberChupito = crearBoton("BEBER CHUPITO 🥃", new Color(128, 0, 128));
        btnUsarSeguro = crearBoton("USAR SEGURO 🛡️", new Color(218, 165, 32));
        btnNuevaRonda = crearBoton("INICIAR RONDA", new Color(70, 130, 180));

        panelControles.add(btnPedir);
        panelControles.add(btnPlantarse);
        panelControles.add(Box.createHorizontalStrut(20));
        panelControles.add(btnBeberChupito);
        panelControles.add(btnUsarSeguro);
        panelControles.add(Box.createHorizontalStrut(20));
        panelControles.add(btnNuevaRonda);

        // Log
        areaMensajes = new JTextArea(3, 40);
        areaMensajes.setEditable(false);
        areaMensajes.setBackground(new Color(20, 20, 20));
        areaMensajes.setForeground(Color.GREEN);
        JScrollPane scroll = new JScrollPane(areaMensajes);
        panelControles.add(scroll);

        add(panelInfo, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);
    }

    // --- MÉTODOS PÚBLICOS PARA EL MOTOR (INTEGRACIÓN) ---

    /**
     * Actualiza visualmente el estado del juego.
     * Llamar desde JuegoBlackjack.mostrar_tablero()
     */
    public void actualizarVista(List<String> cartasJ, List<String> cartasC, int puntos, double dinero, String chupito,
            String seguro) {
        this.cartasJugador = new ArrayList<>(cartasJ);
        this.cartasCrupier = new ArrayList<>(cartasC);
        this.lblPuntosJuego.setText("PUNTOS: " + puntos + "/5");
        this.lblDinero.setText("DINERO: $" + (int) dinero);
        this.lblEstadoChupito.setText("CHUPITOS: " + chupito);
        this.lblEstadoSeguro.setText("SEGURO: " + seguro);
        panelTablero.repaint();
    }

    /**
     * Muestra mensaje en el log visual
     */
    public void log(String msg) {
        areaMensajes.append(msg + "\n");
        areaMensajes.setCaretPosition(areaMensajes.getDocument().getLength());
    }

    /**
     * Muestra alerta final de victoria
     */
    public void mostrarGanadorJuego() {
        JOptionPane.showMessageDialog(this, "¡JUEGO TERMINADO! Alguien llegó a 5 puntos.");
    }

    // --- DIBUJADO (View Logic Only) ---

    private void dibujarTablero(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Textos Mesa
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("CRUPIER", ANCHO / 2 - 50, 50);
        g2.drawString("JUGADOR", ANCHO / 2 - 55, ALTO - 250);
        g2.setColor(new Color(255, 215, 0, 100)); // Dorado
        g2.setFont(new Font("Serif", Font.ITALIC, 40));
        g2.drawString("BLACKJACK", ANCHO / 2 - 110, ALTO / 2 + 10);

        // Dibujar Cartas (Datos recibidos)
        if (!cartasCrupier.isEmpty())
            dibujarCartas(g2, cartasCrupier, ANCHO / 2 - (cartasCrupier.size() * 40), 80);
        if (!cartasJugador.isEmpty())
            dibujarCartas(g2, cartasJugador, ANCHO / 2 - (cartasJugador.size() * 40), ALTO - 200);
    }

    private void dibujarCartas(Graphics2D g, List<String> cartas, int x, int y) {
        for (String carta : cartas) {
            g.setColor(Color.WHITE);
            g.fillRoundRect(x, y, 70, 100, 10, 10);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, 70, 100, 10, 10);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            if (carta.contains("♥") || carta.contains("♦"))
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);

            g.drawString(carta, x + 15, y + 60);
            x += 80;
        }
    }

    // Helpers UI
    private JLabel crearEtiquetaInfo(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        return l;
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    // Método main solo para previsualizar ventana vacía
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}
