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

 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

 setLocationRelativeTo(null); 

 setLayout(new BorderLayout());

 
 JLabel lblTitulo = new JLabel("Crear Learning Path", SwingConstants.CENTER);

 lblTitulo.setFont(new Font("Arial", Font.BOLD, 24)); 

 lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

 add(lblTitulo, BorderLayout.NORTH);

 
 JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10)); 

 formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


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
     

     if (txtTitulo.getText().isEmpty() || txtDescripcion.getText().isEmpty()

             || txtTipo.getText().isEmpty() || txtObjetivo.getText().isEmpty()) {

         JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);

         return;
     }
        
    String titulo = txtTitulo.getText();

    String descripcion = txtDescripcion.getText();

    String tipo = txtTipo.getText();

    String objetivo = txtObjetivo.getText();

    LearningPath nuevoPath = new LearningPath(titulo, descripcion, tipo, objetivo);

    
    Consola.getLearningPaths().add(nuevoPath);

    
    Consola.guardarLearningPaths();



     
     JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

     
     dispose();

     MenuProfesor menuProfesor = new MenuProfesor();

     menuProfesor.setVisible(true);
 });

 // botón "Crear"
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

