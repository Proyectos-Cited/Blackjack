import javax.swing.*;
import java.awt.*;

public class BlackjackGUI extends JFrame {

    private JTextArea areaCrupier;
    private JTextArea areaJugador;
    private JLabel labelInfo;
    private JButton btnPedir, btnPlantarse, btnNuevaRonda;

    public BlackjackGUI() {
        setTitle("Blackjack - Interfaz");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // Arriba: Crupier
        areaCrupier = new JTextArea(6, 40);
        areaCrupier.setEditable(false);
        areaCrupier.setBorder(BorderFactory.createTitledBorder("Crupier"));
        add(new JScrollPane(areaCrupier), BorderLayout.NORTH);

        // Centro: Jugador
        areaJugador = new JTextArea(10, 40);
        areaJugador.setEditable(false);
        areaJugador.setBorder(BorderFactory.createTitledBorder("Jugador"));
        add(new JScrollPane(areaJugador), BorderLayout.CENTER);

        // Abajo: botones + info
        JPanel panelAbajo = new JPanel(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPedir = new JButton("Pedir carta");
        btnPlantarse = new JButton("Plantarse");
        btnNuevaRonda = new JButton("Nueva ronda");

        panelBotones.add(btnPedir);
        panelBotones.add(btnPlantarse);
        panelBotones.add(btnNuevaRonda);

        labelInfo = new JLabel("Listo.");
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

        panelAbajo.add(panelBotones, BorderLayout.CENTER);
        panelAbajo.add(labelInfo, BorderLayout.SOUTH);

        add(panelAbajo, BorderLayout.SOUTH);

        // Texto inicial de prueba
        areaCrupier.setText("Cartas del crupier aparecerán aquí...");
        areaJugador.setText("Cartas del jugador aparecerán aquí...");

        // Eventos (DEMO por ahora)
        btnPedir.addActionListener(e -> labelInfo.setText("Has pulsado: Pedir"));
        btnPlantarse.addActionListener(e -> labelInfo.setText("Has pulsado: Plantarse"));
        btnNuevaRonda.addActionListener(e -> labelInfo.setText("Has pulsado: Nueva ronda"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BlackjackGUI().setVisible(true));
    }
}
