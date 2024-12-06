package Lógica;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

import Interfaz.Feedback;
import Lógica.Usuario;

public class Quiz extends Actividad implements Serializable {
	
    private List<Pregunta> preguntas; 
    
    private int puntaje; 
    
    private double calificacionMinima;
    

    public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		
		this.puntaje = puntaje;
	}

	public double getCalificacionMinima() {
		
		return calificacionMinima;
	}

	public void setCalificacionMinima(double calificacionMinima) {
		
		this.calificacionMinima = calificacionMinima;
	}

	public Quiz(String titulo, String descripcion, int duracion, String objetivo, List<Pregunta> preguntas) {

        super(0, titulo, descripcion, "", "", duracion, "Pendiente", null, 0);

		this.preguntas = preguntas;
	}

	// Método para agregar preguntas al quiz
    public void agregarPregunta(Pregunta pregunta) {
    	
        this.preguntas.add(pregunta);
    }

    public void agregarPreguntaVerdaderoFalso(Pregunta pregunta) {
        if (pregunta.isEsVerdaderoFalso()) {
            this.preguntas.add(pregunta);
        } else {
            System.out.println("Solo se permiten preguntas de verdadero/falso en este quiz.");
        }
    }

    
    public void verificarRespuestas(List<Boolean> respuestasEstudiante) {

        int puntajeObtenido = 0;
        
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            
            // Convertir la respuesta correcta a booleano
            boolean respuestaCorrecta = Boolean.parseBoolean(pregunta.getRespuestaCorrecta());

            boolean respuestaEstudiante = respuestasEstudiante.get(i);
    
            if (respuestaCorrecta == respuestaEstudiante) {
                puntajeObtenido += pregunta.getPuntaje();
            }
        }
        this.puntaje = puntajeObtenido;
        
        this.resultado = "Completado";
    }

    @Override
    public void completar() {
        int puntajeObtenido = 0;

        // Simulación de verificación de respuestas
        for (Pregunta pregunta : preguntas) {
            // Supongamos que el estudiante responde correctamente a todas las preguntas
            puntajeObtenido += pregunta.getPuntaje();
        }

        this.puntaje = puntajeObtenido;

        // Marcar el quiz como completado
        this.resultado = "Completado";
        System.out.println("Has completado el quiz con un puntaje de: " + puntajeObtenido);
    }
    
    // Método para mostrar el puntaje final del estudiante
    public int obtenerPuntaje() {
        return puntaje;
    }

    // Getters y Setters adicionales
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public static void proporcionarFeedback(Feedback feedback, String comentario) {
        feedback.proporcionarFeedback(comentario);  // Llama al método de la interfaz Feedback
        System.out.println("Feedback proporcionado: " + comentario);
    }

 // Sobrescribimos el método toCSV() para incluir la calificación y el número de preguntas
    @Override
    public String[] toCSV() {
        String[] baseCSV = super.toCSV();  

        String[] quizCSV = new String[baseCSV.length + 2];  

        System.arraycopy(baseCSV, 0, quizCSV, 0, baseCSV.length);  

        quizCSV[baseCSV.length] = String.valueOf(calificacion); 

        quizCSV[baseCSV.length + 1] = String.valueOf(preguntas.size());  
        return quizCSV;
    }
	
 // Método para crear una instancia de Quiz desde una línea CSV
    public static Quiz fromCSV(String[] datos) {

        String titulo = datos[1];

        String descripcion = datos[2];

        String objetivo = datos[3];

        int duracion = Integer.parseInt(datos[5]);

        double calificacion = Double.parseDouble(datos[10]);  

        int numPreguntas = Integer.parseInt(datos[11]);  


       
        List<Pregunta> preguntas = new ArrayList<>();

        // Creamos el Quiz
        Quiz quiz = new Quiz(titulo, descripcion, duracion, objetivo, preguntas);

        quiz.setCalificacion(calificacion);
        
        return quiz;
    }

    public void clearFeedbacks() {
        if (this.feedbacks != null) {
            this.feedbacks.clear();
        }
    }

    @Override
public String toString() {
    return getTitulo();  // Suponiendo que `getTitulo()` devuelve el título del Quiz
}

}


