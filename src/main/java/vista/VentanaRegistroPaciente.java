package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaRegistroPaciente extends JFrame {

    private JTextField txtNombre;
    private JTextField txtDni;
    private JTextField txtEdad;
    private JComboBox<String> comboTipoDiabetes;
    private JTextField txtContactoEmergencia;
    private JButton btnRegistrar;

    public VentanaRegistroPaciente() {
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 640); // Tamaño tipo celular
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBackground(Color.WHITE);
        panelGeneral.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(panelGeneral);

        // Encabezado (Imagen + Título)
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBackground(Color.WHITE);
        panelEncabezado.setLayout(new BoxLayout(panelEncabezado, BoxLayout.Y_AXIS));
        panelEncabezado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblIcono = new JLabel(new ImageIcon(getClass().getResource("/img/paciente2.png")));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("REGISTRO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(58, 120, 177));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(new EmptyBorder(10, 0, 20, 0));

        panelEncabezado.add(lblIcono);
        panelEncabezado.add(lblTitulo);

        // Formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(0, 1, 5, 10));
        panelFormulario.setBackground(Color.WHITE);

        txtNombre = new JTextField();
        txtDni = new JTextField();
        txtEdad = new JTextField();
        comboTipoDiabetes = new JComboBox<>(new String[]{"Tipo 1", "Tipo 2", "Gestacional"});
        txtContactoEmergencia = new JTextField();

        panelFormulario.add(new JLabel("Nombre completo:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("DNI:"));
        panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);
        panelFormulario.add(new JLabel("Tipo de Diabetes:"));
        panelFormulario.add(comboTipoDiabetes);
        panelFormulario.add(new JLabel("Contacto de Emergencia:"));
        panelFormulario.add(txtContactoEmergencia);

        // Botón
        btnRegistrar = new JButton("Guardar");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.setBackground(new Color(80, 80, 80)); // Color plomo
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(100, 35));

        // Añadir todo al panel principal
        panelGeneral.add(panelEncabezado);
        panelGeneral.add(panelFormulario);
        panelGeneral.add(Box.createVerticalStrut(10));
        panelGeneral.add(btnRegistrar);
    }

    // Métodos para acceder desde el controlador
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getDni() {
        return txtDni.getText().trim();
    }

    public int getEdad() {
        try {
            return Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getTipoDiabetes() {
        return (String) comboTipoDiabetes.getSelectedItem();
    }

    public String getContactoEmergencia() {
        return txtContactoEmergencia.getText().trim();
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        comboTipoDiabetes.setSelectedIndex(0);
        txtContactoEmergencia.setText("");
    }
    
    
}
