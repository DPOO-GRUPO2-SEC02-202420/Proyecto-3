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

        
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); 

        JButton btnCrearUsuario = new JButton("Crear Usuario");

        JButton btnIniciarSesion = new JButton("Iniciar sesión");

        JButton btnCargarUsuario = new JButton("Cargar Usuario");

        JButton btnSalir = new JButton("Salir");

        
        btnCrearUsuario.addActionListener(e -> {

            
            CrearUsuario ventanaCrearUsuario = new CrearUsuario();

            ventanaCrearUsuario.setVisible(true);
        });

        btnCargarUsuario.addActionListener(e -> {
    try {
        
        int cantidadUsuarios = Consola.cargarUsuarios();

        
        JOptionPane.showMessageDialog(this, "Usuarios cargados exitosamente: " + cantidadUsuarios, "Éxito", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        
        JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});
    btnIniciarSesion.addActionListener(e -> {
    
    List<Usuario> usuarios = Consola.getUsers();
     
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

        
        btnSalir.addActionListener(e -> System.exit(0));

        
        buttonPanel.add(btnCrearUsuario);

        buttonPanel.add(btnIniciarSesion);

        buttonPanel.add(btnCargarUsuario);

        buttonPanel.add(btnSalir);

        
        add(buttonPanel, BorderLayout.CENTER);

        
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER));

        navBar.add(new JLabel("Menú principal"));

        add(navBar, BorderLayout.NORTH);
    }

    //ACá empieza la magia

    public static void main(String[] args) {
        // Crear e iniciar la aplicación
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });

}                                           
}
