package Interfaces_Graficas;
import javax.swing.*;

import Consola.Consola;
import Lógica.Usuario;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MenuPrincipal extends JFrame {

private List<Usuario> usuarios;



    public MenuPrincipal() {

        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cambiar el ícono de la ventana
        setIconImage(new ImageIcon("logo.jpg").getImage());

        // Panel superior para el título
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 botones, 10 px de espacio

        JButton btnCrearUsuario = new JButton("Crear Usuario");
        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        JButton btnCargarUsuario = new JButton("Cargar Usuario");
        JButton btnSalir = new JButton("Salir");

        // Acción para el botón "Crear Usuario"
        btnCrearUsuario.addActionListener(e -> {
            // Abrir la interfaz "Crear Usuario"
            CrearUsuario ventanaCrearUsuario = new CrearUsuario();
            ventanaCrearUsuario.setVisible(true);
        });

        btnCargarUsuario.addActionListener(e -> {
    try {
        // Llamar al método para cargar usuarios
        int cantidadUsuarios = Consola.cargarUsuarios();

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Usuarios cargados exitosamente: " + cantidadUsuarios, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        // Mostrar mensaje de error si ocurre algo
        JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});
    btnIniciarSesion.addActionListener(e -> {
    // Supongamos que tienes una lista de usuarios cargados
    List<Usuario> usuarios = Consola.getUsers(); // Método que retorna la lista de usuarios
    IniciarSesion ventanaIniciarSesion = new IniciarSesion(usuarios);
    ventanaIniciarSesion.setVisible(true);
});


        // Cambiar colores de los botones
        btnCrearUsuario.setBackground(Color.BLUE);
        btnCrearUsuario.setForeground(Color.WHITE);

        btnIniciarSesion.setBackground(Color.GREEN);
        btnIniciarSesion.setForeground(Color.BLACK);

        btnCargarUsuario.setBackground(Color.ORANGE);
        btnCargarUsuario.setForeground(Color.BLACK);

        btnSalir.setBackground(Color.RED);
        btnSalir.setForeground(Color.WHITE);

        // Agregar bordes personalizados a los botones
        btnCrearUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        btnIniciarSesion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        btnCargarUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        btnSalir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Acción para el botón "Salir"
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones al panel
        buttonPanel.add(btnCrearUsuario);
        buttonPanel.add(btnIniciarSesion);
        buttonPanel.add(btnCargarUsuario);
        buttonPanel.add(btnSalir);

        // Agregar el panel de botones al centro
        add(buttonPanel, BorderLayout.CENTER);

        // Barra de navegación simulada
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navBar.add(new JLabel("Menú principal"));
        add(navBar, BorderLayout.NORTH);
    }

    

    public static void main(String[] args) {
        // Crear e iniciar la aplicación
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });

}                                           
}
