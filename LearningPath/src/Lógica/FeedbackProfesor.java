package Lógica;

import Interfaz.Feedback;
import java.io.Serializable;

public class FeedbackProfesor implements Feedback, Serializable {
	
	private double rating;
	
    private String comentario;

    public FeedbackProfesor(double rating, String comentario) {
    	
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
        
        System.out.println("Feedback del Profesor: " + comentario);
    }
    @Override
    public String getComentario() {
    	
        return this.comentario;
    }
}
