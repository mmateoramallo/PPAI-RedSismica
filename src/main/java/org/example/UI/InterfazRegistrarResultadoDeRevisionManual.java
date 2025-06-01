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

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cambiar el estado del evento a 'BloqueadoEnRevision'?",
                "Confirmación de Estado",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean actualizado = gestor.cambiarEstadoEvento(evento, "BloqueadoEnRevision");
        if (!actualizado) {
            JOptionPane.showMessageDialog(this, "No se pudo cambiar a 'BloqueadoEnRevision'");
            return;
        }
        gestor.cargarDatosCompletosEvento(evento);

        JDialog dialog = new JDialog(this, "Detalle del Evento", true);
        dialog.setSize(900, 600);
        dialog.setLayout(new BorderLayout());

        // Panel izquierdo
        JTextArea panelIzquierdo = new JTextArea();
        panelIzquierdo.setEditable(false);
        panelIzquierdo.setText(
                "Fecha/Hora: " + evento.getFechaHoraOcurrencia() + "\n"
                + "Latitud Epicentro: " + evento.getLatitudEpicentro() + "\n"
                + "Longitud Epicentro: " + evento.getLongitudEpicentro() + "\n"
                + "Magnitud: " + evento.getMagnitud().getDescripcionMagnitud() + "\n"
                + "Alcance: " + evento.getAlcanceSismo().getNombre() + "\n"
                + "Clasificación: " + evento.getClasificacion().getNombre() + "\n"
                + "Origen: " + evento.getOrigenGeneracion().getNombre() + "\n"
                + "Estado Actual: " + evento.getEstado().getNombreEstado()
        );

        // Panel derecho
        JTextArea panelDerecho = new JTextArea();
        panelDerecho.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (SerieTemporal serie : evento.getSerieTemporal()) {
            sb.append("[Serie] Condición: ").append(serie.getCondicionAlarma()).append("\n");
            sb.append("Inicio: ").append(serie.getFechaHoraInicioRegistroMuestras()).append(" - Fin: ").append(serie.getFechaHoraRegistro()).append("\n");
            sb.append("Frecuencia: ").append(serie.getFrecuenciaMuestreo()).append("\n");
            for (MuestraSismica muestra : serie.getMuestrasSismicas()) {
                sb.append("  [Muestra] Hora: ").append(muestra.getFechaHoraMuestra()).append("\n");
                for (DetalleMuestraSismica d : muestra.getDetalleMuestraSismica()) {
                    sb.append("    ").append(d.getTipoDeDato().getDenominacion()).append(": ")
                            .append(d.getValor()).append(" ")
                            .append(d.getTipoDeDato().getNombreUnidadMedida()).append("\n");
                }
            }
            sb.append("-----------------------------\n");
        }
        panelDerecho.setText(sb.toString());

        JPanel panelContenido = new JPanel(new GridLayout(1, 2));
        panelContenido.add(new JScrollPane(panelIzquierdo));
        panelContenido.add(new JScrollPane(panelDerecho));

        JPanel panelBotones = new JPanel();
        JButton btnVolver = new JButton("Volver");
        JButton btnSismograma = new JButton("Generar Sismograma");
        JButton btnMapa = new JButton("Visualizar Mapa");

        JButton btnModificar = new JButton("Modificar Datos del Evento");
        btnModificar.addActionListener(e -> mostrarFormularioEdicion(dialog, evento));
        panelBotones.add(btnModificar);

        btnVolver.addActionListener(e -> {
            cargarEventos();
            dialog.dispose();
        });

        btnSismograma.addActionListener(e -> JOptionPane.showMessageDialog(
                dialog, "Sismograma generado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE));

        btnMapa.addActionListener(e -> JOptionPane.showMessageDialog(
                dialog, "(Simulación) Mapa visualizado con éxito", "Mapa", JOptionPane.INFORMATION_MESSAGE));
        String[] opciones = {"Confirmar evento", "Rechazar evento", "Solicitar revisión a experto"};
        JComboBox<String> comboAccion = new JComboBox<>(opciones);
        JButton btnAceptarAccion = new JButton("Aceptar Acción");

        btnAceptarAccion.addActionListener(e -> {
            String seleccion = (String) comboAccion.getSelectedItem();
            boolean ok = gestor.confirmarAccionEvento(evento, seleccion);
            if (ok) {
                JOptionPane.showMessageDialog(dialog, "Evento actualizado correctamente como: " + seleccion);
                cargarEventos();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Faltan datos obligatorios (magnitud, alcance u origen).");
            }
        });

        panelBotones.add(comboAccion);
        panelBotones.add(btnAceptarAccion);

        panelBotones.add(btnVolver);
        panelBotones.add(btnSismograma);
        panelBotones.add(btnMapa);

        dialog.add(panelContenido, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        cargarEventos();
    }

    private void mostrarFormularioEdicion(JDialog parentDialog, EventoSismico evento) {
        JDialog formDialog = new JDialog(this, "Editar Datos del Evento", true);
        formDialog.setSize(400, 300);
        formDialog.setLayout(new GridLayout(5, 2));

        List<AlcanceSismo> alcances = gestor.getAlcances();
        List<OrigenDeGeneracion> origenes = gestor.getOrigenes();
        List<MagnitudRichter> magnitudes = gestor.getMagnitudes();

        JComboBox<String> cbAlcance = new JComboBox<>(alcances.stream().map(AlcanceSismo::getNombre).toArray(String[]::new));
        JComboBox<String> cbOrigen = new JComboBox<>(origenes.stream().map(OrigenDeGeneracion::getNombre).toArray(String[]::new));
        JComboBox<String> cbMagnitud = new JComboBox<>(magnitudes.stream().map(MagnitudRichter::getDescripcionMagnitud).toArray(String[]::new));

        cbAlcance.setSelectedItem(evento.getAlcanceSismo().getNombre());
        cbOrigen.setSelectedItem(evento.getOrigenGeneracion().getNombre());
        cbMagnitud.setSelectedItem(evento.getMagnitud().getDescripcionMagnitud());

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            AlcanceSismo nuevoAlcance = alcances.get(cbAlcance.getSelectedIndex());
            OrigenDeGeneracion nuevoOrigen = origenes.get(cbOrigen.getSelectedIndex());
            MagnitudRichter nuevaMagnitud = magnitudes.get(cbMagnitud.getSelectedIndex());

            gestor.actualizarDatosEvento(evento, nuevoAlcance, nuevoOrigen, nuevaMagnitud);

            JOptionPane.showMessageDialog(formDialog, "Datos actualizados correctamente.");
            formDialog.dispose();
            parentDialog.dispose();
            mostrarDetalle(evento); // refresca datos actualizados
        });

        btnCancelar.addActionListener(e -> formDialog.dispose());

        formDialog.add(new JLabel("Alcance:"));
        formDialog.add(cbAlcance);
        formDialog.add(new JLabel("Origen:"));
        formDialog.add(cbOrigen);
        formDialog.add(new JLabel("Magnitud:"));
        formDialog.add(cbMagnitud);
        formDialog.add(btnCancelar);
        formDialog.add(btnGuardar);

        formDialog.setLocationRelativeTo(this);
        formDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazRegistrarResultadoDeRevisionManual().setVisible(true));
    }

}
