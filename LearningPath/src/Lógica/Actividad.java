package Lógica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Interfaz.Feedback;

//ACTIVIDAD
public abstract class Actividad implements Serializable {
	
	protected int id;
	
	protected String titulo;
	
	protected String descripcion;
	
	protected String objetivo;
	
	protected String dificultad; 
	
	protected int duracion;
	
	protected String resultado;
	
	protected Actividad prerequisito;
	
	protected int orden;
	
	protected double calificacion;
	
	protected List<Feedback> feedbacks = new ArrayList<>();
	
	
	public Actividad(int id, String titulo, String descripcion, String objetivo, String dificultad, int duracion,
			String resultado, Actividad prerequisito, int orden) 
	{
		super();
		
		this.id = id;
		
		this.titulo = titulo;
		
		this.descripcion = descripcion;
		
		this.objetivo = objetivo;
		
		this.dificultad = dificultad;
		
		this.duracion = duracion;
		
		this.resultado = resultado != null ? resultado: "Pendiente";
		
		this.prerequisito = prerequisito;
		
		this.orden = orden;
		
		this.calificacion = 0.0;
		
		this.feedbacks = new ArrayList();
		
		//GETTERS Y SETTERS
	}
	public List<Feedback> getFeedbacks() {
		
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		
		this.feedbacks = feedbacks;
	}
	//Esto es para las superclases
	public abstract void completar();
	
	public boolean estaCompleta(Estudiante estudiante) {
		
		return "Completada".equals(this.resultado);
		
	
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Actividad getPrerequisito() {
		return prerequisito;
	}
	public void setPrerequisito(Actividad prerequisito) {
		this.prerequisito = prerequisito;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	//Para el csv
	
	public String[] toCSV() {
        String prerequisitoId = (prerequisito != null) ? String.valueOf(prerequisito.id) : ""; 
        return new String[] {
            String.valueOf(this.id),
            this.titulo,
            this.descripcion,
            this.objetivo,
            this.dificultad,
            String.valueOf(this.duracion),
            this.resultado,
            prerequisitoId,  // Si no hay prerequisito, se verá como vación en el csv
            String.valueOf(this.orden),
            String.valueOf(this.calificacion)
        };
    }
	
	
	// Meterlo al archivo
    public static Actividad fromCSV(String lineaCSV) {
        String[] datos = lineaCSV.split(",");

        int id = Integer.parseInt(datos[0]);
        
        String titulo = datos[1];
        
        String descripcion = datos[2];
        
        String objetivo = datos[3];
        
        String dificultad = datos[4];
        
        int duracion = Integer.parseInt(datos[5]);
        
        String resultado = datos[6];

        
        int prerequisitoId = (datos[7].isEmpty()) ? 0 : Integer.parseInt(datos[7]);

        int orden = Integer.parseInt(datos[8]);
        
        double calificacion = Double.parseDouble(datos[9]);

        
        String tipoActividad = datos[10]; 
        
        Actividad actividad = null;

        // Dependiendo del tipo, instanciamos la subclase correspondiente que son ya los tipo de actividad
        switch (tipoActividad) {
            case "Tarea":
                String estado = datos[11];  
                actividad = new Tarea(titulo, descripcion, duracion, objetivo, estado);
                break;
            case "Quiz":
                actividad = new Quiz(titulo, descripcion, duracion, objetivo, new ArrayList<>());
                break;
            case "Examen":
                actividad = new Examen(titulo, descripcion, duracion, objetivo, new ArrayList<>(), new ArrayList<>());
                break;
            case "Encuesta":
                // Crear el objeto Encuesta
                actividad = new Encuesta (titulo, descripcion, duracion, objetivo, new ArrayList<>());
                break;

            case "Recurso Educativo":
                String tipoRecurso = datos[11]; 
                actividad = new RecursoEducativo(titulo, descripcion, duracion, objetivo,  tipoRecurso);
                break;

             //Si ponen una respuesta que no es
            default:
                System.out.println("Tipo de actividad no reconocido: " + tipoActividad);
                break;
        }

        
        if (actividad != null) 
        {
            actividad.setCalificacion(calificacion);
        }

        return actividad;
    }
    
 // Método para agregar feedback (por si se necesita llamar de nuevo)
    public void agregarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback agregado a la actividad.");
    }
}

	
	

