/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GlucosaDAO {

    private Connection connection;

    public GlucosaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registrarGlucosa(Glucosa glucosa) {
        String query = "INSERT INTO Glucosa (id_paciente, fecha, nivel_glucosa, observaciones) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, glucosa.getIdPaciente());
            stmt.setString(2, glucosa.getFecha());
            stmt.setDouble(3, glucosa.getNivelGlucosa());
            stmt.setString(4, glucosa.getObservaciones());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Glucosa> obtenerGlucosaPorPaciente(int idPaciente) {
        List<Glucosa> glucosas = new ArrayList<>();
        String query = "SELECT * FROM Glucosa WHERE id_paciente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Glucosa glucosa = new Glucosa(
                        rs.getInt("id"),
                        rs.getInt("id_paciente"),
                        rs.getString("fecha"),
                        rs.getDouble("nivel_glucosa"),
                        rs.getString("observaciones")
                );
                glucosas.add(glucosa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return glucosas;
    }
}
