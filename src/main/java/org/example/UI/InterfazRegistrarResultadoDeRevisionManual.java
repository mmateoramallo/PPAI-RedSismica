package org.example.UI;

import org.example.gestor.GestorRegistrarResultadoDeRevisionManual;
import org.example.modelos.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InterfazRegistrarResultadoDeRevisionManual extends JFrame {

    // --- Paleta de Colores "Dark Modern" ---
    private static final Color BG_DARK = new Color(18, 18, 18);
    private static final Color BG_SURFACE = new Color(30, 30, 30);
    private static final Color BG_HEADER_TABLE = new Color(45, 45, 45);
    
    private static final Color TEXT_PRIMARY = new Color(240, 240, 240);
    private static final Color TEXT_SECONDARY = new Color(160, 160, 160);
    
    private static final Color BTN_BLUE = new Color(64, 158, 255);
    private static final Color BTN_GREEN = new Color(103, 194, 58);
    private static final Color BTN_RED = new Color(245, 108, 108); // Rojo para la alerta
    private static final Color BTN_DEFAULT = new Color(70, 70, 70);

    // Fuentes
    private static final Font FONT_MAIN = new Font("Roboto", Font.PLAIN, 14);
    private static final Font FONT_BOLD = new Font("Roboto", Font.BOLD, 14);
    private static final Font FONT_TITLE = new Font("Roboto", Font.BOLD, 22);

    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JPanel panelPrincipal;
    private JLabel lblNotificacion; // <--- NUEVO COMPONENTE
    private Timer timerBlink;       // <--- TIMER PARA ANIMACIÓN
    
    private List<EventoSismico> eventos;
    private final GestorRegistrarResultadoDeRevisionManual gestor;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    
    
    public InterfazRegistrarResultadoDeRevisionManual() {
        super("Red Sísmica - Dashboard de Revisión");
        this.gestor = new GestorRegistrarResultadoDeRevisionManual();

        gestor.nuevaRevision();

        initComponents();
        cargarEventos();
    }

    public void habilitarVentana(JFrame ventana) {
        ventana.setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(BG_DARK);
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));

        // 1. Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headerPanel.setBackground(BG_DARK);
        
        JLabel lblTitulo = new JLabel("Registrar Resultado de Revisión Manual");
        lblTitulo.setFont(FONT_TITLE);
        lblTitulo.setForeground(TEXT_PRIMARY);
        
        JLabel lblSub = new JLabel("Eventos sísmicos detectados automáticamente pendientes de revisión");
        lblSub.setFont(FONT_MAIN);
        lblSub.setForeground(TEXT_SECONDARY);
        
        headerPanel.add(lblTitulo);
        headerPanel.add(lblSub);
        panelPrincipal.add(headerPanel, BorderLayout.NORTH);

        // 2. Tabla Estilizada
        String[] columnas = {"ID", "Fecha/Hora", "Latitud", "Longitud", "Magnitud", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaEventos = new JTable(modeloTabla);
        configurarTabla(tablaEventos);

        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.getViewport().setBackground(BG_DARK);
        scrollPane.setBorder(BorderFactory.createLineBorder(BG_SURFACE));
        
        // --- 3. Listener de Selección (LOGICA CLAVE AQUÍ) ---
        tablaEventos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (tablaEventos.getSelectedRow() != -1) {
                    // SI SELECCIONA: Detener parpadeo, ocultar mensaje
                    detenerParpadeo();
                    lblNotificacion.setVisible(false);
                } else {
                    // SI DES-SELECCIONA: Mostrar mensaje neutral
                    resetearNotificacion();
                    lblNotificacion.setVisible(true);
                }
            }
        });

        tablaEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    procesarSeleccion();
                }
            }
        });

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBackground(BG_DARK);
        tableContainer.setBorder(new EmptyBorder(20, 0, 20, 0)); // Margen abajo para el label
        tableContainer.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(tableContainer, BorderLayout.CENTER);

        // --- 4. Panel Inferior con Notificación (Estilo Footer) ---
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(BG_DARK);
        
        // Configuración inicial del Label de Notificación
        // Usamos un ícono unicode ⓘ para que se vea como en la imagen
        lblNotificacion = new JLabel("ⓘ Por favor seleccione un registro de la tabla para continuar");
        lblNotificacion.setFont(new Font("Roboto", Font.PLAIN, 14));
        lblNotificacion.setForeground(TEXT_SECONDARY);
        lblNotificacion.setIconTextGap(10);
        
        footerPanel.add(lblNotificacion);
        panelPrincipal.add(footerPanel, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // --- MÉTODO: Activar Alerta Visual (Parpadeo) ---
    private void activarAlertaParpadeante() {
        if (tablaEventos.getSelectedRow() != -1) return; // Si ya seleccionó, no molestar.

        lblNotificacion.setText("⚠ ¡Atención! Debe seleccionar un registro para continuar.");
        lblNotificacion.setForeground(BTN_RED);
        lblNotificacion.setVisible(true);

        // Timer para hacer parpadear el texto (Blink)
        if (timerBlink != null && timerBlink.isRunning()) timerBlink.stop();
        
        timerBlink = new Timer(500, new ActionListener() {
            boolean visible = true;
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alternar color entre Rojo y Transparente/Fondo
                if (visible) {
                    lblNotificacion.setForeground(BG_DARK); // "Invisible"
                } else {
                    lblNotificacion.setForeground(BTN_RED); // Visible rojo
                }
                visible = !visible;
                count++;
                
                // Detener después de unos segundos (ej. 10 parpadeos) para no marear
                if (count > 10) {
                    detenerParpadeo();
                    lblNotificacion.setForeground(BTN_RED); // Dejarlo en rojo fijo
                }
            }
        });
        timerBlink.start();
    }

    private void detenerParpadeo() {
        if (timerBlink != null) timerBlink.stop();
    }

    private void resetearNotificacion() {
        detenerParpadeo();
        lblNotificacion.setText("ⓘ Por favor seleccione un registro de la tabla para continuar");
        lblNotificacion.setForeground(TEXT_SECONDARY);
    }

    private void configurarTabla(JTable table) {
        table.setBackground(BG_DARK);
        table.setForeground(TEXT_PRIMARY);
        table.setRowHeight(45);
        table.setSelectionBackground(new Color(55, 55, 60));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setShowVerticalLines(false);
        table.setGridColor(BG_SURFACE);
        table.setFont(FONT_MAIN);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setBackground(BG_HEADER_TABLE);
        header.setForeground(TEXT_SECONDARY);
        header.setFont(FONT_BOLD);
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setPreferredSize(new Dimension(0, 40));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 5) table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setOpaque(false);
                return new PillLabel((String) value);
            }
        });
    }

    private static class PillLabel extends JLabel {
        private final Color colorBorde;
        public PillLabel(String estado) {
            super(estado);
            setHorizontalAlignment(CENTER);
            setForeground(TEXT_PRIMARY);
            setFont(new Font("Roboto", Font.BOLD, 12));
            if ("AutoDetectado".equals(estado)) this.colorBorde = BTN_BLUE;
            else if ("Confirmado".equals(estado)) this.colorBorde = BTN_GREEN;
            else if ("Rechazado".equals(estado)) this.colorBorde = BTN_RED;
            else this.colorBorde = TEXT_SECONDARY;
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(BG_DARK);
            g2.fillRect(0,0,getWidth(),getHeight());
            g2.setColor(new Color(colorBorde.getRed(), colorBorde.getGreen(), colorBorde.getBlue(), 40));
            g2.fillRoundRect(10, 8, getWidth()-20, getHeight()-16, 20, 20);
            g2.setColor(colorBorde);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(10, 8, getWidth()-20, getHeight()-16, 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private void cargarEventos() {
        modeloTabla.setRowCount(0);
        eventos = gestor.buscarEventoNoDet();
        for (EventoSismico ev : eventos) {
            modeloTabla.addRow(new Object[]{
                ev.getIdEvento(),
                ev.tomarFechaHoraOcurrencia().format(FORMATO_FECHA),
                ev.tomarLatitudEpicentro() + "°",
                ev.tomarLongitudEpicentro() + "°",
                ev.getValorMagnitud(),
                ev.getEstado().getNombre()
            });
        }
        
        // --- TIMER DE RECORDATORIO MODIFICADO ---
        // Antes mostraba un JOptionPane, ahora activa la animación del label
        Timer timerPedido = new Timer(5000, e -> {
            gestor.pedirSeleccionEvento();
            activarAlertaParpadeante(); // <--- Aquí activamos el efecto visual en lugar del popup
        });
        timerPedido.setRepeats(false);
        timerPedido.start();
    }

    private void procesarSeleccion() {
        int fila = tablaEventos.getSelectedRow();
        if (fila < 0) return;
        EventoSismico seleccionado = eventos.get(fila);
        gestor.tomarEventoSeleccionado(seleccionado);
        mostrarDetalleDashboard(seleccionado);
    }

    // ======================================================================================
    //     DETALLE (DASHBOARD STYLE) - Sin cambios funcionales, solo estilo
    // ======================================================================================
    private void mostrarDetalleDashboard(EventoSismico evento) {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Desea revisar el evento #" + evento.getIdEvento() + "?",
            "Confirmar Revisión", JOptionPane.YES_NO_OPTION);
        
        if (opcion != JOptionPane.YES_OPTION) return;

        gestor.bloquearEventoSismico(evento);

        JDialog dialog = new JDialog(this, "Detalles del Evento #" + evento.getIdEvento(), true);
        dialog.setSize(950, 600);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(BG_DARK);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(BG_DARK);
        tabs.setForeground(TEXT_PRIMARY);
        tabs.setFont(FONT_MAIN);
        
        JPanel panelResumen = crearPanelResumen(evento);
        tabs.addTab("Resumen Visual", panelResumen);

        JTextArea txtMuestras = new JTextArea();
        txtMuestras.setEditable(false);
        txtMuestras.setBackground(BG_SURFACE);
        txtMuestras.setForeground(TEXT_SECONDARY);
        txtMuestras.setFont(new Font("Monospaced", Font.PLAIN, 12));
        actualizarTextoMuestras(evento, txtMuestras);
        
        JScrollPane scrollRaw = new JScrollPane(txtMuestras);
        scrollRaw.setBorder(null);
        tabs.addTab("Datos Técnicos (Raw)", scrollRaw);

        dialog.add(tabs, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        actionPanel.setBackground(BG_DARK);
        actionPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        ModernButton btnModificar = new ModernButton("Modificar Datos", BTN_DEFAULT);
        ModernButton btnSismograma = new ModernButton("Generar Sismograma", BTN_BLUE);
        ModernButton btnMapa = new ModernButton("Ver en Mapa", BTN_DEFAULT);
        btnMapa.setEnabled(false);

        btnModificar.addActionListener(e -> {
            mostrarFormularioEdicion(dialog, evento, () -> {
                tabs.setComponentAt(0, crearPanelResumen(evento));
                actualizarTextoMuestras(evento, txtMuestras);
                tabs.revalidate();
            });
        });

        btnSismograma.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Sismograma generado correctamente.");
            btnMapa.setEnabled(true);
            btnMapa.setAccentColor(BTN_BLUE);
        });

        btnMapa.addActionListener(e -> 
            JOptionPane.showMessageDialog(dialog, "(Simulación) Mapa desplegado.")
        );
        //Aca comienza para el rediseño, se tiene en cuenta las opciones que se pueden dar, pero desde aca se manda la opcion para que se rechaze el evento
        String[] opciones = {"Confirmar evento", "Rechazar evento", "Solicitar revisión experto"};
        JComboBox<String> comboAccion = new JComboBox<>(opciones);
        comboAccion.setFont(FONT_MAIN);
        
        ModernButton btnAceptar = new ModernButton("Aceptar Acción", BTN_GREEN);
        
        btnAceptar.addActionListener(e -> {
            String seleccion = (String) comboAccion.getSelectedItem();
            if (gestor.confirmarAccionEvento(evento, seleccion)) { //Seria el tomar Rechazar  del diagrama no se pone esto por que no concordaria que se den todas las posibilidades y diga tomar rechazar  
                JOptionPane.showMessageDialog(dialog, "Evento procesado: " + seleccion);
                cargarEventos();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Faltan datos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        actionPanel.add(btnModificar);
        actionPanel.add(btnSismograma);
        actionPanel.add(btnMapa);
        actionPanel.add(Box.createHorizontalStrut(20));
        actionPanel.add(comboAccion);
        actionPanel.add(btnAceptar);

        dialog.add(actionPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
        cargarEventos();
    }

    private JPanel crearPanelResumen(EventoSismico ev) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 15, 15));
        panel.setBackground(BG_DARK);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel cardFisica = new JPanel(new GridLayout(2, 2, 20, 20));
        estilizarCard(cardFisica, "Datos Físicos");
        
        agregarDato(cardFisica, "Magnitud", String.valueOf(ev.getValorMagnitud()) + " Escala Ritcher", BTN_BLUE);
        String profundidad = (ev.getClasificacion() != null) ? ev.getClasificacion().getNombre() : "Sin Definir";
        agregarDato(cardFisica, "Profundidad / Clasif.", profundidad, TEXT_PRIMARY);
        agregarDato(cardFisica, "Latitud Epicentro", ev.tomarLatitudEpicentro() + "°", TEXT_PRIMARY);
        agregarDato(cardFisica, "Longitud Epicentro", ev.tomarLongitudEpicentro() + "°", TEXT_PRIMARY);

        JPanel cardClasif = new JPanel(new GridLayout(2, 2, 20, 20));
        estilizarCard(cardClasif, "Clasificación y Estado");
        
        String origen = (ev.getOrigenGeneracion() != null) ? ev.getOrigenGeneracion().getNombre() : "---";
        String alcance = (ev.getAlcanceSismo() != null) ? ev.getAlcanceSismo().getNombre() : "---";
        
        agregarDato(cardClasif, "Origen Generación", origen, TEXT_PRIMARY);
        agregarDato(cardClasif, "Alcance Sismo", alcance, TEXT_PRIMARY);
        agregarDato(cardClasif, "Estado Actual", ev.getEstado().getNombre(), BTN_GREEN);
        int cantEstaciones = (ev.getSerieTemporal() != null) ? ev.getSerieTemporal().size() : 0;
        agregarDato(cardClasif, "Estaciones Reportadas", String.valueOf(cantEstaciones), TEXT_PRIMARY);

        panel.add(cardFisica);
        panel.add(cardClasif);
        return panel;
    }

    private void estilizarCard(JPanel panel, String titulo) {
        panel.setBackground(BG_SURFACE);
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(60, 60, 60)), titulo, 
            0, 0, FONT_BOLD, TEXT_SECONDARY
        ));
    }

    private void agregarDato(JPanel panel, String label, String valor, Color colorValor) {
        JPanel pDato = new JPanel(new BorderLayout());
        pDato.setBackground(BG_SURFACE);
        
        JLabel lblTitulo = new JLabel(label);
        lblTitulo.setForeground(TEXT_SECONDARY);
        lblTitulo.setFont(new Font("Roboto", Font.PLAIN, 12));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(colorValor);
        lblValor.setFont(new Font("Roboto", Font.BOLD, 18));
        
        pDato.add(lblTitulo, BorderLayout.NORTH);
        pDato.add(lblValor, BorderLayout.CENTER);
        panel.add(pDato);
    }

    private static class ModernButton extends JButton {
        private Color accentColor;
        
        public ModernButton(String text, Color color) {
            super(text);
            this.accentColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(new EmptyBorder(10, 20, 10, 20));
            setForeground(Color.WHITE);
            setFont(FONT_BOLD);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { if(isEnabled()) setForeground(accentColor.brighter()); }
                @Override
                public void mouseExited(MouseEvent e) { if(isEnabled()) setForeground(Color.WHITE); }
            });
        }
        public void setAccentColor(Color c) { this.accentColor = c; repaint(); }
        @Override
        protected void paintComponent(Graphics g) {
            if (!isEnabled()) { g.setColor(new Color(60,60,60)); setForeground(Color.GRAY); } 
            else { g.setColor(accentColor); setForeground(Color.WHITE); }
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            super.paintComponent(g);
        }
    }

    private void actualizarTextoMuestras(EventoSismico evento, JTextArea area) {
        StringBuilder sb = new StringBuilder();
        sb.append("DATOS TÉCNICOS ID: ").append(evento.getIdEvento()).append("\n------------------\n");
        if (evento.getSerieTemporal() != null) {
            for (SerieTemporal serie : evento.getSerieTemporal()) {
                sb.append("ESTACIÓN: ").append(serie.getEstacionSismica().getNombre()).append("\n");
                for (MuestraSismica m : serie.getMuestrasSismicas()) {
                    sb.append(" > ").append(m.getFechaHoraMuestra()).append("\n");
                }
            }
        }
        area.setText(sb.toString());
    }

    private void mostrarFormularioEdicion(JDialog parent, EventoSismico evento, Runnable onSave) {
        JDialog form = new JDialog(parent, "Editar Datos", true);
        form.setSize(450, 350);
        form.getContentPane().setBackground(BG_DARK);
        form.setLayout(new GridBagLayout());
        form.setLocationRelativeTo(parent);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        List<AlcanceSismo> alcances = gestor.getAlcances();
        List<OrigenDeGeneracion> origenes = gestor.getOrigenes();
        List<MagnitudRichter> magnitudes = gestor.getMagnitudes();

        JComboBox<String> cbAlc = new JComboBox<>(alcances.stream().map(AlcanceSismo::getNombre).toArray(String[]::new));
        JComboBox<String> cbOri = new JComboBox<>(origenes.stream().map(OrigenDeGeneracion::getNombre).toArray(String[]::new));
        JComboBox<String> cbMag = new JComboBox<>(magnitudes.stream().map(MagnitudRichter::getDescripcionMagnitud).toArray(String[]::new));

        if(evento.getAlcanceSismo() != null) cbAlc.setSelectedItem(evento.getAlcanceSismo().getNombre());
        if(evento.getOrigenGeneracion() != null) cbOri.setSelectedItem(evento.getOrigenGeneracion().getNombre());
        if(evento.tomarMagnitud() != null) cbMag.setSelectedItem(evento.tomarMagnitud().getDescripcionMagnitud());

        ModernButton btnGuardar = new ModernButton("Guardar Cambios", BTN_GREEN);
        btnGuardar.addActionListener(e -> {
            gestor.actualizarDatosEvento(evento, 
                alcances.get(cbAlc.getSelectedIndex()), 
                origenes.get(cbOri.getSelectedIndex()), 
                magnitudes.get(cbMag.getSelectedIndex())
            );
            JOptionPane.showMessageDialog(form, "Datos actualizados localmente.");
            onSave.run();
            form.dispose();
        });

        agregarCampoForm(form, "Alcance:", cbAlc, gbc, 0);
        agregarCampoForm(form, "Origen:", cbOri, gbc, 1);
        agregarCampoForm(form, "Magnitud:", cbMag, gbc, 2);
        gbc.gridy = 3; gbc.gridwidth = 2;
        form.add(btnGuardar, gbc);
        form.setVisible(true);
    }
    
    private void agregarCampoForm(JDialog form, String label, JComponent comp, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.weightx = 0.3; gbc.gridwidth = 1;
        JLabel l = new JLabel(label);
        l.setForeground(TEXT_PRIMARY);
        l.setFont(FONT_MAIN);
        form.add(l, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        form.add(comp, gbc);
    }
}