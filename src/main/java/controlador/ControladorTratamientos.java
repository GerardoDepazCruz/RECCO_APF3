package controlador;

import modelo.Tratamiento;
import vista.VentanaConsultaTratamientos;
import vista.VentanaRegistroTratamiento;
import BaseDatosConexion.CConexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTratamientos {
    private VentanaConsultaTratamientos ventanaConsulta;
    private VentanaRegistroTratamiento ventanaRegistro;

    public ControladorTratamientos(VentanaConsultaTratamientos ventana) {
        this.ventanaConsulta = ventana;

        // Acción del botón consultar tratamientos
        ventanaConsulta.getBtnConsultar().addActionListener(e -> consultarTratamientos());
    }

    public ControladorTratamientos(VentanaRegistroTratamiento ventana) {
        this.ventanaRegistro = ventana;
    }

    // Método para consultar tratamientos de un paciente
    public void consultarTratamientos() {
        int idPaciente = obtenerIdPacienteSeleccionado();

        if (idPaciente != -1) {
            List<Tratamiento> tratamientos = obtenerTratamientosDeBD(idPaciente);
            ventanaConsulta.mostrarTratamientos(tratamientos);
        } else {
            JOptionPane.showMessageDialog(ventanaConsulta, "Por favor, seleccione un paciente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Obtener el ID del paciente seleccionado en el ComboBox
    private int obtenerIdPacienteSeleccionado() {
        String selectedPaciente = (String) ventanaConsulta.getComboPaciente().getSelectedItem();
        if (selectedPaciente != null) {
            String[] parts = selectedPaciente.split(" - ");
            return Integer.parseInt(parts[0]);
        }
        return -1;  // Indica que no se seleccionó ningún paciente
    }

    // Obtener los tratamientos desde la base de datos
    private List<Tratamiento> obtenerTratamientosDeBD(int idPaciente) {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String query = "SELECT * FROM Tratamientos WHERE id_paciente = ?";

        try (Connection conn = new CConexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");
                String tipoTratamiento = rs.getString("tipo_tratamiento");
                String observaciones = rs.getString("observaciones");
                tratamientos.add(new Tratamiento(id, idPaciente, fechaInicio, fechaFin, tipoTratamiento, observaciones));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tratamientos;
    }

    // Cargar pacientes en el JComboBox
    public void cargarPacientesEnCombo(JComboBox<String> comboPaciente) {
        List<String> pacientes = obtenerPacientesDeBD();

        // Verificar si la lista de pacientes está vacía
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron pacientes en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (String paciente : pacientes) {
                comboPaciente.addItem(paciente);
            }
        }
    }

    // Obtener pacientes desde la base de datos
    private List<String> obtenerPacientesDeBD() {
        List<String> pacientes = new ArrayList<>();
        String query = "SELECT id, nombre FROM Pacientes";

        try (Connection conn = new CConexion().establecerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                pacientes.add(id + " - " + nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public boolean registrarTratamiento(String pacienteSeleccionado, String fechaInicio, String fechaFin, String tipoTratamiento, String observaciones) {
        try {
            String[] parts = pacienteSeleccionado.split(" - ");
            int idPaciente = Integer.parseInt(parts[0]);

            String sql = "INSERT INTO Tratamientos (id_paciente, fecha_inicio, fecha_fin, tipo_tratamiento, observaciones) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = new CConexion().establecerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, idPaciente);
                ps.setString(2, fechaInicio);
                ps.setString(3, fechaFin);
                ps.setString(4, tipoTratamiento);
                ps.setString(5, observaciones);
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
