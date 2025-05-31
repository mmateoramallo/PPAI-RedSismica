package org.example.UI;

import org.example.gestor.GestorRegistrarResultadoDeRevisionManual;
import org.example.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class InterfazRegistrarResultadoDeRevisionManual extends JFrame {

    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JPanel panelPrincipal;

    private final GestorRegistrarResultadoDeRevisionManual gestor;
    private List<EventoSismico> eventos;

    public InterfazRegistrarResultadoDeRevisionManual() {
        super("Registrar Resultado de Revisión Manual");
        gestor = new GestorRegistrarResultadoDeRevisionManual();
        initComponents();
        cargarEventos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"Fecha/Hora", "Latitud", "Longitud", "Magnitud", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEventos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEventos);

        tablaEventos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablaEventos.getSelectedRow() != -1) {
                    int fila = tablaEventos.getSelectedRow();
                    EventoSismico seleccionado = eventos.get(fila);
                    mostrarDetalle(seleccionado);
                }
            }
        });

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void cargarEventos() {
        modeloTabla.setRowCount(0);
        eventos = gestor.buscarEventosSismicosNoRevisados();

        for (EventoSismico ev : eventos) {
            modeloTabla.addRow(new Object[]{
                ev.getFechaHoraOcurrencia(),
                ev.getLatitudEpicentro(),
                ev.getLongitudEpicentro(),
                ev.getValorMagnitud(),
                ev.getEstado().getNombreEstado()
            });
        }
    }

    private void mostrarDetalle(EventoSismico evento) {
        JDialog dialog = new JDialog(this, "Detalle del Evento", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new BorderLayout());

        JTextArea detalle = new JTextArea();
        detalle.setEditable(false);
        detalle.setText(
                "Fecha/Hora: " + evento.getFechaHoraOcurrencia() + "\n"
                + "Latitud Epicentro: " + evento.getLatitudEpicentro() + "\n"
                + "Longitud Epicentro: " + evento.getLongitudEpicentro() + "\n"
                + "Magnitud: " + evento.getMagnitud().getDescripcionMagnitud() + "\n"
                + "Alcance: " + evento.getAlcanceSismo().getNombre() + "\n"
                + "Clasificación: " + evento.getClasificacion().getNombre() + "\n"
                + "Origen: " + evento.getOrigenGeneracion().getNombre() + "\n"
                + "Estado Actual: " + evento.getEstado().getNombreEstado()
        );

        JPanel botones = new JPanel();
        JButton btnCambiarEstado = new JButton("Cambiar Estado");

        btnCambiarEstado.addActionListener(e -> {
            List<Estado> posiblesEstados = gestor.getEstadosPosibles();
            String[] nombres = posiblesEstados.stream()
                    .map(Estado::getNombreEstado)
                    .toArray(String[]::new);

            String nuevo = (String) JOptionPane.showInputDialog(
                    dialog,
                    "Seleccione el nuevo estado:",
                    "Cambiar Estado",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombres,
                    evento.getEstado().getNombreEstado()
            );

            if (nuevo != null && !nuevo.isEmpty()) {
                boolean actualizado = gestor.cambiarEstadoEvento(evento, nuevo);
                if (actualizado) {
                    JOptionPane.showMessageDialog(dialog, "Estado actualizado correctamente a: " + nuevo);
                    cargarEventos();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "El estado seleccionado no es válido.");
                }
            }
        });

        botones.add(btnCambiarEstado);

        dialog.add(new JScrollPane(detalle), BorderLayout.CENTER);
        dialog.add(botones, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazRegistrarResultadoDeRevisionManual().setVisible(true));
    }
}
