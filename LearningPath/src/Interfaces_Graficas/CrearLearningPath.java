package Interfaces_Graficas;
import javax.swing.*;

import Consola.Consola;
import Lógica.LearningPath;

import java.awt.*;
public class CrearLearningPath extends JFrame {
 // Configuración de la ventana
 public CrearLearningPath() {
 setTitle("Crear Learning Path");
 setSize(500, 400);
 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cerrar esta ventana
 setLocationRelativeTo(null); // Centrar la ventana
 setLayout(new BorderLayout());

 // Título grande y centrado
 JLabel lblTitulo = new JLabel("Crear Learning Path", SwingConstants.CENTER);
 lblTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente grande y negrita
 lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Espaciado
 add(lblTitulo, BorderLayout.NORTH);

 // Panel para los campos de entrada
 JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 filas, 2 columnas
 formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaciado interno

 // Campos de entrada
 JLabel lblTituloLP = new JLabel("Título:");
 JTextField txtTitulo = new JTextField();
 JLabel lblDescripcion = new JLabel("Descripción:");
 JTextField txtDescripcion = new JTextField();
 JLabel lblTipo = new JLabel("Tipo (Estándar, Intermedio, Avanzado):");
 JTextField txtTipo = new JTextField();
 JLabel lblObjetivo = new JLabel("Objetivo:");
 JTextField txtObjetivo = new JTextField();

 // Agregar campos al formulario
 formPanel.add(lblTituloLP);
 formPanel.add(txtTitulo);
 formPanel.add(lblDescripcion);
 formPanel.add(txtDescripcion);
 formPanel.add(lblTipo);
 formPanel.add(txtTipo);
 formPanel.add(lblObjetivo);
 formPanel.add(txtObjetivo);
 add(formPanel, BorderLayout.CENTER);

 // Botón "Crear"
 JButton btnCrear = new JButton("Crear");
 btnCrear.setBackground(Color.YELLOW);
 btnCrear.setForeground(Color.BLACK);
 btnCrear.setFont(new Font("Arial", Font.BOLD, 16));
 btnCrear.addActionListener(e -> {
     // Validar campos
     if (txtTitulo.getText().isEmpty() || txtDescripcion.getText().isEmpty()
             || txtTipo.getText().isEmpty() || txtObjetivo.getText().isEmpty()) {
         JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
         return;
     }
        // Crear el Learning Path con los datos ingresados
    String titulo = txtTitulo.getText();
    String descripcion = txtDescripcion.getText();
    String tipo = txtTipo.getText();
    String objetivo = txtObjetivo.getText();
    LearningPath nuevoPath = new LearningPath(titulo, descripcion, tipo, objetivo);

    // Agregar a la lista de Learning Paths
    Consola.getLearningPaths().add(nuevoPath);

    // Guardar el Learning Path en el archivo CSV
    Consola.guardarLearningPaths();



     // Crear el Learning Path (aquí puedes agregar lógica para guardarlo)
     JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

     // Cerrar esta ventana y volver al menú profesor
     dispose();
     MenuProfesor menuProfesor = new MenuProfesor();
     menuProfesor.setVisible(true);
 });

 // Panel para el botón "Crear"
 JPanel buttonPanel = new JPanel();
 buttonPanel.add(btnCrear);
 add(buttonPanel, BorderLayout.SOUTH);
}

// Método principal para pruebas
public static void main(String[] args) {
 SwingUtilities.invokeLater(() -> {
     CrearLearningPath crearLP = new CrearLearningPath();
     crearLP.setVisible(true);
 });
}
}

