package org.example.UI;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author mateo
 */
public class InterfazPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName());

    /**
     * Creates new form InterfazPrincipal
     */
    public InterfazPrincipal() {
        initComponents();
         setTitle("Red Sísmica");
         jPanel1.setBackground(Color.GRAY); //Color de mi panel
         ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
         setIconImage(icon.getImage());
               
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
        // TODO add your handling code here:
        //Aca iria el habilitar Ventana para la ventana de revision manual
        // Al hacer clic, abrimos la ventana de registrar resultados de revisión manual
        InterfazRegistrarResultadoDeRevisionManual ventanaRevision =
            new InterfazRegistrarResultadoDeRevisionManual();
        //ventanaRevision.setVisible(true);        
        ventanaRevision.habilitarVentana(ventanaRevision);
        
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
}
