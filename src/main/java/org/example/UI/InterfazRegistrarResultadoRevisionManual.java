package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class InterfazRegistrarResultadoRevisionManual extends JFrame {

    public InterfazRegistrarResultadoRevisionManual() {
        setTitle("Resultado de Revisión Manual");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnAprobar = new JButton("Aprobar");
        JButton btnRechazar = new JButton("Rechazar");
        JButton btnDerivar = new JButton("Derivar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAprobar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Sismo aprobado."); // ✅ null como primer argumento
            dispose();
        });

        btnRechazar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Sismo rechazado.");
            dispose();
        });

        btnDerivar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Sismo derivado.");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        panel.add(btnAprobar);
        panel.add(btnRechazar);
        panel.add(btnDerivar);
        panel.add(btnCancelar);

        add(panel);
        setVisible(true);
    }
}
