package org.example.UI;

import org.example.gestor.GestorRegistrarResultadoDeRevisionManual;
import org.example.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InterfazRegistrarResultadoDeRevisionManual extends JFrame {

    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JPanel panelPrincipal;

    /**
     * Aquí guardamos la misma lista de objetos originales que devuelve el
     * gestor
     */
    private List<EventoSismico> eventos;

    private final GestorRegistrarResultadoDeRevisionManual gestor;

    private static final DateTimeFormatter FORMATO_FECHA
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private boolean yaPidioSeleccion = false;

    public InterfazRegistrarResultadoDeRevisionManual() {
        super("Registrar Resultado de Revisión Manual");
        this.gestor = new GestorRegistrarResultadoDeRevisionManual();

        // Paso 5/6 del diagrama: desencadenar nueva revisión
        gestor.nuevaRevision();

        initComponents();
        cargarEventos();
    }

    public void habilitarVentana(JFrame ventana) {
        ventana.setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel(
                new String[]{"Id evneto","Fecha/Hora", "Latitud", "Longitud", "Magnitud", "Estado"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEventos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEventos);

        tablaEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Solo actuamos si es doble‐clic; si no, ni intentamos nada
                if (e.getClickCount() == 2) {
                    int fila = tablaEventos.getSelectedRow();

                    // ----- Si no hay fila seleccionada, “pedirSelección” -----
                    if (fila < 0) {
                        // 1) Llamo al gestor para que registre que aún no se eligió nada
                        gestor.pedirSeleccionEvento();

                        if (!yaPidioSeleccion) {
                            yaPidioSeleccion = true;
                            JOptionPane.showMessageDialog(
                                    InterfazRegistrarResultadoDeRevisionManual.this,
                                    "Por favor seleccione un registro de la tabla primero.",
                                    "Seleccione un evento",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                        return;
                    }

                    // ----- Si hay fila, recupero el EventoSismico original y lo paso al gestor -----
                    EventoSismico seleccionado = eventos.get(fila);
                    gestor.tomarEventoSeleccionado(seleccionado);

                    // Ahora mostramos el detalle (bloquearlo en revisión, etc.)
                    mostrarDetalle(seleccionado);
                }
            }
        });

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void cargarEventos() {
        // 1) Limpiar la tabla
        modeloTabla.setRowCount(0);

        // 2) Pedir al Gestor la lista “original” filtrada (ya no usamos copias ligeras)
        eventos = gestor.buscarEventoNoDet();

        // 3) Mostrar cada uno en la tabla
        for (EventoSismico ev : eventos) {
            mostrarDatosPrincipalesEvento(ev);
        }

        // 4) Programar un Timer de 5 segundos para forzar la selección (solo una vez)
        Timer timerPedido = new Timer(5000, e -> {
            gestor.pedirSeleccionEvento();
            JOptionPane.showMessageDialog(
                    InterfazRegistrarResultadoDeRevisionManual.this,
                    "Por favor seleccione un registro de la tabla para continuar.",
                    "Seleccione un evento",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        timerPedido.setRepeats(false);
        timerPedido.start();
    }

    private void mostrarDatosPrincipalesEvento(EventoSismico ev) {
        String fechaFormateada = ev.tomarFechaHoraOcurrencia().format(FORMATO_FECHA);

        modeloTabla.addRow(new Object[]{
            ev.getIdEvento(),
            fechaFormateada,
            ev.tomarLatitudEpicentro(),
            ev.tomarLongitudEpicentro(),
            ev.getValorMagnitud(),
            ev.getEstado().getNombre()
        });
    }

    /**
     * Muestra el diálogo de detalle de un evento. Aquí vamos a: 1) Inhabilitar
     * inicialmente “Visualizar Mapa” 2) Habilitarlo solo después de presionar
     * “Generar Sismograma” 3) Permitir que, si el usuario modifica datos y
     * guarda, se actualicen las áreas de texto sin cerrar el diálogo
     */
    private void mostrarDetalle(EventoSismico evento) {
        // 1) Confirmación “Bloquear en revisión”
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cambiar el estado del evento a 'BloqueadoEnRevision'?",
                "Confirmación de Estado",
                JOptionPane.YES_NO_OPTION
        );
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        // 2) Invocar al gestor para bloquear exactamente este mismo objeto “evento”
        gestor.bloquearEventoSismico(evento);

        // 3) A estas alturas, en “evento” ya cambiamos su estado y
        //    registramos un CambioEstado; además, su magnitud, alcance,
        //    clasificación, origen y series ya estaban en el objeto original.
        // 4) Construir el diálogo que muestra todos los datos:
        JDialog dialog = new JDialog(this, "Detalle del Evento", true);
        dialog.setSize(900, 600);
        dialog.setLayout(new BorderLayout());

        // —— Panel IZQUIERDO (datos principales) —— 
        // Lo declaramos como final para que podamos modificar su contenido desde el formulario de edición
        final JTextArea panelIzquierdo = new JTextArea();
        panelIzquierdo.setEditable(false);

        // —— Panel DERECHO (series → muestras → detalle) —— 
        final JTextArea panelDerecho = new JTextArea();
        panelDerecho.setEditable(false);

        // Método que carga/recarga los textos de panelIzquierdo y panelDerecho
        Runnable refrescarTextosDetalle = () -> {
            // 4.1) Panel izquierdo
            String fechaDetalle = evento.tomarFechaHoraOcurrencia().format(FORMATO_FECHA);
            StringBuilder sbIzq = new StringBuilder();
            sbIzq.append("Fecha/Hora: ").append(fechaDetalle).append("\n")
                    .append("Latitud Epicentro: ").append(evento.tomarLatitudEpicentro()).append("\n")
                    .append("Longitud Epicentro: ").append(evento.tomarLongitudEpicentro()).append("\n")
                    .append("Magnitud: ").append(evento.tomarMagnitud().getDescripcionMagnitud()).append("\n")
                    .append("Alcance: ").append(evento.getAlcanceSismo().getNombre()).append("\n")
                    .append("Clasificación: ").append(evento.getClasificacion().getNombre()).append("\n")
                    .append("Origen: ").append(evento.getOrigenGeneracion().getNombre()).append("\n")
                    .append("Estado Actual: ").append(evento.getEstado().getNombre()).append("\n");
            panelIzquierdo.setText(sbIzq.toString());

            // 4.2) Panel derecho (series → muestras → detalle, incluyendo estación)
            StringBuilder sbDer = new StringBuilder();
            for (SerieTemporal serie : evento.getSerieTemporal()) {
                String nombreEstacion = serie.getEstacionSismica().getNombre();
                sbDer.append("[Serie en estación: ").append(nombreEstacion).append("]\n")
                        .append("   Condición: ").append(serie.getCondicionAlarma()).append("\n")
                        .append("   Inicio registro: ").append(serie.getFechaHoraInicioRegistroMuestras()).append("\n")
                        .append("   Fin registro: ").append(serie.getFechaHoraRegistro()).append("\n")
                        .append("   Frecuencia: ").append(serie.getFrecuenciaMuestreo()).append("\n");
                for (MuestraSismica muestra : serie.getMuestrasSismicas()) {
                    sbDer.append("     [Muestra] Hora: ").append(muestra.getFechaHoraMuestra()).append("\n");
                    for (DetalleMuestraSismica d : muestra.getDetalleMuestraSismica()) {
                        sbDer.append("        - ")
                                .append(d.getTipoDeDato().getDenominacion())
                                .append(": ").append(d.getValor())
                                .append(" ").append(d.getTipoDeDato().getNombreUnidadMedida())
                                .append("\n");
                    }
                }
                sbDer.append("-----------------------------\n");
            }
            panelDerecho.setText(sbDer.toString());
        };

        // Cargamos inicialmente los textos
        refrescarTextosDetalle.run();

        // Construimos los paneles contenedores
        JPanel panelContenido = new JPanel(new GridLayout(1, 2));
        panelContenido.add(new JScrollPane(panelIzquierdo));
        panelContenido.add(new JScrollPane(panelDerecho));

        // ===== Botonera =====
        JPanel panelBotones = new JPanel();

        // 5) Botón “Modificar” abre el formulario de edición
        JButton btnModificar = new JButton("Modificar Datos del Evento");
        btnModificar.addActionListener(e
                -> mostrarFormularioEdicion(dialog, evento, panelIzquierdo, panelDerecho)
        );
        panelBotones.add(btnModificar);

        // 7) Botón “Ver Mapa” (inicialmente deshabilitado)
        JButton btnMapa = new JButton("Visualizar Mapa");
        btnMapa.setEnabled(false); // ← Inhabilitado hasta que se genere sismograma
        btnMapa.addActionListener(e -> JOptionPane.showMessageDialog(
                dialog,
                "(Simulación) Mapa visualizado con éxito",
                "Mapa",
                JOptionPane.INFORMATION_MESSAGE
        ));
        panelBotones.add(btnMapa);

        // 6) Botón “Generar Sismograma” 
        JButton btnSismograma = new JButton("Generar Sismograma");
        // Antes de presionar este botón, el botón “Ver Mapa” estará inhabilitado
        btnSismograma.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    dialog,
                    "Sismograma generado con éxito",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // Una vez que se genera el sismograma, habilitar el botón de “Ver Mapa”
            btnMapa.setEnabled(true);
        });
        panelBotones.add(btnSismograma);

        // 8) Botón “Volver” cierra el diálogo y recarga la lista principal
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            cargarEventos();
            dialog.dispose();
        });
        panelBotones.add(btnVolver);

        // 9) Combo + “Aceptar Acción” (Confirmar/Rechazar/Solicitar revisión a experto)
        String[] opciones = {
            "Confirmar evento",
            "Rechazar evento",
            "Solicitar revisión a experto"
        };
        JComboBox<String> comboAccion = new JComboBox<>(opciones);
        JButton btnAceptarAccion = new JButton("Aceptar Acción");
        btnAceptarAccion.addActionListener(e -> {
            String seleccion = (String) comboAccion.getSelectedItem();
            boolean ok = gestor.confirmarAccionEvento(evento, seleccion);
            if (ok) {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Evento actualizado correctamente como: " + seleccion,
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarEventos();  // Al recargar, ese evento ya no será AUTO_DETECTADO
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Faltan datos obligatorios (magnitud, alcance u origen).",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        panelBotones.add(comboAccion);
        panelBotones.add(btnAceptarAccion);

        // Agregamos todos los componentes al diálogo
        dialog.add(panelContenido, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Cuando se cierre el diálogo, recargamos la tabla principal
        cargarEventos();
    }

    /**
     * Muestra el formulario de edición de datos del evento. Ahora: - No
     * cerramos el diálogo principal, - Actualizamos en el mismo diálogo
     * (panelIzquierdo y panelDerecho) si el usuario guarda cambios.
     */
    private void mostrarFormularioEdicion(JDialog parentDialog,
            EventoSismico evento,
            JTextArea panelIzquierdo,
            JTextArea panelDerecho) {
        JDialog formDialog = new JDialog(this, "Editar Datos del Evento", true);
        formDialog.setSize(400, 300);
        formDialog.setLayout(new GridLayout(5, 2));

        List<AlcanceSismo> alcances = gestor.getAlcances();
        List<OrigenDeGeneracion> origenes = gestor.getOrigenes();
        List<MagnitudRichter> magnitudes = gestor.getMagnitudes();

        // ComboBoxes con los valores actuales seleccionados
        JComboBox<String> cbAlcance = new JComboBox<>(
                alcances.stream().map(AlcanceSismo::getNombre).toArray(String[]::new)
        );
        JComboBox<String> cbOrigen = new JComboBox<>(
                origenes.stream().map(OrigenDeGeneracion::getNombre).toArray(String[]::new)
        );
        JComboBox<String> cbMagnitud = new JComboBox<>(
                magnitudes.stream().map(MagnitudRichter::getDescripcionMagnitud).toArray(String[]::new)
        );

        // Seteamos el valor actual en cada combo
        cbAlcance.setSelectedItem(evento.getAlcanceSismo().getNombre());
        cbOrigen.setSelectedItem(evento.getOrigenGeneracion().getNombre());
        cbMagnitud.setSelectedItem(evento.tomarMagnitud().getDescripcionMagnitud());

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        // Si el usuario pulsa “Guardar”:
        btnGuardar.addActionListener(e -> {
            AlcanceSismo nuevoAlcance = alcances.get(cbAlcance.getSelectedIndex());
            OrigenDeGeneracion nuevoOrigen = origenes.get(cbOrigen.getSelectedIndex());
            MagnitudRichter nuevaMagnitud = magnitudes.get(cbMagnitud.getSelectedIndex());

            // 1) Actualizamos el evento (en la misma instancia que estamos mostrando)
            gestor.actualizarDatosEvento(evento, nuevoAlcance, nuevoOrigen, nuevaMagnitud);

            // 2) Mensaje de confirmación
            JOptionPane.showMessageDialog(formDialog, "Datos actualizados correctamente.");

            // 3) A estas alturas, 'evento' ya contiene los nuevos datos:
            //    Actualizamos los dos paneles de texto del detalle
            //    (recordar que esos paneles se pasaron como referencias finales)
            //    Recalculamos el texto con los datos actualizados:
            String fechaDetalle = evento.tomarFechaHoraOcurrencia().format(FORMATO_FECHA);

            StringBuilder sbIzq = new StringBuilder();
            sbIzq.append("Fecha/Hora: ").append(fechaDetalle).append("\n")
                    .append("Latitud Epicentro: ").append(evento.tomarLatitudEpicentro()).append("\n")
                    .append("Longitud Epicentro: ").append(evento.tomarLongitudEpicentro()).append("\n")
                    .append("Magnitud: ").append(evento.tomarMagnitud().getDescripcionMagnitud()).append("\n")
                    .append("Alcance: ").append(evento.getAlcanceSismo().getNombre()).append("\n")
                    .append("Clasificación: ").append(evento.getClasificacion().getNombre()).append("\n")
                    .append("Origen: ").append(evento.getOrigenGeneracion().getNombre()).append("\n")
                    .append("Estado Actual: ").append(evento.getEstado().getNombre()).append("\n");
            panelIzquierdo.setText(sbIzq.toString());

            StringBuilder sbDer = new StringBuilder();
            for (SerieTemporal serie : evento.getSerieTemporal()) {
                String nombreEstacion = serie.getEstacionSismica().getNombre();
                sbDer.append("[Serie en estación: ").append(nombreEstacion).append("]\n")
                        .append("   Condición: ").append(serie.getCondicionAlarma()).append("\n")
                        .append("   Inicio registro: ").append(serie.getFechaHoraInicioRegistroMuestras()).append("\n")
                        .append("   Fin registro: ").append(serie.getFechaHoraRegistro()).append("\n")
                        .append("   Frecuencia: ").append(serie.getFrecuenciaMuestreo()).append("\n");
                for (MuestraSismica muestra : serie.getMuestrasSismicas()) {
                    sbDer.append("     [Muestra] Hora: ").append(muestra.getFechaHoraMuestra()).append("\n");
                    for (DetalleMuestraSismica d : muestra.getDetalleMuestraSismica()) {
                        sbDer.append("        - ")
                                .append(d.getTipoDeDato().getDenominacion())
                                .append(": ").append(d.getValor())
                                .append(" ").append(d.getTipoDeDato().getNombreUnidadMedida())
                                .append("\n");
                    }
                }
                sbDer.append("-----------------------------\n");
            }
            panelDerecho.setText(sbDer.toString());

            // 4) Cerramos sólo el formulario de edición (no el diálogo principal)
            formDialog.dispose();
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
}
