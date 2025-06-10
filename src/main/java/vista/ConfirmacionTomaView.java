/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConfirmacionTomaController;
import modelo.ConfirmacionToma;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConfirmacionTomaView extends JFrame {

    private JPanel panelChat;
    private JTextField tfMensaje;
    private JButton btnEnviar;
    private JComboBox<Integer> cbPaciente;

    private ConfirmacionTomaController controller;

    public ConfirmacionTomaView() {
        controller = new ConfirmacionTomaController();

        setTitle("Confirmar Toma - Chat WhatsApp");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con selección paciente
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(new Color(7, 94, 84)); // verde WhatsApp
        JLabel lblPaciente = new JLabel("Paciente ID:");
        lblPaciente.setForeground(Color.WHITE);
        panelSuperior.add(lblPaciente);

        cbPaciente = new JComboBox<>();
        cargarPacientes();
        panelSuperior.add(cbPaciente);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel chat con scroll
        panelChat = new JPanel();
        panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
        panelChat.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(panelChat);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con campo texto y botón enviar
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.setBorder(new EmptyBorder(5,5,5,5));
        tfMensaje = new JTextField();
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBackground(new Color(7, 94, 84));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFocusPainted(false);

        panelInferior.add(tfMensaje, BorderLayout.CENTER);
        panelInferior.add(btnEnviar, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

        // Cargar mensajes al cambiar paciente
        cbPaciente.addActionListener(e -> cargarMensajes());

        // Enviar mensaje
        btnEnviar.addActionListener(e -> enviarMensaje());

        // Carga inicial
        if (cbPaciente.getItemCount() > 0) {
            cbPaciente.setSelectedIndex(0);
            cargarMensajes();
            mostrarMensajeRecordatorioInicial();
        }
    }

    private void cargarPacientes() {
        try {
            var con = controller.conexion;
            var st = con.createStatement();
            var rs = st.executeQuery("SELECT id FROM Pacientes");
            while (rs.next()) {
                cbPaciente.addItem(rs.getInt("id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando pacientes: " + e.getMessage());
        }
    }

    private void cargarMensajes() {
        panelChat.removeAll();
        int idPaciente = (Integer) cbPaciente.getSelectedItem();
        List<ConfirmacionToma> mensajes = controller.listarMensajes(idPaciente);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for (ConfirmacionToma m : mensajes) {
            agregarMensajeAlPanel(m.getMensaje(), sdf.format(m.getFechaEnvio()), m.isEnviadoPorPaciente());
        }
        panelChat.revalidate();
        panelChat.repaint();
    }

    private void agregarMensajeAlPanel(String texto, String hora, boolean esPaciente) {
    JPanel panelMensaje = new JPanel(new BorderLayout());
    panelMensaje.setOpaque(false);

    // HTML con ancho fijo para forzar salto de línea y evitar espacio extra
    String htmlTexto = "<html><div style='width:240px;'>" + texto + "</div></html>";
    JLabel lblTexto = new JLabel(htmlTexto);

    // Ajustar tamaño preferido para que el JLabel encierre justo el texto
    lblTexto.setSize(new Dimension(240, Short.MAX_VALUE));
    lblTexto.setPreferredSize(null);

    JLabel lblHora = new JLabel(hora);
    lblHora.setFont(new Font("Arial", Font.PLAIN, 10));
    lblHora.setForeground(Color.GRAY);

    // Panel contenedor del mensaje con BoxLayout vertical, sin relleno extra arriba/abajo
    JPanel panelContenido = new JPanel();
    panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
    panelContenido.add(lblTexto);
    panelContenido.add(lblHora);

    // Bordes ajustados para que no haya espacio vertical extra
    panelContenido.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    panelContenido.setOpaque(true);

    if (esPaciente) {
        panelContenido.setBackground(new Color(220, 248, 198));
        panelMensaje.add(panelContenido, BorderLayout.EAST);
        panelMensaje.setBorder(new EmptyBorder(5, 50, 5, 10));
    } else {
        panelContenido.setBackground(new Color(240, 240, 240));
        panelMensaje.add(panelContenido, BorderLayout.WEST);
        panelMensaje.setBorder(new EmptyBorder(5, 10, 5, 50));
    }

    // Limitar ancho máximo para que el cuadro no se extienda demasiado
    panelContenido.setMaximumSize(new Dimension(260, Integer.MAX_VALUE));

    panelChat.add(panelMensaje);
}



    private void mostrarMensajeRecordatorioInicial() {
        // Mensaje fijo del sistema al iniciar chat
        String medicamento = "tu medicamento"; // Aquí puedes obtener el nombre real si quieres
        String texto = "Hola, es hora de tomar " + medicamento + ". ¡Ya tomaste? Haz click en el botón para confirmar.";
        String hora = new SimpleDateFormat("HH:mm").format(new Date());

        agregarMensajeAlPanel(texto, hora, false);

        // Agregar botón confirmar debajo del mensaje
        JButton btnConfirmar = new JButton("Confirmar toma");
        btnConfirmar.setBackground(new Color(7, 94, 84));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);
        panelBoton.add(btnConfirmar);

        panelChat.add(panelBoton);
        panelChat.revalidate();
        panelChat.repaint();

        btnConfirmar.addActionListener(e -> {
            int idPaciente = (Integer) cbPaciente.getSelectedItem();
            ConfirmacionToma confirmacion = new ConfirmacionToma(0, idPaciente, "Confirmo que he tomado el medicamento.", new Date(), true);
            if (controller.agregarMensaje(confirmacion)) {
                // Remover botón para evitar múltiples confirmaciones
                panelChat.remove(panelBoton);
                agregarMensajeAlPanel("Gracias, tu toma ha sido registrada.", new SimpleDateFormat("HH:mm").format(new Date()), false);
                panelChat.revalidate();
                panelChat.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la confirmación.");
            }
        });
    }

    private void enviarMensaje() {
        String texto = tfMensaje.getText().trim();
        if (texto.isEmpty()) return;

        int idPaciente = (Integer) cbPaciente.getSelectedItem();
        ConfirmacionToma mensaje = new ConfirmacionToma(0, idPaciente, texto, new Date(), true);

        if (controller.agregarMensaje(mensaje)) {
            tfMensaje.setText("");
            cargarMensajes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al enviar el mensaje.");
        }
    }
}

