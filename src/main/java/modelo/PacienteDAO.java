package modelo;

import BaseDatosConexion.CConexion;
import java.sql.*;
import java.util.*;

public class PacienteDAO {
    public void insertarPaciente(Paciente paciente) {
        String sql = "INSERT INTO Pacientes (nombre, dni, edad, tipo_diabetes, contacto_emergencia) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = new CConexion().establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getDni());
            ps.setInt(3, paciente.getEdad());
            ps.setString(4, paciente.getTipoDiabetes());
            ps.setString(5, paciente.getContactoEmergencia());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar paciente: " + e.getMessage());
        }
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT nombre, dni, edad, tipo_diabetes, contacto_emergencia FROM Pacientes";

        try (Connection con = new CConexion().establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getInt("edad"),
                        rs.getString("tipo_diabetes"),
                        rs.getString("contacto_emergencia")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }
        return lista;
    }
}


