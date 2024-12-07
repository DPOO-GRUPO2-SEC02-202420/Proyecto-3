package Interfaces_Graficas;
import javax.swing.*;
import java.util.List;
import java.util.Map;

import Consola.Consola;
import Lógica.Actividad;
import Lógica.Estudiante;
import Lógica.LearningPath;
import Lógica.Usuario;
import Interfaz.Feedback;
import Lógica.FeedbackProfesor;
import Lógica.FeedbackEstudiante;

import java.awt.*;
public class MenuProfesor extends JFrame {

    public MenuProfesor() {

        // Configuración de la ventana
        setTitle("Menú Profesor");

        setSize(500, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        setLocationRelativeTo(null); 

        setLayout(new GridLayout(9, 1, 10, 10)); 

        JLabel lblTitulo = new JLabel("Menú Profesor", SwingConstants.CENTER);

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lblTitulo.setForeground(Color.BLACK);

        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(lblTitulo, BorderLayout.NORTH); 


        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(new GridLayout(10, 1, 10, 10)); 
        

        // Crear botones
        JButton btnCrearLP = new JButton("Crear Learning Path");

        JButton btnAdminLP = new JButton("Administrar Learning Path");

        JButton btnGuardarLP = new JButton("Guardar Learning Path");

        JButton btnCargarLP = new JButton("Cargar Learning Paths");

        JButton btnFeedback = new JButton("Dar Feedback a estudiante");

        JButton btnGrafica = new JButton("Gráfica de actividades realizadas");

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        configurarBoton(btnCrearLP);

        configurarBoton(btnAdminLP);

        configurarBoton(btnGuardarLP);

        configurarBoton(btnCargarLP);

        configurarBoton(btnFeedback);

        configurarBoton(btnGrafica);



        
        btnCrearLP.setBackground(Color.BLUE);

        btnCrearLP.setForeground(Color.WHITE);

        btnAdminLP.setBackground(Color.BLUE);

        btnAdminLP.setForeground(Color.WHITE);

        btnGuardarLP.setBackground(Color.BLUE);

        btnGuardarLP.setForeground(Color.WHITE);

        btnFeedback.setBackground(Color.BLUE);

        btnFeedback.setForeground(Color.WHITE);


        btnCargarLP.setBackground(Color.BLUE);

        btnCargarLP.setForeground(Color.WHITE);

        btnGrafica.setBackground(Color.BLUE);

        btnGrafica.setForeground(Color.WHITE);

        btnCerrarSesion.setBackground(Color.RED);

        btnCerrarSesion.setForeground(Color.WHITE);

        btnCerrarSesion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        panelBotones.add(btnCrearLP);

        panelBotones.add(btnAdminLP);

        panelBotones.add(btnGuardarLP);

        panelBotones.add(btnCargarLP);

        panelBotones.add(btnFeedback);

        panelBotones.add(btnGrafica);

        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.CENTER);
        
    

    // Agregar botones al layout
    add(btnCrearLP);

    add(btnAdminLP);

    add(btnGuardarLP);

    add(btnCargarLP);

    add(btnFeedback);

    add(btnGrafica);

    add(btnCerrarSesion);

    btnCrearLP.addActionListener(e -> {
        dispose(); 

        CrearLearningPath crearLP = new CrearLearningPath(); 

        crearLP.setVisible(true);
    });

    
    btnCerrarSesion.addActionListener(e -> {

        dispose(); 
        
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        menuPrincipal.setVisible(true);
    });

    btnGuardarLP.addActionListener(e -> {
    
    Consola.guardarLearningPaths();

    
    JOptionPane.showMessageDialog(this, "Learning Paths guardados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    });
    btnCargarLP.addActionListener(e -> {
    
        
        Consola.cargarLearningPaths();
    
        
        JOptionPane.showMessageDialog(this, "Learning Paths cargados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    });

    btnFeedback.addActionListener(e -> { 
        
        darFeedbackEstudiante();

    });

    btnGrafica.addActionListener(e -> {
    
    Map<String, Integer> actividadesPorDia = Consola.obtenerActividadesRealizadasPorDia();

    
    GraficaActividadesRealizadas graficaVentana = new GraficaActividadesRealizadas(actividadesPorDia);
    graficaVentana.setVisible(true);
});

    btnAdminLP.addActionListener(e -> {
    
    List<LearningPath> learningPaths = Consola.getLearningPaths();

    

    

    
    ListaLearningPaths listaLP = new ListaLearningPaths(learningPaths);

    listaLP.setVisible(true);


});

   
}
private void configurarBoton(JButton boton) {

    boton.setBackground(Color.WHITE); 

    boton.setForeground(Color.BLACK); 

    boton.setFocusPainted(false);

    boton.setFont(new Font("Arial", Font.PLAIN, 16)); 

    boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
    
}

private void darFeedbackEstudiante() {
    
    JFrame ventanaFeedback = new JFrame("Dar Feedback a Estudiante");

    ventanaFeedback.setSize(400, 300);

    ventanaFeedback.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    ventanaFeedback.setLocationRelativeTo(null);

    ventanaFeedback.setLayout(new BorderLayout());

    
    DefaultListModel<String> modeloEstudiantes = new DefaultListModel<>();

    for (Usuario usuario : Consola.getUsers()) { 

        if (usuario instanceof Estudiante) {

            modeloEstudiantes.addElement(usuario.getCorreo());
        }
    }

    JList<String> listaEstudiantes = new JList<>(modeloEstudiantes);

    ventanaFeedback.add(new JScrollPane(listaEstudiantes), BorderLayout.CENTER);

    
    JButton btnSeleccionar = new JButton("Seleccionar");

    btnSeleccionar.setBackground(Color.BLUE);

    btnSeleccionar.setForeground(Color.WHITE);

    btnSeleccionar.addActionListener(ev -> {

        String estudianteSeleccionado = listaEstudiantes.getSelectedValue();

        if (estudianteSeleccionado != null) {

            mostrarActividadesCompletadas(estudianteSeleccionado);

            ventanaFeedback.dispose();

        } else {

            JOptionPane.showMessageDialog(ventanaFeedback, "Selecciona un estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    ventanaFeedback.add(btnSeleccionar, BorderLayout.SOUTH);

    ventanaFeedback.setVisible(true);
}

private void mostrarActividadesCompletadas(String estudianteCorreo) {

    JFrame ventanaActividades = new JFrame("Actividades Completadas por " + estudianteCorreo);

    ventanaActividades.setSize(400, 400);

    ventanaActividades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    ventanaActividades.setLocationRelativeTo(null);

    ventanaActividades.setLayout(new BorderLayout());

    Estudiante estudiante = (Estudiante) Consola.getUsers().stream()
            .filter(u -> u instanceof Estudiante && u.getCorreo().equals(estudianteCorreo))
            .findFirst()
            .orElse(null);

    if (estudiante == null) {

        JOptionPane.showMessageDialog(null, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);

        return;
    }

    DefaultListModel<String> modeloActividades = new DefaultListModel<>();

    for (Actividad actividad : estudiante.getActividadesCompletadas()) {

        modeloActividades.addElement(actividad.getTitulo());
    }

    JList<String> listaActividades = new JList<>(modeloActividades);

    ventanaActividades.add(new JScrollPane(listaActividades), BorderLayout.CENTER);

    JPanel panelFeedback = new JPanel(new BorderLayout());

    JTextArea txtFeedback = new JTextArea(5, 20);

    txtFeedback.setBorder(BorderFactory.createTitledBorder("Escribe tu feedback aquí"));

    panelFeedback.add(new JScrollPane(txtFeedback), BorderLayout.CENTER);

    JButton btnGuardarFeedback = new JButton("Guardar Feedback");

    btnGuardarFeedback.setBackground(Color.GREEN);

    btnGuardarFeedback.setForeground(Color.BLACK);

    btnGuardarFeedback.addActionListener(ev -> {

        String actividadSeleccionada = listaActividades.getSelectedValue();

        if (actividadSeleccionada != null) {

            String feedback = txtFeedback.getText().trim();

            if (!feedback.isEmpty()) {

                Actividad actividad = estudiante.getActividadesCompletadas().stream()

                        .filter(a -> a.getTitulo().equals(actividadSeleccionada))
                        .findFirst()
                        .orElse(null);
                if (actividad != null) {

                    actividad.agregarFeedback(new FeedbackProfesor(5.0,feedback));

                    JOptionPane.showMessageDialog(ventanaActividades, "Feedback guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ventanaActividades, "Por favor, escribe un feedback.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(ventanaActividades, "Selecciona una actividad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    panelFeedback.add(btnGuardarFeedback, BorderLayout.SOUTH);

    ventanaActividades.add(panelFeedback, BorderLayout.SOUTH);
    
    ventanaActividades.setVisible(true);
}

// Método principal para pruebas
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MenuProfesor menuProfesor = new MenuProfesor();
        menuProfesor.setVisible(true);
    });
}
}
