package Ejecutor;
import controlador.ControladorPaciente;
import vista.VentanaRegistroPaciente;

public class Ejecutable {
    public static void main(String[] args) {
        VentanaRegistroPaciente vista = new VentanaRegistroPaciente();
        ControladorPaciente controlador = new ControladorPaciente(vista);
        vista.setVisible(true);
    }
}
