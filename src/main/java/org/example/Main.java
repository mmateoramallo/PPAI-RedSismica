package org.example;

import org.example.UI.PantallaLogin;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Intentar poner estilo Nimbus o Sistema, aunque forzaremos colores manuales luego
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Fallback
        }

        SwingUtilities.invokeLater(() -> {
            // PASO 1: Arrancar con el Login
            PantallaLogin login = new PantallaLogin();
            login.setVisible(true);
        });
    }
}