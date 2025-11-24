package org.example.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PantallaBienvenida extends JFrame {

    private static final Color BG_DARK = new Color(18, 18, 18);
    private static final Color TEXT_PRIMARY = new Color(240, 240, 240);
    private static final Color BTN_GREEN = new Color(103, 194, 58);

    public PantallaBienvenida(String nombreAnalista, String legajoAnalista) {
        setTitle("Bienvenido");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Opcional: Quita bordes para que parezca un splash
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_DARK);
        panel.setBorder(BorderFactory.createLineBorder(new Color(60,60,60), 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("¡Bienvenido nuevamente!");
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lblTitulo.setForeground(TEXT_PRIMARY);

        JLabel lblNombre = new JLabel("Analista: " + nombreAnalista);
        lblNombre.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblNombre.setForeground(Color.LIGHT_GRAY);

        JLabel lblLegajo = new JLabel("Legajo: " + legajoAnalista);
        lblLegajo.setFont(new Font("Roboto", Font.PLAIN, 16));
        lblLegajo.setForeground(Color.GRAY);

        JButton btnContinuar = new JButton("Ingresar al Sistema");
        btnContinuar.setBackground(BTN_GREEN);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Roboto", Font.BOLD, 14));
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // ACCIÓN: Abrir la Interfaz Principal
        btnContinuar.addActionListener(e -> {
            InterfazPrincipal principal = new InterfazPrincipal();
            principal.setVisible(true);
            this.dispose();
        });

        panel.add(lblTitulo, gbc);
        panel.add(Box.createVerticalStrut(10), gbc);
        panel.add(lblNombre, gbc);
        panel.add(lblLegajo, gbc);
        panel.add(Box.createVerticalStrut(20), gbc);
        panel.add(btnContinuar, gbc);

        add(panel);
    }
}