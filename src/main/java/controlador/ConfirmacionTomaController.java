/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import BaseDatosConexion.CConexion;
import modelo.ConfirmacionToma;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfirmacionTomaController {
    public Connection conexion;

    public ConfirmacionTomaController() {
        CConexion con = new CConexion();
        conexion = con.establecerConexion();
    }

    public boolean agregarMensaje(ConfirmacionToma c) {
        String sql = "INSERT INTO ConfirmacionToma (id_paciente, mensaje, fecha_envio, enviado_por_paciente) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, c.getIdPaciente());
            ps.setString(2, c.getMensaje());
            ps.setTimestamp(3, new Timestamp(c.getFechaEnvio().getTime()));
            ps.setBoolean(4, c.isEnviadoPorPaciente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al enviar mensaje: " + e.getMessage());
            return false;
        }
    }

    public List<ConfirmacionToma> listarMensajes(int idPaciente) {
        List<ConfirmacionToma> lista = new ArrayList<>();
        String sql = "SELECT * FROM ConfirmacionToma WHERE id_paciente = ? ORDER BY fecha_envio";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ConfirmacionToma c = new ConfirmacionToma();
                c.setId(rs.getInt("id"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setMensaje(rs.getString("mensaje"));
                c.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                c.setEnviadoPorPaciente(rs.getBoolean("enviado_por_paciente"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar mensajes: " + e.getMessage());
        }
        return lista;
    }
}

