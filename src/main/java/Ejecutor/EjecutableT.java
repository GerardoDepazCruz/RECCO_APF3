/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutor;

import controlador.ControladorTratamientos;
import vista.VentanaRegistroTratamiento;
import javax.swing.SwingUtilities;

public class EjecutableT {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear la ventana de registro
            VentanaRegistroTratamiento ventanaRegistro = new VentanaRegistroTratamiento();

            // Crear el controlador y asociarlo con la ventana
            ControladorTratamientos controlador = new ControladorTratamientos(ventanaRegistro);

            // Cargar pacientes en el ComboBox
            controlador.cargarPacientesEnCombo(ventanaRegistro.getComboPaciente());

            // Hacer visible la ventana
            ventanaRegistro.setVisible(true);
        });
    }
}

