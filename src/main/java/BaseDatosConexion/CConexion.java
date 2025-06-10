/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatosConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {

    Connection conectar = null;
    
    String usuario = "gerardo";
    String contraseña = "123";
    String bd = "Recordatorios";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection establecerConexion(){
        try {
            String cadena = "jdbc:sqlserver://"+ip+":"+puerto+";databaseName="+bd+";encrypt=true;trustServerCertificate=true";
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            //JOptionPane.showMessageDialog(null, "Se conectó correctamente a la base de datos");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, error: " + e.toString());
        }
        return conectar;
    }
}
