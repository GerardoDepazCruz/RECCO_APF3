/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Glucosa;
import modelo.GlucosaDAO;
import vista.VentanaListaGlucosa;
import vista.VentanaRegistrarGlucosa;

public class ControladorGlucosa {
    private VentanaRegistrarGlucosa ventanaRegistrar;
    private VentanaListaGlucosa ventanaLista;
    private GlucosaDAO glucosaDAO;

    public ControladorGlucosa(VentanaRegistrarGlucosa ventanaRegistrar, VentanaListaGlucosa ventanaLista, GlucosaDAO glucosaDAO) {
        this.ventanaRegistrar = ventanaRegistrar;
        this.ventanaLista = ventanaLista;
        this.glucosaDAO = glucosaDAO;
        inicializarEventos();
    }

    private void inicializarEventos() {
        ventanaRegistrar.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarGlucosa();
            }
        });

        ventanaLista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListaGlucosa();
            }
        });
    }

    private void registrarGlucosa() {
        int idPaciente = Integer.parseInt(ventanaRegistrar.getTxtIdPaciente().getText());
        String fecha = ventanaRegistrar.getTxtFecha().getText();
        double nivelGlucosa = Double.parseDouble(ventanaRegistrar.getTxtNivelGlucosa().getText());
        String observaciones = ventanaRegistrar.getTxtObservaciones().getText();

        Glucosa glucosa = new Glucosa(0, idPaciente, fecha, nivelGlucosa, observaciones);
        boolean registrado = glucosaDAO.registrarGlucosa(glucosa);
        if (registrado) {
            ventanaRegistrar.mostrarMensaje("Glucosa registrada correctamente.");
        } else {
            ventanaRegistrar.mostrarMensaje("Error al registrar la glucosa.");
        }
    }

    private void cargarListaGlucosa() {
        int idPaciente = Integer.parseInt(ventanaLista.getTxtIdPaciente().getText());
        List<Glucosa> glucosas = glucosaDAO.obtenerGlucosaPorPaciente(idPaciente);
        ventanaLista.mostrarListaGlucosa(glucosas);
    }
}

