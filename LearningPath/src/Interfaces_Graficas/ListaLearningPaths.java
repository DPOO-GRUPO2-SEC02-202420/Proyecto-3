package Interfaces_Graficas;

import javax.swing.*;

import Consola.Consola;
import Lógica.LearningPath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListaLearningPaths extends JFrame{
    private List<LearningPath> learningPaths;

    public ListaLearningPaths(List<LearningPath> learningPaths) {

        this.learningPaths = learningPaths != null ? learningPaths : new ArrayList<>();

        configurarInterfaz();
    }

    public void configurarInterfaz() {

        setTitle("Lista Learning Paths");

        setSize(600, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Lista Learning Paths", SwingConstants.CENTER);

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(lblTitulo, BorderLayout.NORTH);

        JPanel listPanel = generarPanelLearningPaths();

        add(listPanel, BorderLayout.CENTER);

        
        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new GridLayout(3, 1, 10, 10));

        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 


        
        JLabel lblInput = new JLabel("Ingrese el Learning Path que desea administrar:");

        JTextField txtInput = new JTextField();

        JButton btnAceptar = new JButton("Aceptar");

        btnAceptar.setBackground(Color.BLUE); 

        btnAceptar.setForeground(Color.WHITE); 

        btnAceptar.setFont(new Font("Arial", Font.BOLD, 16)); 

        

        
        btnAceptar.addActionListener(e -> {

            String nombreLP = txtInput.getText().trim();
    
            if (nombreLP.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                
                LearningPath learningPathSeleccionado = null;

                for (LearningPath lp : Consola.getLearningPaths()) {

                    if (lp.getTitulo().equalsIgnoreCase(nombreLP)) { 

                        learningPathSeleccionado = lp;

                        break;
                    }
                }
        
                if (learningPathSeleccionado != null) {

                    JOptionPane.showMessageDialog(this, "Se encontró el Learning Path: " + learningPathSeleccionado.getTitulo());

                    dispose(); 
                    AdministrarLearningPath adminLP = new AdministrarLearningPath(learningPathSeleccionado);

                    adminLP.setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(this, "El Learning Path no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        inputPanel.add(lblInput);

        inputPanel.add(txtInput);

        inputPanel.add(btnAceptar);

        
        add(inputPanel, BorderLayout.SOUTH);
    }

    public JPanel generarPanelLearningPaths() {

        JPanel listPanel = new JPanel();

        listPanel.setLayout(new GridLayout(this.learningPaths.size(), 2, 10, 10));

        if (this.learningPaths.isEmpty()) {

            JLabel lblSinDatos = new JLabel("No hay Learning Paths disponibles.", SwingConstants.CENTER);

            listPanel.add(lblSinDatos);

            return listPanel;
        }

        for (LearningPath lp : this.learningPaths) {

            JLabel lblTitulo = new JLabel("Título: " + lp.getTitulo(), SwingConstants.LEFT);

            JLabel lblObjetivo = new JLabel("Objetivo: " + lp.getObjetivo(), SwingConstants.LEFT);

            listPanel.add(lblTitulo);

            listPanel.add(lblObjetivo);
        }

        return listPanel;
    }
    //para probar
    public static void main(String[] args) {
        Consola.cargarLearningPaths();
        SwingUtilities.invokeLater(() -> {
            ListaLearningPaths listaLP = new ListaLearningPaths(Consola.getLearningPaths());
            listaLP.setVisible(true);
        });
    }
}
