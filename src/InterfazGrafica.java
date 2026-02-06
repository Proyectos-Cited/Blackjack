import javax.swing.*;
import java.awt.*;

/**
 * Panel de Demostración - Mecánicas Adicionales
 * Demuestra ÚNICAMENTE los sistemas Chupito y Seguro
 * 
 * @author Luis Miguel
 * @version 6.0 - Demo Simple
 */
public class InterfazGrafica extends JFrame {

    private JLabel lblDinero, lblVictorias, lblSeguroEstado, lblChupitosEstado;
    private JTextArea areaLog;
    private JButton btnBeberChupito, btnUsarSeguro, btnSimularVictoria, btnSimularDerrota, btnReset;

    // Sistemas de mecánicas adicionales
    private Chupito sistemaChupito;
    private Seguro sistemaSeguro;

    // Estado del juego (simulado)
    private double dinero = 1000;
    private int victorias = 0;

    public InterfazGrafica() {
        sistemaChupito = new Chupito();
        sistemaSeguro = new Seguro();
        configurarVentana();
        inicializarComponentes();
        setVisible(true);
        log("=== DEMO DE MECANICAS ADICIONALES ===");
        log("Prueba los sistemas Chupito y Seguro");
    }

    private void configurarVentana() {
        setTitle("Demo Mecánicas Adicionales - Chupito y Seguro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior - Estado
        JPanel panelEstado = new JPanel(new GridLayout(2, 2, 10, 10));
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado Actual"));
        panelEstado.setBackground(new Color(240, 240, 240));

        lblDinero = new JLabel("Dinero: $1000", SwingConstants.CENTER);
        lblVictorias = new JLabel("Victorias: 0", SwingConstants.CENTER);
        lblChupitosEstado = new JLabel("Chupitos: 0/3", SwingConstants.CENTER);
        lblSeguroEstado = new JLabel("Seguro: NO", SwingConstants.CENTER);

        Font fuenteEstado = new Font("Arial", Font.BOLD, 18);
        lblDinero.setFont(fuenteEstado);
        lblVictorias.setFont(fuenteEstado);
        lblChupitosEstado.setFont(fuenteEstado);
        lblSeguroEstado.setFont(fuenteEstado);

        panelEstado.add(lblDinero);
        panelEstado.add(lblVictorias);
        panelEstado.add(lblChupitosEstado);
        panelEstado.add(lblSeguroEstado);

        // Panel central - Log
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaLog.setBackground(new Color(20, 20, 20));
        areaLog.setForeground(new Color(0, 255, 0));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log de Acciones"));

        // Panel inferior - Botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnBeberChupito = crearBoton("BEBER CHUPITO", new Color(142, 36, 170));
        btnUsarSeguro = crearBoton("USAR SEGURO", new Color(255, 193, 7));
        btnSimularVictoria = crearBoton("Simular Victoria", new Color(46, 125, 50));
        btnSimularDerrota = crearBoton("Simular Derrota", new Color(211, 47, 47));
        btnReset = crearBoton("REINICIAR", new Color(63, 81, 181));

        btnBeberChupito.addActionListener(e -> accionBeberChupito());
        btnUsarSeguro.addActionListener(e -> accionUsarSeguro());
        btnSimularVictoria.addActionListener(e -> simularVictoria());
        btnSimularDerrota.addActionListener(e -> simularDerrota());
        btnReset.addActionListener(e -> reiniciar());

        panelBotones.add(btnBeberChupito);
        panelBotones.add(btnUsarSeguro);
        panelBotones.add(btnSimularVictoria);
        panelBotones.add(btnSimularDerrota);
        panelBotones.add(btnReset);

        add(panelEstado, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        actualizarEstado();
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    // === ACCIONES ===

    private void accionBeberChupito() {
        log("\n[ACCION] Intentando beber chupito...");
        if (sistemaChupito.beber()) {
            if (sistemaChupito.tieneFortuna()) {
                log("*** FORTUNA ACTIVADA ***");
                log("Probabilidad de ganar aumentada esta partida");
            } else {
                log("Chupito bebido (" + sistemaChupito.getContadorChupitos() + "/3)");
            }
        }
        actualizarEstado();
    }

    private void accionUsarSeguro() {
        log("\n[ACCION] Intentando usar seguro...");
        if (sistemaSeguro.isSeguroActivo()) {
            if (sistemaSeguro.usarSeguro()) {
                log("SEGURO USADO - Perdida anulada");
                dinero += 50; // Recuperar pérdida simulada
            }
        } else {
            log("ERROR: No tienes seguro disponible");
            log("Gana 2 partidas seguidas para obtenerlo");
        }
        actualizarEstado();
    }

    private void simularVictoria() {
        log("\n[SIMULACION] Victoria en partida");
        victorias++;
        dinero += 100;
        sistemaSeguro.registrarVictoria();

        // Comprobar desmayo
        double dineroAnterior = dinero;
        dinero = sistemaChupito.comprobarDesmayo(dinero);
        if (dinero < dineroAnterior) {
            log("*** DESMAYO *** Perdiste el 30% del dinero");
        }

        sistemaChupito.finalizarPartida();
        log("Resultado: +$100");
        actualizarEstado();
    }

    private void simularDerrota() {
        log("\n[SIMULACION] Derrota en partida");
        sistemaSeguro.registrarDerrota();

        if (dinero >= 50) {
            dinero -= 50;
            log("Resultado: -$50");
        } else {
            dinero = 0;
            log("Resultado: Sin dinero");
        }

        // Comprobar desmayo
        double dineroAnterior = dinero;
        dinero = sistemaChupito.comprobarDesmayo(dinero);
        if (dinero < dineroAnterior) {
            log("*** DESMAYO *** Perdiste el 30% del dinero");
        }

        sistemaChupito.finalizarPartida();
        actualizarEstado();
    }

    private void reiniciar() {
        log("\n=== REINICIO ===");
        sistemaChupito = new Chupito();
        sistemaSeguro = new Seguro();
        dinero = 1000;
        victorias = 0;
        areaLog.setText("");
        log("Sistema reiniciado");
        actualizarEstado();
    }

    private void actualizarEstado() {
        if (dinero < 0)
            dinero = 0;

        lblDinero.setText("Dinero: $" + (int) dinero);
        lblVictorias.setText("Victorias: " + victorias);

        // Chupitos
        int chupitos = sistemaChupito.getContadorChupitos();
        boolean fortuna = sistemaChupito.tieneFortuna();
        lblChupitosEstado.setText("Chupitos: " + chupitos + "/3" + (fortuna ? " [FORTUNA]" : ""));
        lblChupitosEstado.setForeground(fortuna ? new Color(255, 165, 0) : Color.BLACK);

        // Seguro
        boolean seguro = sistemaSeguro.isSeguroActivo();
        lblSeguroEstado.setText("Seguro: " + (seguro ? "SI" : "NO"));
        lblSeguroEstado.setForeground(seguro ? new Color(0, 150, 0) : Color.BLACK);
    }

    private void log(String mensaje) {
        areaLog.append(mensaje + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}
