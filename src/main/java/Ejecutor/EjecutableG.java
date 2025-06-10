/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutor;

import controlador.ControladorGlucosa;
import vista.VentanaRegistrarGlucosa;
import vista.VentanaListaGlucosa;
import modelo.GlucosaDAO;
import BaseDatosConexion.CConexion;
import java.sql.Connection;

public class EjecutableG {
    public static void main(String[] args) {
        // Crear las instancias de las vistas
        VentanaRegistrarGlucosa ventanaRegistrar = new VentanaRegistrarGlucosa();
        VentanaListaGlucosa ventanaLista = new VentanaListaGlucosa();

        // Crear la conexión a la base de datos usando tu clase CConexion
        CConexion conexion = new CConexion();
        Connection connection = conexion.establecerConexion();

        if (connection != null) {
            // Crear la instancia de GlucosaDAO con la conexión
            GlucosaDAO glucosaDAO = new GlucosaDAO(connection);

            // Crear el controlador que conecta las vistas y el modelo (DAO)
            ControladorGlucosa controlador = new ControladorGlucosa(ventanaRegistrar, ventanaLista, glucosaDAO);

            // Hacer visible la ventana principal de registro (VentanaRegistrarGlucosa)
            ventanaRegistrar.setVisible(true);
        } else {
            System.out.println("Error al conectar con la base de datos. El programa se detendrá.");
        }
    }
}
