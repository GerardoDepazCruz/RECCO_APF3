package controlador;

import modelo.*;
import vista.*;

import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPaciente {
    private VentanaRegistroPaciente vista;
    private PacienteDAO dao;

    public ControladorPaciente(VentanaRegistroPaciente vista) {
        this.vista = vista;
        this.dao = new PacienteDAO();

        // Evento para el botón Registrar
        this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });
    }

    private void guardarPaciente() {
        try {
            String nombre = vista.getNombre();
            String dni = vista.getDni();
            int edad = vista.getEdad();
            String tipo = vista.getTipoDiabetes();
            String contacto = vista.getContactoEmergencia();

            // Validación básica
            if (nombre.isEmpty() || dni.isEmpty() || edad <= 0 || contacto.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos correctamente.");
                return;
            }

            Paciente paciente = new Paciente(nombre, dni, edad, tipo, contacto);
            dao.insertarPaciente(paciente);

            JOptionPane.showMessageDialog(vista, "Paciente registrado correctamente.");
            vista.limpiarCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al registrar paciente: " + ex.getMessage());
        }
    }

    // Si luego quieres agregar la funcionalidad de ver pacientes, se puede extender aquí:
    public void mostrarPacientes() {
        VentanaListaPacientes ventanaTabla = new VentanaListaPacientes();
        List<Paciente> lista = dao.listarPacientes();

        DefaultTableModel modelo = ventanaTabla.modelo;
        modelo.setRowCount(0);

        for (Paciente p : lista) {
            modelo.addRow(new Object[]{
                p.getNombre(),
                p.getDni(),
                p.getEdad(),
                p.getTipoDiabetes(),
                p.getContactoEmergencia()
            });
        }

        ventanaTabla.setVisible(true);
    }
}
