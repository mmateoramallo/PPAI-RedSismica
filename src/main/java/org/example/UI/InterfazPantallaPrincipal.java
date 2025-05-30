package org.example.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class InterfazPantallaPrincipal extends JFrame { // ✅ hereda de JFrame

    private JButton btnRegistrarRevision;

    public InterfazPantallaPrincipal() {
        this.setTitle("Red Sísmica");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        JLabel titulo = new JLabel("Red Sísmica");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        // Botón
        btnRegistrarRevision = new JButton("Registrar resultado de revisión manual");
        btnRegistrarRevision.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarRevision.setFocusPainted(false);

        btnRegistrarRevision.addActionListener(this::abrirVentanaRegistrarResultado);

        // Agregamos componentes
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnRegistrarRevision);

        add(panel);
        setVisible(true);
    }

    private void abrirVentanaRegistrarResultado(ActionEvent e) {
        new InterfazRegistrarResultadoRevisionManual(); // Abre nueva ventana
    }
}
