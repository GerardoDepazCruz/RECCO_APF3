package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modelo.Tratamiento;

public class VentanaConsultaTratamientos extends JFrame {
    private JComboBox<String> comboPaciente;
    private JTable tablaTratamientos;
    private JButton btnConsultar;

    public VentanaConsultaTratamientos() {
        // Configuración básica de la ventana
        setTitle("Consulta de Tratamientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(panelPrincipal);

        // Panel superior para ComboBox y botón
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 1, 10, 10));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Seleccionar Paciente"));

        // ComboBox para seleccionar paciente
        comboPaciente = new JComboBox<>();
        comboPaciente.setPreferredSize(new Dimension(250, 30));
        panelSuperior.add(comboPaciente);

        // Botón para consultar
        btnConsultar = new JButton("Consultar Tratamientos");
        btnConsultar.setPreferredSize(new Dimension(250, 40));
        panelSuperior.add(btnConsultar);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Tabla de tratamientos dentro de un JScrollPane
        tablaTratamientos = new JTable();
        tablaTratamientos.setFillsViewportHeight(true);
        tablaTratamientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tablaTratamientos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Estilo del botón
        btnConsultar.setBackground(new Color(0, 123, 255));  // Color azul
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setBorderPainted(false);
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));

        // Estilo de la tabla
        tablaTratamientos.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaTratamientos.setRowHeight(25);
        tablaTratamientos.setGridColor(Color.LIGHT_GRAY);
        tablaTratamientos.setSelectionBackground(new Color(0, 123, 255));
        tablaTratamientos.setSelectionForeground(Color.WHITE);

        // Hacer visible la ventana
        setVisible(true);
    }

    public JComboBox<String> getComboPaciente() {
        return comboPaciente;
    }

    public JTable getTablaTratamientos() {
        return tablaTratamientos;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public void mostrarTratamientos(List<Tratamiento> tratamientos) {
        String[] columnNames = {"ID", "Fecha Inicio", "Fecha Fin", "Tipo de Tratamiento", "Observaciones"};
        Object[][] data = new Object[tratamientos.size()][5];

        for (int i = 0; i < tratamientos.size(); i++) {
            Tratamiento tratamiento = tratamientos.get(i);
            data[i][0] = tratamiento.getId();
            data[i][1] = tratamiento.getFechaInicio();
            data[i][2] = tratamiento.getFechaFin();
            data[i][3] = tratamiento.getTipoTratamiento();
            data[i][4] = tratamiento.getObservaciones();
        }

        tablaTratamientos.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
