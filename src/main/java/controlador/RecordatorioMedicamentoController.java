/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import BaseDatosConexion.CConexion;
import modelo.RecordatorioMedicamento;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioMedicamentoController {
    public Connection conexion;

    public RecordatorioMedicamentoController() {
        CConexion con = new CConexion();
        conexion = con.establecerConexion();
    }

    public boolean agregarRecordatorio(RecordatorioMedicamento r) {
        String sql = "INSERT INTO RecordatoriosMedicamento (id_paciente, nombre_medicina, frecuencia) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, r.getIdPaciente());
            ps.setString(2, r.getNombreMedicina());
            ps.setString(3, r.getFrecuencia());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar recordatorio: " + e.getMessage());
            return false;
        }
    }

    public List<RecordatorioMedicamento> listarRecordatorios() {
        List<RecordatorioMedicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM RecordatoriosMedicamento ORDER BY id";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                RecordatorioMedicamento r = new RecordatorioMedicamento();
                r.setId(rs.getInt("id"));
                r.setIdPaciente(rs.getInt("id_paciente"));
                r.setNombreMedicina(rs.getString("nombre_medicina"));
                r.setFrecuencia(rs.getString("frecuencia"));
                lista.add(r);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar recordatorios: " + e.getMessage());
        }
        return lista;
    }
}

