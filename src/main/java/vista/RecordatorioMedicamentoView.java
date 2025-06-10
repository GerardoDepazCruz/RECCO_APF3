/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.RecordatorioMedicamentoController;
import modelo.RecordatorioMedicamento;

import javax.swing.*;
import java.awt.*;

public class RecordatorioMedicamentoView extends JFrame {

    private JComboBox<Integer> cbPaciente;
    private JTextField tfNombreMedicina;
    private JComboBox<String> cbFrecuencia;
    private JButton btnGuardar;
    private JTextArea taResumen;

    private RecordatorioMedicamentoController controller;

    public RecordatorioMedicamentoView() {
        controller = new RecordatorioMedicamentoController();

        setTitle("Configurar Recordatorio de Medicamento");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con título en amarillo y fondo celeste
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBackground(new Color(173, 216, 230)); // celeste claro

        // Título con fondo amarillo
        JLabel lblTitulo = new JLabel("CONFIGURAR RECORDATORIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(Color.YELLOW);
        lblTitulo.setForeground(new Color(0, 100, 200));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel formulario con GridBagLayout (fondo celeste)
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(173, 216, 230)); // celeste claro
        add(panelFormulario, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Paciente
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panelFormulario.add(new JLabel("ID Paciente:"), gbc);

        cbPaciente = new JComboBox<>();
        cargarPacientes();
        gbc.gridx = 1;
        panelFormulario.add(cbPaciente, gbc);

        // Nombre medicina
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulario.add(new JLabel("Nombre Medicina:"), gbc);

        tfNombreMedicina = new JTextField();
        gbc.gridx = 1;
        panelFormulario.add(tfNombreMedicina, gbc);

        // Frecuencia
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulario.add(new JLabel("Frecuencia:"), gbc);

        cbFrecuencia = new JComboBox<>(new String[] {
            "Diaria", "Semanal", "Mensual", "Cada 8 horas", "Cada 12 horas"
        });
        gbc.gridx = 1;
        panelFormulario.add(cbFrecuencia, gbc);

        // Botón guardar (azul)
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 0, 204)); // Azul oscuro
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panelFormulario.add(btnGuardar, gbc);

        // Área resumen (fondo celeste)
        taResumen = new JTextArea(8, 25);
        taResumen.setEditable(false);
        taResumen.setLineWrap(true);
        taResumen.setWrapStyleWord(true);
        taResumen.setBackground(new Color(173, 216, 230)); // celeste claro
        JScrollPane scrollResumen = new JScrollPane(taResumen);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        panelFormulario.add(scrollResumen, gbc);

        btnGuardar.addActionListener(e -> guardarRecordatorio());
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

    private void guardarRecordatorio() {
        try {
            int idPaciente = (Integer) cbPaciente.getSelectedItem();
            String nombreMed = tfNombreMedicina.getText().trim();
            String frecuencia = (String) cbFrecuencia.getSelectedItem();

            if (nombreMed.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre de la medicina.");
                return;
            }

            RecordatorioMedicamento r = new RecordatorioMedicamento(0, idPaciente, nombreMed, frecuencia);
            if (controller.agregarRecordatorio(r)) {
                taResumen.setText("Recordatorio guardado:\nPaciente ID: " + idPaciente +
                        "\nMedicina: " + nombreMed +
                        "\nFrecuencia: " + frecuencia);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}



