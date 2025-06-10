/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.RecepcionRecordatorioController;
import modelo.RecepcionRecordatorio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class RecepcionRecordatorioView extends JFrame {

    private JPanel panelPrincipal;
    private JPanel panelRecordatorios;
    private RecepcionRecordatorioController controller;

    public RecepcionRecordatorioView() {
        controller = new RecepcionRecordatorioController();

        setTitle("CANAL DE NOTIFICACIÓN");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con fondo dividido
        panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int height = getHeight();
                int width = getWidth();

                // Mitad superior celeste claro
                g.setColor(new Color(173, 216, 230)); // Light Blue
                g.fillRect(0, 0, width, height / 2);

                // Mitad inferior blanco
                g.setColor(Color.WHITE);
                g.fillRect(0, height / 2, width, height / 2);
            }
        };
        panelPrincipal.setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);

        // Título arriba centrado
        JLabel lblTitulo = new JLabel("CANAL DE NOTIFICACIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 100, 200));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel para mostrar recordatorios con scroll
        panelRecordatorios = new JPanel();
        panelRecordatorios.setLayout(new BoxLayout(panelRecordatorios, BoxLayout.Y_AXIS));
        panelRecordatorios.setOpaque(false); // transparente para mostrar fondo

        JScrollPane scroll = new JScrollPane(panelRecordatorios);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        cargarRecordatorios();
    }

    private void cargarRecordatorios() {
        panelRecordatorios.removeAll();

        List<RecepcionRecordatorio> lista = controller.listarRecepciones();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        if (lista.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay recordatorios recibidos.");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 16));
            lblVacio.setForeground(Color.DARK_GRAY);
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelRecordatorios.add(Box.createVerticalStrut(20));
            panelRecordatorios.add(lblVacio);
        } else {
            for (RecepcionRecordatorio r : lista) {
                JPanel panelRecord = new JPanel();
                panelRecord.setLayout(new BorderLayout(10, 10));
                panelRecord.setMaximumSize(new Dimension(320, 80));
                panelRecord.setBackground(new Color(255, 255, 150)); // amarillo claro
                panelRecord.setBorder(new LineBorder(new Color(255, 204, 0), 2, true));
                panelRecord.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Texto del recordatorio
                String texto = "<html><b>Paciente ID:</b> " + r.getIdPaciente() + "<br>" +
                        "<b>Hora programada:</b> " + (r.getFechaEnvio() != null ? sdf.format(r.getFechaEnvio()) : "N/A") + "<br>" +
                        "<b>Recibido:</b> " + (r.isRecibido() ? "Sí" : "No") + "</html>";

                JLabel lblTexto = new JLabel(texto);
                lblTexto.setFont(new Font("Arial", Font.PLAIN, 14));
                lblTexto.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                panelRecord.add(lblTexto, BorderLayout.CENTER);

                panelRecordatorios.add(panelRecord);
                panelRecordatorios.add(Box.createVerticalStrut(10)); // espacio entre recordatorios
            }
        }

        panelRecordatorios.revalidate();
        panelRecordatorios.repaint();
    }
}

