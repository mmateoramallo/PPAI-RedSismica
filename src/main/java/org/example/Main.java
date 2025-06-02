package org.example;

import org.example.UI.InterfazPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configurar el Look & Feel (opcional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si Nimbus no está disponible, se ignora y se usa el Look & Feel por defecto
        }

        // Arrancar la Interfaz Principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            InterfazPrincipal ventana = new InterfazPrincipal();
            ventana.setVisible(true);
        });
    }
}
