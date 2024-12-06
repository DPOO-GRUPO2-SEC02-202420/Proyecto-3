package Interfaces_Graficas;
import javax.swing.*;

import Lógica.Actividad;
import Lógica.LearningPath;
import Consola.Consola;
import java.util.List;
import java.awt.*;
public class MenuEstudiantes extends JFrame {
    public MenuEstudiantes() {
        // Configuración de la ventana principal
        setTitle("Menú de Estudiantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Menú de estudiantes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Botón "Ver Learning Paths"
        JButton btnVerLearningPaths = new JButton("Ver Learning Paths");
        btnVerLearningPaths.setBackground(Color.GREEN);
        btnVerLearningPaths.setForeground(Color.BLACK);
        btnVerLearningPaths.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerLearningPaths.addActionListener(e -> {
            JFrame ventanaLearningPaths = new JFrame("Lista Learning Paths");
    ventanaLearningPaths.setSize(400, 300);
    ventanaLearningPaths.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaLearningPaths.setLocationRelativeTo(null);

    // Obtener los Learning Paths disponibles
    List<LearningPath> learningPaths = Consola.getLearningPaths(); // Método para obtener los Learning Paths

    // Crear un modelo para la lista
    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    for (LearningPath lp : learningPaths) {
        modeloLista.addElement(lp.getTitulo()); // Obtener el nombre del Learning Path
    }

    JList<String> listaLearningPaths = new JList<>(modeloLista);
    JScrollPane scrollPane = new JScrollPane(listaLearningPaths);

    // Botón para cerrar la ventana
    JButton btnVolver = new JButton("Volver");
    btnVolver.setBackground(Color.RED); // Fondo rojo
    btnVolver.setForeground(Color.WHITE); // Texto blanco
    btnVolver.setFocusPainted(false); // Elimina el enfoque visual (opcional)
    btnVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro (opcional)
    btnVolver.addActionListener(ev -> ventanaLearningPaths.dispose());

    // Panel principal
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(btnVolver, BorderLayout.SOUTH);

    ventanaLearningPaths.add(panel);
    ventanaLearningPaths.setVisible(true);
        });

        // Botón "Realizar Learning Path"
        JButton btnRealizarLearningPath = new JButton("Realizar Learning Path");
        btnRealizarLearningPath.setBackground(Color.GREEN);
        btnRealizarLearningPath.setForeground(Color.BLACK);
        btnRealizarLearningPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRealizarLearningPath.addActionListener(e -> {
    // Ventana para mostrar los Learning Paths
    JFrame ventanaLearningPaths = new JFrame("Learning Paths");
    ventanaLearningPaths.setSize(400, 300);
    ventanaLearningPaths.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaLearningPaths.setLocationRelativeTo(null);
    ventanaLearningPaths.setLayout(new BorderLayout());

    // Lista de Learning Paths
    DefaultListModel<String> modeloLP = new DefaultListModel<>();
    for (LearningPath lp : Consola.getLearningPaths()) {
        modeloLP.addElement(lp.getTitulo());
    }

    JList<String> listaLP = new JList<>(modeloLP);
    ventanaLearningPaths.add(new JScrollPane(listaLP), BorderLayout.CENTER);

    // Botón para seleccionar un Learning Path
    JButton btnSeleccionarLP = new JButton("Seleccionar Learning Path");
    btnSeleccionarLP.setBackground(Color.BLUE);
    btnSeleccionarLP.setForeground(Color.WHITE);

    btnSeleccionarLP.addActionListener(ev -> {
        int indexLP = listaLP.getSelectedIndex();
        if (indexLP >= 0) {
            LearningPath lpSeleccionado = Consola.getLearningPaths().get(indexLP);
            ventanaLearningPaths.dispose();

            // Mostrar las actividades del Learning Path seleccionado
            JFrame ventanaActividades = new JFrame("Actividades de " + lpSeleccionado.getTitulo());
            ventanaActividades.setSize(400, 300);
            ventanaActividades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaActividades.setLocationRelativeTo(null);
            ventanaActividades.setLayout(new BorderLayout());

            DefaultListModel<String> modeloActividades = new DefaultListModel<>();
            for (Actividad act : lpSeleccionado.getActividades()) {
                modeloActividades.addElement(act.getTitulo() + " - " + act.getResultado());
            }

            JList<String> listaActividades = new JList<>(modeloActividades);
            ventanaActividades.add(new JScrollPane(listaActividades), BorderLayout.CENTER);

            // Botón para simular realizar la actividad
            JButton btnCompletarActividad = new JButton("Completar Actividad");
            btnCompletarActividad.setBackground(Color.RED);
            btnCompletarActividad.setForeground(Color.WHITE);

            btnCompletarActividad.addActionListener(ea -> {
                int indexAct = listaActividades.getSelectedIndex();
                if (indexAct >= 0) {
                    Actividad actSeleccionada = lpSeleccionado.getActividades().get(indexAct);
                    actSeleccionada.completar(); // Método para marcar como completada
                    modeloActividades.set(indexAct, actSeleccionada.getTitulo() + " - Completada");
                    JOptionPane.showMessageDialog(ventanaActividades, "Actividad completada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventanaActividades, "Selecciona una actividad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            ventanaActividades.add(btnCompletarActividad, BorderLayout.SOUTH);
            ventanaActividades.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(ventanaLearningPaths, "Selecciona un Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    ventanaLearningPaths.add(btnSeleccionarLP, BorderLayout.SOUTH);
    ventanaLearningPaths.setVisible(true);
});

// Agregar el botón al panel del menú de estudiante
buttonPanel.add(btnRealizarLearningPath);

        // Botón "Ver Feedback"
        JButton btnVerFeedback = new JButton("Ver Feedback");
        btnVerFeedback.setBackground(Color.GREEN);
        btnVerFeedback.setForeground(Color.BLACK);
        btnVerFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerFeedback.addActionListener(e -> {
            // Lógica para ver Feedback
            JOptionPane.showMessageDialog(this, "Abrir ventana para ver Feedback.");
        });

        // Botón "Dejar rating en un Learning Path"
        JButton btnDejarRating = new JButton("Dejar rating en un Learning Path");
        btnDejarRating.setBackground(Color.GREEN);
        btnDejarRating.setForeground(Color.BLACK);
        btnDejarRating.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDejarRating.addActionListener(e -> {
            dejarRatingLearningPath();
        });

        // Botón "Cerrar Sesión"
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(Color.RED);
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.addActionListener(e -> {
            // Lógica para cerrar sesión
            this.dispose();
            JOptionPane.showMessageDialog(this, "Sesión cerrada.");
        });

        // Agregar botones al panel
        buttonPanel.add(btnVerLearningPaths);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnRealizarLearningPath);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnVerFeedback);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnDejarRating);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnCerrarSesion);

        // Agregar el panel al centro
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void dejarRatingLearningPath() {
        // Crear ventana para calificar
        JFrame ventanaCalificar = new JFrame("Dejar Rating");
        ventanaCalificar.setSize(400, 300);
        ventanaCalificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaCalificar.setLayout(new BorderLayout());
        ventanaCalificar.setLocationRelativeTo(null);
    
        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        ventanaCalificar.add(panel, BorderLayout.CENTER);
    
        // Selección del Learning Path
        JPanel panelSeleccion = new JPanel(new FlowLayout());
        JLabel lblSeleccion = new JLabel("Seleccione un Learning Path:");
        JComboBox<String> comboLearningPaths = new JComboBox<>();
        for (LearningPath lp : Consola.getLearningPaths()) {
            comboLearningPaths.addItem(lp.getTitulo());
        }
        panelSeleccion.add(lblSeleccion);
        panelSeleccion.add(comboLearningPaths);
        panel.add(panelSeleccion);
    
        // Slider para calificación
        JPanel panelSlider = new JPanel(new FlowLayout());
        JLabel lblCalificacion = new JLabel("Califique del 1 al 5:");
        JSlider sliderRating = new JSlider(1, 5, 3); // Min: 1, Max: 5, Default: 3
        sliderRating.setMajorTickSpacing(1);
        sliderRating.setPaintTicks(true);
        sliderRating.setPaintLabels(true);
        panelSlider.add(lblCalificacion);
        panelSlider.add(sliderRating);
        panel.add(panelSlider);
    
        // Botón de Confirmar
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(Color.GREEN);
        btnConfirmar.setForeground(Color.BLACK);
        btnConfirmar.addActionListener(e -> {
            String learningPathSeleccionado = (String) comboLearningPaths.getSelectedItem();
            int calificacion = sliderRating.getValue();
    
            if (learningPathSeleccionado != null) {
                // Encontrar el Learning Path y asignar el rating
                for (LearningPath lp : Consola.getLearningPaths()) {
                    if (lp.getTitulo().equals(learningPathSeleccionado)) {
                        lp.setRating(calificacion); // Asumiendo que tienes un método setRating
                        JOptionPane.showMessageDialog(ventanaCalificar, 
                            "Has calificado el Learning Path '" + learningPathSeleccionado + "' con: " + calificacion, 
                            "Calificación Exitosa", 
                            JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                ventanaCalificar.dispose();
            } else {
                JOptionPane.showMessageDialog(ventanaCalificar, "Por favor, seleccione un Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        ventanaCalificar.add(btnConfirmar, BorderLayout.SOUTH);
    
        // Mostrar ventana
        ventanaCalificar.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuEstudiantes menuEstudiantes = new MenuEstudiantes();
            menuEstudiantes.setVisible(true);
        });
    }
}
