package Lógica;
import java.util.ArrayList;
import java.util.List;

import Interfaz.Feedback;

//ESTUDIANTE
public class Estudiante extends Usuario implements Feedback {
	
	private List<LearningPath> learningPaths; 
	
    private List<Actividad> actividadesCompletadas; 
    
    private String comentario;

    // Constructor
    public Estudiante(String nombre, String correo, String contraseña) {
    	
        super(nombre, correo, contraseña);
        
        this.learningPaths = new ArrayList<>();
        
        this.actividadesCompletadas = new ArrayList<>();
    }

    // Te inscribes a un Learnig Path
    public void inscribirseEnLearningPath(LearningPath learningPath) {
    	
        learningPaths.add(learningPath);
        
        System.out.println("Estudiante " + this.getUser() + " se ha inscrito en el Learning Path '" + learningPath.getTitulo() + "'");
    }

    // Sale que la actividad está completa
    public void completarActividad(Actividad actividad) {
    	
        if (!actividadesCompletadas.contains(actividad)) {
        	
            actividadesCompletadas.add(actividad);
            
            actividad.completar();
            
            System.out.println("Estudiante " + this.getUser() + " ha completado la actividad: " + actividad.getTitulo());
            
        } else {
        	
            System.out.println("La actividad ya ha sido completada.");
        }
    }

    
    public List<LearningPath> getLearningPaths() {
        return learningPaths;
    }

    public List<Actividad> getActividadesCompletadas() {
        return actividadesCompletadas;
    }

    public void realizarQuiz(LearningPath learningPath, String tituloQuiz, List<Boolean> respuestas) {
        Quiz quiz = learningPath.obtenerQuiz(tituloQuiz);
        
        if (quiz != null) {

            quiz.verificarRespuestas(respuestas); // Verifica las respuestas y asigna el puntaje

            completarActividad(quiz); // Marca el quiz como completado

            System.out.println("Has completado el quiz '" + tituloQuiz + "' con un puntaje de: " + quiz.obtenerPuntaje());
        } else {

            System.out.println("No se encontró el quiz con el título: " + tituloQuiz);
            
        }
    }


    @Override
    public void proporcionarFeedback(String comentario) {
        System.out.println("Estudiante " + this.getUser() + " ha proporcionado el siguiente feedback: " + comentario);
    }
    @Override
    public double getRating() {
    	
        if (this.actividadesCompletadas.isEmpty()) {
        	
            return 0.0; 
        }

        double sumaRatings = 0.0;
        
        for (Actividad actividad : this.actividadesCompletadas) {
        	
            sumaRatings += actividad.getCalificacion(); 
        }

        
        return sumaRatings / this.actividadesCompletadas.size();
    }
    
    @Override
    public String getComentario() {
        return this.comentario;
    }
//Esto para el estudiante
    public void verFeedbacks(Actividad actividad) {
    	
    

        for (Feedback feedback : actividad.getFeedbacks()) {

            System.out.println("Comentario: " + feedback.getComentario());

            System.out.println("Puntaje: " + feedback.getRating());
        }
    }
    
    
    @Override
    public String toString() {
        StringBuilder actividades = new StringBuilder();
        for (Actividad actividad : actividadesCompletadas) {
            actividades.append(actividad.getTitulo()).append(", ");
        }
        // Remover la última coma y espacio si hay actividades
        if (actividades.length() > 0) {
            actividades.setLength(actividades.length() - 2);
        }
        return "Estudiante{" +
               "nombre='" + getUser() + '\'' +  // Llama a getUser para obtener el nombre
               ", actividadesCompletadas=[" + actividades.toString() + "]" +
               '}';
    }
}
