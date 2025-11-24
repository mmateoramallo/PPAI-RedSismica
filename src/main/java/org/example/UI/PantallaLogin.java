package org.example.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent; // Importación necesaria para setCaretColor
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import org.example.DAO.AnalistaDAO;
import org.example.modelos.Analista;

public class PantallaLogin extends JFrame {

    // --- Paleta de Colores Moderna ---
    private static final Color BG_APP = new Color(18, 18, 18);       
    private static final Color BG_CARD = new Color(35, 35, 35);      
    private static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    private static final Color TEXT_SECONDARY = new Color(170, 170, 170);
    private static final Color ACCENT_COLOR = new Color(64, 158, 255); 
    private static final Color INPUT_BG = new Color(45, 45, 45);
    private static final Color INPUT_BORDER = new Color(80, 80, 80);
    // Usaremos Segoe UI ya que es la que mejor simula el diseño que quieres
    private static final Font FONT_MAIN = new Font("Segoe UI", Font.PLAIN, 14);


    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private final AnalistaDAO analistaDAO = new AnalistaDAO();

    public PantallaLogin() {
        setTitle("Acceso al Sistema");
        // CORRECCIÓN 1: Aumentamos la altura del frame para dar aire
        setSize(500, 700); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panel Principal (Fondo)
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG_APP);

        // --- TARJETA CENTRAL (Con bordes redondeados) ---
        JPanelRound card = new JPanelRound();
        card.setBackground(BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40)); 
        
        // Ajustamos la dimensión para que se vea completo
        card.setPreferredSize(new Dimension(380, 500)); 

        // 1. HEADER (Icono + Títulos)
        JLabel lblIcon = new JLabel("⚡", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 65));
        lblIcon.setForeground(ACCENT_COLOR);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblIcon.setBorder(new EmptyBorder(10, 0, 10, 0)); 

        JLabel lblTitle = new JLabel("Red Sísmica");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Panel de Control");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(TEXT_SECONDARY);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. FORMULARIO
        JLabel lblUser = crearLabel("Usuario / Legajo");
        txtUsuario = crearInput();

        JLabel lblPass = crearLabel("Contraseña");
        txtPassword = new JPasswordField();
        estilizarInput(txtPassword);

        // 3. BOTÓN
        JButton btnLogin = new JButton("Ingresar");
        estilizarBoton(btnLogin);

        // --- LÓGICA DE VALIDACIÓN (MISMA LÓGICA) ---
        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtPassword.getPassword());

            if (!user.isEmpty() && !pass.isEmpty()) {
                try {
                    Analista analistaRegistrado = analistaDAO.buscarPorNombre(user);
                    
                    if (analistaRegistrado != null) {
                        if (analistaRegistrado.getNombre().equals(user) && 
                            analistaRegistrado.getContraseña().equals(pass)) {
                            
                            abrirBienvenida(analistaRegistrado.getNombre(), analistaRegistrado.getLegajo());
                            
                        } else {
                            mostrarError("Contraseña incorrecta.");
                        }
                    } else {
                        mostrarError("Usuario no encontrado.");
                    }
                } catch (Exception ex) {
                    mostrarError("Error de conexión: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                mostrarError("Por favor complete todos los campos.");
            }
        });

        // --- ARMADO DE LA TARJETA ---
        card.add(lblIcon);
        card.add(Box.createVerticalStrut(10));
        card.add(lblTitle);
        card.add(lblSub);
        
        card.add(Box.createVerticalStrut(40)); 
        
        card.add(lblUser);
        card.add(Box.createVerticalStrut(5));
        card.add(txtUsuario);
        
        card.add(Box.createVerticalStrut(20)); 
        
        card.add(lblPass);
        card.add(Box.createVerticalStrut(5));
        card.add(txtPassword);
        
        card.add(Box.createVerticalStrut(40)); 
        
        card.add(btnLogin);
        card.add(Box.createVerticalStrut(10)); 

        mainPanel.add(card);
        add(mainPanel);
    }

    private void abrirBienvenida(String nombre, String legajo) {
        PantallaBienvenida bienvenida = new PantallaBienvenida(nombre, legajo);
        bienvenida.setVisible(true);
        this.dispose();
    }
    
    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de Acceso", JOptionPane.ERROR_MESSAGE);
    }

    // --- Helpers Visuales ---

    private JLabel crearLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(TEXT_SECONDARY);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setAlignmentX(Component.LEFT_ALIGNMENT); 
        return l;
    }

    private JTextField crearInput() {
        JTextField t = new JTextField();
        estilizarInput(t);
        return t;
    }

    private void estilizarInput(JComponent c) {
        c.setBackground(INPUT_BG);
        c.setForeground(Color.WHITE);
        
        // CORRECCIÓN 2: Casting para usar setCaretColor
        if (c instanceof JTextComponent) {
            ((JTextComponent) c).setCaretColor(ACCENT_COLOR); 
        }
        
        // Borde compuesto
        c.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(INPUT_BORDER, 1), 
            new EmptyBorder(10, 15, 10, 15) 
        ));
        
        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Dimensiones para inputs largos y con altura cómoda
        c.setMinimumSize(new Dimension(200, 45)); 
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); 
        c.setPreferredSize(new Dimension(300, 45)); 
        ((JComponent) c).setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBorder(new EmptyBorder(12, 0, 12, 0)); 
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT); 
        
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_COLOR.brighter()); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(ACCENT_COLOR); }
        });
    }

    // --- CLASE INTERNA PARA PANEL REDONDEADO ---
    // Esto resuelve que la tarjeta se vea plana
    class JPanelRound extends JPanel {
        
        // CORRECCIÓN 3: Llamar a super.paintComponent() para asegurar que los hijos se pinten.
        // Aunque la convención es dibujar el fondo, y luego los hijos, 
        // a veces BoxLayout necesita ayuda. Aquí pintamos el fondo redondeado.
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            // Dibuja el fondo redondeado
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            // No llamamos a super.paintComponent(g) si estamos dibujando el fondo
            // porque ya lo estamos manejando. Los componentes se dibujarán encima.
        }
    }
}