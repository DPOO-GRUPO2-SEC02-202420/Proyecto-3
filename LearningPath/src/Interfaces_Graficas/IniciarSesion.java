package Interfaces_Graficas;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import Lógica.Estudiante;
import Lógica.Profesor;
import Lógica.Usuario;

import java.awt.*;
public class IniciarSesion extends JFrame {

    private List<Usuario> usuarios;

    public IniciarSesion(List<Usuario> usuarios) {
        super("Iniciar Sesión");
        this.usuarios = usuarios;
        // Configuración de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cerrar esta ventana
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior para el título
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel central para los campos
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas, espacio de 10 px

        // Crear campos y etiquetas
        JLabel lblCorreo = new JLabel("Correo Uniandes:");
        JTextField txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        // Agregar campos y etiquetas al formulario
        formPanel.add(lblCorreo);
        formPanel.add(txtCorreo);
        formPanel.add(lblContrasena);
        formPanel.add(txtContrasena);

        add(formPanel, BorderLayout.CENTER);

        // Panel inferior con el botón de aceptar
        JPanel bottomPanel = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.BLUE);
        btnAceptar.setForeground(Color.WHITE);

        // Acción para el botón "Aceptar"
        btnAceptar.addActionListener(e -> {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());

            // Aquí puedes validar el correo y la contraseña contra los usuarios cargados
            if (correo.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar el correo y la contraseña
            boolean encontrado = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getPassword().equals(contrasena)) {
            encontrado = true;
            // Verificar si es estudiante o profesor
                if (usuario instanceof Profesor) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como: Profesor", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar la ventana actual
                MenuProfesor menuProfesor = new MenuProfesor(); // Abrir el menú de profesor
                menuProfesor.setVisible(true);
            } else if (usuario instanceof Estudiante) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como: Estudiante", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                MenuEstudiantes menuEstudiantes = new MenuEstudiantes();
                menuEstudiantes.setVisible(true);
                this.dispose();
            }
            break; // Salir del bucle una vez encontrado
        }
    }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomPanel.add(btnAceptar);
        add(bottomPanel, BorderLayout.SOUTH);
}


}
