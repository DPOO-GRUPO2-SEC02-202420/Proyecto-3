package Lógica;
import java.util.ArrayList;
import Persistencia.PersistenciaSerializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaz.Feedback;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class LearningPath implements Serializable {
//Atributos segun el UML
    private Map<Actividad, String> fechasCompletadas = new HashMap<>();

	private String titulo;
	
	private String descripcion;
	
	private int duracion; // minutos en base a la actividad 
	
	private double rating; //calificacion de los usuarios 
	
	private String tipo;
	
	private Date fechaCreacion;
	
	private Date fechaModificacion;
	
	private List<Actividad> actividades; //lista de actividades Learning Paths
	
	private List<Feedback> feedbacks; // Comentarios y calificaciones de los usuarios 
	
	private String objetivo;

	public String getObjetivo() {
		return objetivo;
	}






	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}






	// CONSTRUCTOR
	public LearningPath(String titulo, String descripcion, String tipo, String objetivo) {
		super();
		this.titulo = titulo;
		
		this.descripcion = descripcion;
		
		this.tipo = tipo;
		
		this.objetivo = objetivo;
		
		this.duracion = 0;
		
		this.rating = 0.0;
		
		this.fechaCreacion = new Date();
		
		this.fechaModificacion = new Date();
		
		this.actividades = new ArrayList<>();
		
		this.feedbacks = new ArrayList<>();
	}





 
	// Métodos 
    public void agregarActividad(Actividad actividad) {
    	
    	 if (actividad != null) {
    		 
    	        this.actividades.add(actividad);
    	        
    	        actualizarDuracion();  
    	        
    	        actualizarFechaModificacion();  
    	    } else {
    	        System.out.println("Error: actividad es null y no se puede agregar");
    	    }
    }

    public void eliminarActividad(Actividad actividad) {
    	
        this.actividades.remove(actividad);
        
        actualizarDuracion();
        
        actualizarFechaModificacion();
    }

    // Métodos que calculan la duración de Learning Path
    public void actualizarDuracion() {
    	
    	int totalDuracion = 0;
    	
        if (this.actividades != null && !actividades.isEmpty()) {
        	
            for (Actividad actividad : actividades) {
            	
                totalDuracion += actividad.getDuracion();
            }
            System.out.println("Duración actualizada: " + totalDuracion);
        } else {
            System.out.println("Error: la lista de actividades es null o está vacía");
        }
        this.duracion = totalDuracion;
    	}

    // Actualiza la fecha de modificación
    public void actualizarFechaModificacion() {
    	
        this.fechaModificacion = new Date(); // actualizar la fecha actual (antes era 1780?)
    }

    public void recalcularRating() {
    	
        calcularRating(); 
    }

    public void agregarFeedback(Feedback feedback) {
    	
        feedbacks.add(feedback);
        
        recalcularRating();
        
        actualizarFechaModificacion();
    }

    private void calcularRating() {
    	
        double sumaRatings = 0;
        
        for (Feedback fb : feedbacks) {
        	
            sumaRatings += fb.getRating(); 
        }
        this.rating = feedbacks.isEmpty() ? 0 : sumaRatings / feedbacks.size(); 
    }

    public double calcularProgreso(Estudiante estudiante) {
    	
        if (actividades.isEmpty()) {
        	
            return 0.0; 
        }

        int actividadesCompletadas = 0;
        
        for (Actividad actividad : actividades) {
        	
            if (estudiante.getActividadesCompletadas().contains(actividad)) {
            	
                actividadesCompletadas++;
            }
        }
        return (double) actividadesCompletadas / actividades.size() * 100;
    }
    
    public Actividad buscarActividad(String titulo) {
    	
        for (Actividad actividad : this.actividades) {
        	
            if (actividad.getTitulo().equalsIgnoreCase(titulo)) {
            	
                return actividad;
            }
        }
        return null; 
    }

    public Quiz obtenerQuiz(String titulo) {
        
        for (Actividad actividad : actividades) {

            if (actividad instanceof Quiz && actividad.getTitulo().equalsIgnoreCase(titulo)) {

                return (Quiz) actividad;
            }
        }
        return null; // Si no encuentra el quiz
    }


    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        actualizarFechaModificacion();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        actualizarFechaModificacion();
    }

    public int getDuracion() {
        return duracion;
    }

    public double getRating() {
        return rating;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
        actualizarDuracion();
        actualizarFechaModificacion();
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
        recalcularRating();
        actualizarFechaModificacion();
    }






	public String getTipo() {
		return tipo;
	}






	public void setTipo(String tipo) {
		this.tipo = tipo;
	}






	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}






	public void setRating(double rating) {
		this.rating = rating;
	}






	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}






	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
 
	public String toCSV() {
	    return titulo + "," + descripcion + "," + duracion + "," + tipo + "," + rating + "," + fechaCreacion.getTime() + "," + fechaModificacion.getTime();
	}
	
	public static LearningPath fromCSV(String lineaCSV) {
		
	    String[] datos = lineaCSV.split(",");
	    
	    String titulo = datos[0];
	    
	    String descripcion = datos[1];
	    
	    int duracion = Integer.parseInt(datos[2]);
	    
	    String tipo = datos[3];
	    
	    double rating = Double.parseDouble(datos[4]);
	    
	    Date fechaCreacion = new Date(Long.parseLong(datos[5]));
	    
	    Date fechaModificacion = new Date(Long.parseLong(datos[6]));
	    
	    String objetivo = datos[7];
	    
	    LearningPath lp = new LearningPath(titulo, descripcion, tipo, objetivo);
	    
	    lp.setDuracion(duracion);
	    
	    lp.setRating(rating);
	    
	    lp.setFechaCreacion(fechaCreacion);
	    
	    lp.setFechaModificacion(fechaModificacion);
	    
	    return lp;
	}
    public void registrarFechaCompletada(Actividad actividad) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fechaCompletada = sdf.format(new Date());
    actividad.setResultado(fechaCompletada); // Usamos el campo `resultado` para almacenar la fecha.
}
public Map<String, List<Actividad>> obtenerActividadesPorFecha() {
    Map<String, List<Actividad>> actividadesPorFecha = new HashMap<>();
    
    for (Actividad actividad : actividades) {
        String fecha = actividad.getResultado(); // Usamos `resultado` como la fecha de finalización.
        
        if (fecha != null && !fecha.isEmpty()) { // Solo considerar actividades que tienen fecha.
            actividadesPorFecha
                .computeIfAbsent(fecha, k -> new ArrayList<>())
                .add(actividad);
        }
    }
    
    return actividadesPorFecha;
}

// Método para registrar una actividad como completada en una fecha
public void registrarFechaCompletada(Actividad actividad, String fecha) {
    fechasCompletadas.put(actividad, fecha);
}

// Método para obtener la fecha en la que se completó una actividad
public String obtenerFechaCompletada(Actividad actividad) {
    return fechasCompletadas.getOrDefault(actividad, "No completada");
}
	
}