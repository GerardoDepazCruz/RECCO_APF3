package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistrarGlucosa extends JFrame {
    private JTextField txtIdPaciente, txtFecha, txtNivelGlucosa, txtObservaciones;
    private JButton btnRegistrar;

    public VentanaRegistrarGlucosa() {
        setTitle("Registrar Nivel de Glucosa");
        // Cambiar icono usando ruta relativa
        setIconImage(new ImageIcon(getClass().getResource("/img/diabetes.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBackground(Color.WHITE);
        panelGeneral.setBorder(new EmptyBorder(15, 20, 15, 20));
        getContentPane().add(panelGeneral);

        // Título centrado
        JLabel lblTitulo = new JLabel("GLUCOSA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(54, 157, 245));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGeneral.add(lblTitulo);
        panelGeneral.add(Box.createVerticalStrut(15));

        // Formulario
        panelGeneral.add(crearCampo("ID Paciente:", txtIdPaciente = new JTextField()));
        panelGeneral.add(crearCampo("Fecha (YYYY-MM-DD HH:mm:ss):", txtFecha = new JTextField()));
        panelGeneral.add(crearCampo("Nivel de Glucosa:", txtNivelGlucosa = new JTextField()));
        panelGeneral.add(crearCampo("Observaciones:", txtObservaciones = new JTextField()));
        panelGeneral.add(Box.createVerticalStrut(10));

        // Botón registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(54, 157, 245));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panelGeneral.add(btnRegistrar);
        panelGeneral.add(Box.createVerticalStrut(20));

        // Imagen del gráfico - ahora con ruta relativa
        ImageIcon graficoIcon = new ImageIcon(getClass().getResource("/img/graficoo.png"));
        Image imagenEscalada = graficoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        JLabel lblGrafico = new JLabel(new ImageIcon(imagenEscalada));
        lblGrafico.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGeneral.add(lblGrafico);

        setVisible(true);
    }

    // Resto del código permanece igual...
    private JPanel crearCampo(String etiqueta, JTextField campoTexto) {
        JPanel panelCampo = new JPanel();
        panelCampo.setLayout(new BoxLayout(panelCampo, BoxLayout.Y_AXIS));
        panelCampo.setBackground(Color.WHITE);

        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        campoTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        panelCampo.add(lbl);
        panelCampo.add(campoTexto);
        panelCampo.add(Box.createVerticalStrut(10));
        return panelCampo;
    }

    public JTextField getTxtIdPaciente() {
        return txtIdPaciente;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtNivelGlucosa() {
        return txtNivelGlucosa;
    }

    public JTextField getTxtObservaciones() {
        return txtObservaciones;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}