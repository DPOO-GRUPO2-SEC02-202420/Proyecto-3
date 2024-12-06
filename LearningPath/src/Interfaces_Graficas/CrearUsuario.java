package Interfaces_Graficas;
import javax.swing.*;
import java.io.*;
import Lógica.Estudiante;
import Lógica.Profesor;
import Lógica.Usuario;
import Persistencia.PersistenciaSerializable;

import java.awt.*;

public class CrearUsuario extends JFrame {

    public CrearUsuario() {
        // Configuración de la ventana principal
        setTitle("Crear Usuario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cerrar esta ventana
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior para el título
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Crear Usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel central para el formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 filas, 2 columnas, espacio de 10 px

        // Crear campos y etiquetas
        JLabel lblNombre = new JLabel("Nombre de Usuario:");
        JTextField txtNombre = new JTextField();

        JLabel lblCorreo = new JLabel("Correo Uniandes:");
        JTextField txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        JLabel lblTipoUsuario = new JLabel("¿Es estudiante o profesor? (E/P):");
        JTextField txtTipoUsuario = new JTextField();

        // Agregar campos y etiquetas al formulario
        formPanel.add(lblNombre);
        formPanel.add(txtNombre);
        formPanel.add(lblCorreo);
        formPanel.add(txtCorreo);
        formPanel.add(lblContrasena);
        formPanel.add(txtContrasena);
        formPanel.add(lblTipoUsuario);
        formPanel.add(txtTipoUsuario);

        add(formPanel, BorderLayout.CENTER);

        // Panel inferior con el botón de guardar
        JPanel bottomPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(Color.GREEN);
        btnGuardar.setForeground(Color.WHITE);

        // Acción para el botón "Guardar"
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());
            String tipoUsuario = txtTipoUsuario.getText();
        
            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || tipoUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (!tipoUsuario.equalsIgnoreCase("E") && !tipoUsuario.equalsIgnoreCase("P")) {
                JOptionPane.showMessageDialog(this, "El tipo de usuario debe ser 'E' (Estudiante) o 'P' (Profesor)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            try {
                // Crear instancia del usuario y guardarlo
                Usuario nuevoUsuario;
                if (tipoUsuario.equalsIgnoreCase("E")) {
                    nuevoUsuario = new Estudiante(nombre, correo, contrasena);
                } else {
                    nuevoUsuario = new Profesor(nombre, correo, contrasena);
                }
                nuevoUsuario.guardarEnCSV();
        
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
                // Cerrar esta ventana
                dispose();
        
                // Mostrar el menú principal
                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);
        
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        bottomPanel.add(btnGuardar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrearUsuario ventana = new CrearUsuario();
            ventana.setVisible(true);
        });
    }
}
