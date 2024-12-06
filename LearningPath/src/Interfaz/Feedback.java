package Interfaz;

public abstract interface Feedback {
	double getRating();// Método que todas las clases que implementan Feedback deben tener
	
	String getComentario();
	
	
    void proporcionarFeedback(String comentario); //Este proporciona los feedbacks
    
}
