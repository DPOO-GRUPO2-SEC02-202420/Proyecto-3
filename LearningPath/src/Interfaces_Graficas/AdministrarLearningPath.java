package Interfaces_Graficas;
import Lógica.LearningPath;
import Lógica.Quiz;
import Lógica.RecursoEducativo;
import Lógica.Tarea;
import Persistencia.ArchivoCSV;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import Lógica.Actividad;
import Lógica.Encuesta;
import Lógica.Examen;
public class AdministrarLearningPath extends JFrame {
    private LearningPath learningPathSeleccionado;

    public AdministrarLearningPath(LearningPath learningPath) {
        this.learningPathSeleccionado = learningPath;

        // Configuración de la ventana
        setTitle("Administrar Learning Path");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10)); // Una fila por botón

        // Título
        JLabel lblTitulo = new JLabel("Administrar LearningPath", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo);

JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Disposición vertical
buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Espaciado interno

// Botón "Agregar Actividad"
JButton btnAgregarActividad = new JButton("Agregar Actividad");
btnAgregarActividad.setBackground(Color.GREEN);
btnAgregarActividad.setForeground(Color.BLACK);
btnAgregarActividad.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
btnAgregarActividad.addActionListener(e -> mostrarVentanaAgregarActividad());
buttonPanel.add(btnAgregarActividad);
buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones

// Botón "Eliminar Actividad"
JButton btnEliminarActividad = new JButton("Eliminar Actividad");
btnEliminarActividad.setBackground(Color.GREEN);
btnEliminarActividad.setForeground(Color.BLACK);
btnEliminarActividad.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
btnEliminarActividad.addActionListener(e -> mostrarVentanaEliminarActividad());
buttonPanel.add(btnEliminarActividad);
buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones

