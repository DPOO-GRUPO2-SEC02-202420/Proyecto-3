package Lógica;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
//ENCUESTA
public class Encuesta extends Actividad implements Serializable {
	
	private List<Pregunta> preguntasAbiertas;
	 

    // Constructor
    public Encuesta(String titulo, String descripcion, int duracion, String objetivo, List<Pregunta> preguntasAbiertas) {
        super(0, titulo, descripcion, objetivo, "", duracion, "Pendiente", null, 0);
        this.preguntasAbiertas = preguntasAbiertas;
    }

 
    public void agregarPregunta(Pregunta pregunta) {
        this.preguntasAbiertas.add(pregunta);
    }

    public List<Pregunta> getPreguntas() {
    	
        return preguntasAbiertas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
    	
        this.preguntasAbiertas = preguntas;
    }

    @Override
    public void completar() {
    	
        this.resultado = "Completada"; 
        
        System.out.println("La encuesta ha sido completada.");
    }
    @Override
    public String[] toCSV() {
    	
        String[] baseCSV = super.toCSV();  
        
        String[] encuestaCSV = new String[baseCSV.length + 1];
        
        System.arraycopy(baseCSV, 0, encuestaCSV, 0, baseCSV.length); 
        
        encuestaCSV[baseCSV.length] = String.valueOf(preguntasAbiertas.size()); 
        
        return encuestaCSV;
    }

    public static Encuesta fromCSV(String[] datos) {
    	
        String titulo = datos[1];
        
        String descripcion = datos[2];
        
        String objetivo = datos[3];
        
        int duracion = Integer.parseInt(datos[5]);
        
        int numPreguntas = Integer.parseInt(datos[10]);  
        
        List<Pregunta> preguntas = new ArrayList<>();

        
        return new Encuesta(titulo, descripcion, duracion, objetivo, preguntas);
    }

}
