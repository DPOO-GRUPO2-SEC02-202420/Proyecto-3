package Lógica;
import java.util.ArrayList;

import java.util.List;

import Interfaz.Feedback;

//PROFESOR
public class Profesor extends Usuario implements Feedback {
	
	private List<LearningPath> learningPaths; 
	
	private String comentario;

    // Constructor
    public Profesor(String nombre, String correo, String contraseña) {
    	
        super(nombre, correo, contraseña);
        
        this.learningPaths = new ArrayList<>();
    }

    // Método para crear un Learning Path
    public void crearLearningPath(LearningPath learningPath) {
    	
        learningPaths.add(learningPath);
        
        System.out.println("Learning Path '" + learningPath.getTitulo() + "' creado por el profesor " + this.getUser());
    }

    // Método para listar los Learning Paths creados por el profesor
    public List<LearningPath> getLearningPaths() {
    	
        return learningPaths;
    }

    public void revisarQuizEstudiante(Estudiante estudiante, LearningPath learningPath, String tituloQuiz) {
        Quiz quiz = learningPath.obtenerQuiz(tituloQuiz);
        
        if (quiz != null) {
            int puntaje = quiz.obtenerPuntaje();

            System.out.println("El estudiante " + estudiante.getUser() + " obtuvo un puntaje de " + puntaje + " en el quiz: " + tituloQuiz);
            
        } else {

            System.out.println("No se encontró el quiz con el título: " + tituloQuiz);
        }
    }

    @Override
    public void proporcionarFeedback(String comentario) {
    	
        System.out.println("Profesor " + this.getUser() + " ha proporcionado el siguiente feedback: " + comentario);
    }
    @Override
    public double getRating() {
        
        return 0.0; 
    }
    @Override
    public String getComentario() {
    	
        return this.comentario; 
    }
}
