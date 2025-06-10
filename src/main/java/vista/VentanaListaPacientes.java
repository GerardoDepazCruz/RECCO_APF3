/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class VentanaListaPacientes extends JFrame {
    public JTable tablaPacientes;
    public DefaultTableModel modelo;

    public VentanaListaPacientes() {
        setTitle("Listado de Pacientes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1010, 350);
        setLocationRelativeTo(null);

        // Columnas
        String[] columnas = {"Nombre", "DNI", "Edad", "Tipo de Diabetes", "Contacto Emergencia"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos que las celdas no sean editables
            }
        };

        // Tabla
        tablaPacientes = new JTable(modelo);
        tablaPacientes.setFillsViewportHeight(true);
        tablaPacientes.setRowHeight(25);
        tablaPacientes.getTableHeader().setReorderingAllowed(false);
        tablaPacientes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaPacientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaPacientes);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(scroll, BorderLayout.CENTER);
    }
}



