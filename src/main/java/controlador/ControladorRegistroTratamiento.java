package controlador;

import vista.VentanaRegistroTratamiento;
import BaseDatosConexion.CConexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorRegistroTratamiento {
    private VentanaRegistroTratamiento ventana;

    public ControladorRegistroTratamiento(VentanaRegistroTratamiento ventana) {
        this.ventana = ventana;
        cargarPacientesEnCombo();
        ventana.getBtnRegistrar().addActionListener(e -> registrarTratamiento());
    }

    private void registrarTratamiento() {
        String pacienteSeleccionado = (String) ventana.getComboPaciente().getSelectedItem();
        if (pacienteSeleccionado == null) {
            JOptionPane.showMessageDialog(ventana, "Seleccione un paciente.");
            return;
        }

        int idPaciente = Integer.parseInt(pacienteSeleccionado.split(" - ")[0]);
        String fechaInicio = ventana.getTxtFechaInicio().getText();
        String fechaFin = ventana.getTxtFechaFin().getText();
        String tipo = ventana.getTxtTipoTratamiento().getText();
        String observaciones = ventana.getTxtObservaciones().getText();

        String query = "INSERT INTO Tratamientos (id_paciente, fecha_inicio, fecha_fin, tipo_tratamiento, observaciones) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = new CConexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPaciente);
            stmt.setDate(2, Date.valueOf(fechaInicio));
            stmt.setDate(3, Date.valueOf(fechaFin));
            stmt.setString(4, tipo);
            stmt.setString(5, observaciones);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(ventana, "Tratamiento registrado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(ventana, "Error al registrar tratamiento.");
        }
    }

    private void cargarPacientesEnCombo() {
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

            for (String p : pacientes) {
                ventana.getComboPaciente().addItem(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
