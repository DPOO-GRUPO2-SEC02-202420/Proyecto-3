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

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Iniciar Sesión", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        
        JPanel formPanel = new JPanel();

        formPanel.setLayout(new GridLayout(2, 2, 10, 10)); 

        
        JLabel lblCorreo = new JLabel("Correo Uniandes:");

        JTextField txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");

        JPasswordField txtContrasena = new JPasswordField();

       
        formPanel.add(lblCorreo);

        formPanel.add(txtCorreo);

        formPanel.add(lblContrasena);

        formPanel.add(txtContrasena);

        add(formPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();

        JButton btnAceptar = new JButton("Aceptar");

        btnAceptar.setBackground(Color.BLUE);

        btnAceptar.setForeground(Color.WHITE);

        
        btnAceptar.addActionListener(e -> {

            String correo = txtCorreo.getText();

            String contrasena = new String(txtContrasena.getPassword());

            
            if (correo.isEmpty() || contrasena.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Validar el correo y la contraseña
            boolean encontrado = false;

            for (Usuario usuario : usuarios) {

                if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getPassword().equals(contrasena)) {

            encontrado = true;

            // AQUI Verificar si es estudiante o profesor
                if (usuario instanceof Profesor) {

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como: Profesor", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                dispose(); 
                MenuProfesor menuProfesor = new MenuProfesor();

                menuProfesor.setVisible(true);

            } else if (usuario instanceof Estudiante) {

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como: Estudiante", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                MenuEstudiantes menuEstudiantes = new MenuEstudiantes();

                menuEstudiantes.setVisible(true);

                this.dispose();
            }
            break; 
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
