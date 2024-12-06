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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new GridLayout(9, 1, 10, 10)); // 7 filas, 1 columna, con espacio de 10 px

        JLabel lblTitulo = new JLabel("Menú Profesor", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente grande y negrita
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Espaciado
        add(lblTitulo, BorderLayout.NORTH); // Agregar al layout superior


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(10, 1, 10, 10)); // 7 filas, espacio entre botones
        

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



        // Configurar colores de botones
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
        dispose(); // Cerrar el menú profesor
        CrearLearningPath crearLP = new CrearLearningPath(); // Abrir la ventana de creación
        crearLP.setVisible(true);
    });

    // Acción para el botón "Cerrar Sesión"
    btnCerrarSesion.addActionListener(e -> {
        dispose(); // Cerrar esta ventana
        // Opcional: Regresar al Menú Principal
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    });

    btnGuardarLP.addActionListener(e -> {
    // Llamar al método de guardar Learning Paths
    Consola.guardarLearningPaths();

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Learning Paths guardados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    });
    btnCargarLP.addActionListener(e -> {
        // Llamar al método de cargar Learning Paths
        Consola.cargarLearningPaths();
    
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Learning Paths cargados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    });

    btnFeedback.addActionListener(e -> { 
        
        darFeedbackEstudiante();

    });

    btnGrafica.addActionListener(e -> {
    // Obtener los datos agrupados por día (puedes ajustar según tu estructura)
    Map<String, Integer> actividadesPorDia = Consola.obtenerActividadesRealizadasPorDia();

    // Abrir la ventana de la gráfica
    GraficaActividadesRealizadas graficaVentana = new GraficaActividadesRealizadas(actividadesPorDia);
    graficaVentana.setVisible(true);
});

    btnAdminLP.addActionListener(e -> {
    // Cargar los Learning Paths desde la consola
    List<LearningPath> learningPaths = Consola.getLearningPaths();

    

    

    // Abrir la ventana Lista Learning Paths
    ListaLearningPaths listaLP = new ListaLearningPaths(learningPaths);
    listaLP.setVisible(true);


});

   
}
private void configurarBoton(JButton boton) {
    boton.setBackground(Color.WHITE); // Fondo blanco
    boton.setForeground(Color.BLACK); // Texto negro
    boton.setFocusPainted(false); // Eliminar borde de foco
    boton.setFont(new Font("Arial", Font.PLAIN, 16)); // Tamaño y estilo de fuente
    boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro
    
}

private void darFeedbackEstudiante() {
    // Ventana para seleccionar un estudiante
    JFrame ventanaFeedback = new JFrame("Dar Feedback a Estudiante");
    ventanaFeedback.setSize(400, 300);
    ventanaFeedback.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaFeedback.setLocationRelativeTo(null);
    ventanaFeedback.setLayout(new BorderLayout());

    // Lista de estudiantes
    DefaultListModel<String> modeloEstudiantes = new DefaultListModel<>();
    for (Usuario usuario : Consola.getUsers()) { // Método que obtenga los usuarios
        if (usuario instanceof Estudiante) {
            modeloEstudiantes.addElement(usuario.getCorreo());
        }
    }

    JList<String> listaEstudiantes = new JList<>(modeloEstudiantes);
    ventanaFeedback.add(new JScrollPane(listaEstudiantes), BorderLayout.CENTER);

    // Botón para confirmar selección de estudiante
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
