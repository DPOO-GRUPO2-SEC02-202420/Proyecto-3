package Lógica;
import java.io.Serializable;
import Interfaz.Feedback;

public class FeedbackEstudiante implements Feedback, Serializable{
	
	private double rating;
	
	private String comentario;

    public FeedbackEstudiante(double rating, String comentario) {
    	
        this.rating = rating;
        
        this.comentario = comentario;
    }

    @Override
    public double getRating() {
    	
        return this.rating;
    }
    
    @Override
    public void proporcionarFeedback(String comentario) {
    	
        this.comentario = comentario;
        
        System.out.println("Feedback del Estudiante: " + comentario);
    }
    @Override
    public String getComentario() {
    	
        return this.comentario;
    }
}