// Botón "Guardar Actividades"
JButton btnGuardarActividades = new JButton("Guardar Actividades");
btnGuardarActividades.setBackground(Color.BLUE);
btnGuardarActividades.setForeground(Color.WHITE);
btnGuardarActividades.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
btnGuardarActividades.addActionListener(e -> {
    try {
        // Ruta donde se guardará el archivo CSV
        String rutaArchivo = "actividades.csv";
        
        // Llama al método para guardar las actividades en el CSV
        ArchivoCSV.guardarActividadesCSV(learningPathSeleccionado.getActividades(), rutaArchivo);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Actividades guardadas exitosamente en: " + rutaArchivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
        // Mostrar mensaje de error si algo falla
        JOptionPane.showMessageDialog(this, "Error al guardar las actividades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});
buttonPanel.add(btnGuardarActividades);
buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones



// Botón "Volver al Menú"
JButton btnVolverMenu = new JButton("Volver al Menú");
btnVolverMenu.setBackground(Color.GREEN);
btnVolverMenu.setForeground(Color.BLACK);
btnVolverMenu.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
btnVolverMenu.addActionListener(e -> {
    dispose();
    MenuProfesor menuProfesor = new MenuProfesor();
    menuProfesor.setVisible(true);
    
});
buttonPanel.add(btnVolverMenu);

// Agregar el panel de botones al JFrame principal
add(buttonPanel, BorderLayout.CENTER);

// Configuración final del JFrame
setTitle("Administrar Learning Path");
setSize(600, 700);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setLocationRelativeTo(null); // Centrar la ventana
setVisible(true);

    }

    private void mostrarVentanaEliminarActividad() {
        // Crear la ventana para eliminar actividades
        JFrame ventanaEliminar = new JFrame("Eliminar Actividad");
        ventanaEliminar.setSize(400, 300);
        ventanaEliminar.setLayout(new BorderLayout());
        ventanaEliminar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEliminar.setLocationRelativeTo(null);
    
        // Modelo para la lista de actividades
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Actividad act : learningPathSeleccionado.getActividades()) {
            modelo.addElement(act.getTitulo()); // Asegúrate de que getTitulo() esté en Actividad
        }
    
        // Crear la lista con el modelo
        JList<String> lista = new JList<>(modelo);
        ventanaEliminar.add(new JScrollPane(lista), BorderLayout.CENTER);
    
        // Botón para confirmar eliminación
        JButton btnConfirmarEliminar = new JButton("Eliminar");
        btnConfirmarEliminar.setBackground(Color.RED);
        btnConfirmarEliminar.setForeground(Color.WHITE);
        btnConfirmarEliminar.addActionListener(ev -> {
            int index = lista.getSelectedIndex(); // Obtener el índice seleccionado
            if (index >= 0) {
                // Eliminar actividad del Learning Path
                learningPathSeleccionado.eliminarActividad(learningPathSeleccionado.getActividades().get(index));
                modelo.remove(index); // Actualizar el modelo de la lista
                JOptionPane.showMessageDialog(ventanaEliminar, "Actividad eliminada.");
            } else {
                JOptionPane.showMessageDialog(ventanaEliminar, "Selecciona una actividad para eliminar.");
            }
        });
    
        ventanaEliminar.add(btnConfirmarEliminar, BorderLayout.SOUTH);
    
        // Mostrar la ventana
        ventanaEliminar.setVisible(true);
    }

    // Método para mostrar la ventana de "Agregar Actividad"
private void mostrarVentanaAgregarActividad() {
    JFrame ventanaAgregar = new JFrame("Agregar Actividad");
    ventanaAgregar.setSize(400, 300);
    ventanaAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaAgregar.setLocationRelativeTo(null);
    ventanaAgregar.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

    // Campos y etiquetas
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    ventanaAgregar.add(new JLabel("Nombre:"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtNombre = new JTextField(15);
    ventanaAgregar.add(txtNombre, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    ventanaAgregar.add(new JLabel("Descripción:"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtDescripcion = new JTextField(15);
    ventanaAgregar.add(txtDescripcion, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.NONE;
    ventanaAgregar.add(new JLabel("Duración (horas):"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtDuracion = new JTextField(15);
    ventanaAgregar.add(txtDuracion, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.NONE;
    ventanaAgregar.add(new JLabel("Objetivo:"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtObjetivo = new JTextField(15);
    ventanaAgregar.add(txtObjetivo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.fill = GridBagConstraints.NONE;
    ventanaAgregar.add(new JLabel("Tipo de Actividad:"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtTipoActividad = new JTextField(15);
    ventanaAgregar.add(txtTipoActividad, gbc);

    // Botón Confirmar
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.CENTER;
    JButton btnConfirmar = new JButton("Confirmar");
    btnConfirmar.setBackground(Color.RED);
    btnConfirmar.setForeground(Color.WHITE); // Para texto legible
    btnConfirmar.addActionListener(ev -> {
        try {
            // Validar los campos
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String objetivo = txtObjetivo.getText().trim();
            String tipoActividad = txtTipoActividad.getText().trim();
            int duracion = Integer.parseInt(txtDuracion.getText().trim());

            Actividad nuevaActividad = null;
            switch (tipoActividad.toLowerCase()) {
                case "tarea":
                    nuevaActividad = new Tarea(nombre, descripcion, duracion, objetivo, "Pendiente");
                    break;
                case "quiz":
                    nuevaActividad = new Quiz(nombre, descripcion, duracion, objetivo, new ArrayList<>());
                    break;
                case "examen":
                    nuevaActividad = new Examen(nombre, descripcion, duracion, objetivo, new ArrayList<>(), new ArrayList<>());
                    break;
                case "encuesta":
                    nuevaActividad = new Encuesta(nombre, descripcion, duracion, objetivo, new ArrayList<>());
                    break;
                case "recurso educativo":
                    nuevaActividad = new RecursoEducativo(nombre, descripcion, duracion, objetivo, "Tipo de recurso por defecto");
                    break;
                default:
                    JOptionPane.showMessageDialog(ventanaAgregar, "Tipo de actividad no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            // Agregar la actividad al Learning Path seleccionado
            learningPathSeleccionado.agregarActividad(nuevaActividad);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(ventanaAgregar, "Actividad agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaAgregar.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventanaAgregar, "La duración debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventanaAgregar, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
    ventanaAgregar.add(btnConfirmar, gbc);

    // Mostrar la ventana
    ventanaAgregar.setVisible(true);
}

}
