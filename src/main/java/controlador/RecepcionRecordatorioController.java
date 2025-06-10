/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import BaseDatosConexion.CConexion;
import modelo.RecepcionRecordatorio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RecepcionRecordatorioController {
    private Connection conexion;

    public RecepcionRecordatorioController() {
        CConexion con = new CConexion();
        conexion = con.establecerConexion();
    }

    // Listar todos los registros de recepción
    public List<RecepcionRecordatorio> listarRecepciones() {
        List<RecepcionRecordatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM RecepcionRecordatorioWhatsApp ORDER BY fecha_envio DESC";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                RecepcionRecordatorio r = new RecepcionRecordatorio();
                r.setId(rs.getInt("id"));
                r.setIdPaciente(rs.getInt("id_paciente"));
                r.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                r.setRecibido(rs.getBoolean("recibido"));
                lista.add(r);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar recepciones: " + e.getMessage());
        }
        return lista;
    }
}

