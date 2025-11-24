package org.example.UI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author mateo
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName());



    private static final Color BG_DARK = new Color(18, 18, 18);
    private static final Color BTN_COLOR = new Color(70, 70, 70);
    private static final Color BTN_HOVER = new Color(90, 90, 90);
    private static final Color TEXT_WHITE = new Color(240, 240, 240);

    /**
     * Constructor
     */
    public InterfazPrincipal() {
        // 1. NetBeans crea los componentes (No tocar)
        initComponents();

        // 2. Nosotros aplicamos el estilo visual encima
        aplicarEstiloDark();
    }

    /**
     * Método PERSONALIZADO para sobreescribir el diseño de NetBeans sin romper
     * el "Guarded Code".
     */
    private void personalizarInterfaz() {
        // Configuración de la Ventana
        setTitle("Red Sísmica - Menú Principal");
        setLocationRelativeTo(null); // Centrar
        getContentPane().setBackground(BG_DARK); // Fondo de la ventana

        // Configuración del Panel (jPanel1)
        jPanel1.setBackground(BG_DARK);

        // --- Agregar Título Manualmente (ya que no está en el diseño original) ---
        JLabel lblTitulo = new JLabel("Panel de Control");
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 28));
        lblTitulo.setForeground(TEXT_WHITE);
        // Lo agregamos usando las coordenadas absolutas (ajusta X e Y si quieres moverlo)
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        // --- Estilizar el Botón Existente (jButton1) ---
        estilizarBoton(jButton1);
        // Ajustamos la posición para que quede centrado estéticamente
        // (NetBeans usa AbsoluteConstraints, aquí actualizamos sus coordenadas)
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 350, 50));
    }

    // Método auxiliar para dar estilo moderno al botón
    private void estilizarBoton(JButton btn) {
        btn.setBackground(BTN_COLOR);
        btn.setForeground(TEXT_WHITE);
        btn.setFont(new Font("Roboto", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(100, 100, 100), 1),
                new EmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto Hover (Ratón encima)
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BTN_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BTN_COLOR);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Registrar resultado de revisión manual ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarOpcionRegistrarRevManual(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 279, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void seleccionarOpcionRegistrarRevManual(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// Instancia tu ventana del caso de uso
        InterfazRegistrarResultadoDeRevisionManual ventanaRevision
                = new InterfazRegistrarResultadoDeRevisionManual();

        // Usamos tu método habilitarVentana
        ventanaRevision.habilitarVentana(ventanaRevision);

        // Opcional: Cerrar el menú principal al abrir la otra ventana
        // this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    /* public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(() -> new InterfazPrincipal().setVisible(true));
    }
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void aplicarEstiloDark() {
        personalizarInterfaz();
    }
}
