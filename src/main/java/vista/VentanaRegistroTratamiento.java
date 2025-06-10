package vista;

import controlador.ControladorTratamientos;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaRegistroTratamiento extends JFrame {
    private JComboBox<String> comboPaciente;
    private JTextField txtFechaInicio, txtFechaFin, txtTipoTratamiento;
    private JTextArea txtObservaciones;
    private JButton btnRegistrar;
    private JLabel lblResumen;
    private ControladorTratamientos controlador;

    public VentanaRegistroTratamiento() {
        controlador = new ControladorTratamientos(this);

        setTitle("Registro de Tratamiento");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Título centrado "MIS TRATAMIENTOS"
        JLabel lblTitulo = new JLabel("MIS TRATAMIENTOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 100, 200)); // Azul oscuro
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lblTitulo, gbc);

        // Etiqueta y combo paciente
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Paciente:"), gbc);

        comboPaciente = new JComboBox<>();
        controlador.cargarPacientesEnCombo(comboPaciente);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(comboPaciente, gbc);

        // Fecha Inicio
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha Inicio:"), gbc);

        txtFechaInicio = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtFechaInicio, gbc);

        // Fecha Fin
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha Fin:"), gbc);

        txtFechaFin = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtFechaFin, gbc);

        // Tipo Tratamiento
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo Tratamiento:"), gbc);

        txtTipoTratamiento = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtTipoTratamiento, gbc);

        // Observaciones (etiqueta arriba)
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Observaciones:"), gbc);

        // Área de texto para observaciones con scroll
        txtObservaciones = new JTextArea(6, 15);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtObservaciones);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        add(scroll, gbc);

        // Botón Registrar amarillo
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(255, 255, 0)); // Amarillo puro
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.setFocusPainted(false);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 15, 10, 15);
        add(btnRegistrar, gbc);

        // Label resumen debajo del botón (inicialmente vacío)
        lblResumen = new JLabel(" ");
        lblResumen.setFont(new Font("Arial", Font.ITALIC, 14));
        lblResumen.setForeground(new Color(50, 50, 50));
        gbc.gridy++;
        gbc.insets = new Insets(10, 15, 20, 15);
        add(lblResumen, gbc);

        // Acción del botón para mostrar resumen con hora actual
        btnRegistrar.addActionListener(e -> registrarTratamiento());
    }

    private void registrarTratamiento() {
        String pacienteSeleccionado = (String) comboPaciente.getSelectedItem();
        String fechaInicio = txtFechaInicio.getText().trim();
        String fechaFin = txtFechaFin.getText().trim();
        String tipoTratamiento = txtTipoTratamiento.getText().trim();
        String observaciones = txtObservaciones.getText().trim();

        if (pacienteSeleccionado == null || pacienteSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente.");
            return;
        }
        if (fechaInicio.isEmpty() || tipoTratamiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios.");
            return;
        }

        boolean exito = controlador.registrarTratamiento(pacienteSeleccionado, fechaInicio, fechaFin, tipoTratamiento, observaciones);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Tratamiento registrado correctamente.");
            mostrarResumen();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar tratamiento.");
        }
    }

    private void mostrarResumen() {
        String paciente = (String) comboPaciente.getSelectedItem();
        String inicio = txtFechaInicio.getText().trim();
        String fin = txtFechaFin.getText().trim();
        String tipo = txtTipoTratamiento.getText().trim();
        String obs = txtObservaciones.getText().trim();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
        String horaActual = sdf.format(new Date());

        String resumen = "<html><body style='width: 320px;'>" +
                "<b>Último registro:</b> " + horaActual + "<br>" +
                "<b>Paciente:</b> " + (paciente != null ? paciente : "N/A") + "<br>" +
                "<b>Fecha Inicio:</b> " + inicio + "<br>" +
                "<b>Fecha Fin:</b> " + fin + "<br>" +
                "<b>Tipo Tratamiento:</b> " + tipo + "<br>" +
                "<b>Observaciones:</b> " + obs +
                "</body></html>";

        lblResumen.setText(resumen);
    }

    // Getters para acceso externo si es necesario
    public JComboBox<String> getComboPaciente() {
        return comboPaciente;
    }

    public JTextField getTxtFechaInicio() {
        return txtFechaInicio;
    }

    public JTextField getTxtFechaFin() {
        return txtFechaFin;
    }

    public JTextField getTxtTipoTratamiento() {
        return txtTipoTratamiento;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
}
