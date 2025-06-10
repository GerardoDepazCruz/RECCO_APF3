package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modelo.Glucosa;

public class VentanaListaGlucosa extends JFrame {
    private JTextField txtIdPaciente;
    private JButton btnActualizar;
    private JTable tablaGlucosa;

    public VentanaListaGlucosa() {
        setTitle("Lista de Niveles de Glucosa");
        setIconImage(new ImageIcon("C:\\Users\\Gerardo\\Pictures\\Saved Pictures\\diabetes.png").getImage());

 // Agregar un ícono
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configuración del panel principal con BorderLayout
        setLayout(new BorderLayout());
        
        // Panel superior para el formulario
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblIdPaciente = new JLabel("ID Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelTop.add(lblIdPaciente, gbc);
        
        txtIdPaciente = new JTextField(15); // Más grande para mejorar la visibilidad
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelTop.add(txtIdPaciente, gbc);

        // Botón para actualizar
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));  // Fuentes más legibles
        btnActualizar.setBackground(new Color(54, 157, 245));  // Color de fondo agradable
        btnActualizar.setForeground(Color.WHITE);  // Texto blanco para mejor contraste
        btnActualizar.setFocusPainted(false);  // Sin el borde de enfoque
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelTop.add(btnActualizar, gbc);

        add(panelTop, BorderLayout.NORTH);

        // Configuración de la tabla
        tablaGlucosa = new JTable();
        tablaGlucosa.setRowHeight(30);  // Hacer que las filas sean más altas para mayor legibilidad
        tablaGlucosa.setFont(new Font("Arial", Font.PLAIN, 14));  // Fuente más legible
        tablaGlucosa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Configuración de la tabla dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaGlucosa);
        add(scrollPane, BorderLayout.CENTER);

        // Configuración de la ventana
        setSize(800, 500);  // Tamaño más amplio para mejor visibilidad
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setVisible(true);
    }

    public JTextField getTxtIdPaciente() {
        return txtIdPaciente;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    // Mostrar los datos en la tabla
    public void mostrarListaGlucosa(List<Glucosa> glucosas) {
        String[] columnNames = {"ID", "Fecha", "Nivel de Glucosa", "Observaciones"};
        Object[][] data = new Object[glucosas.size()][4];

        for (int i = 0; i < glucosas.size(); i++) {
            Glucosa glucosa = glucosas.get(i);
            data[i][0] = glucosa.getId();
            data[i][1] = glucosa.getFecha();
            data[i][2] = glucosa.getNivelGlucosa();
            data[i][3] = glucosa.getObservaciones();
        }

        tablaGlucosa.setModel(new javax.swing.table.DefaultTableModel(data, columnNames) {
            // Ajuste automático de columnas al tamaño de la ventana
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Evita la edición directa en las celdas
            }
        });
    }
}
