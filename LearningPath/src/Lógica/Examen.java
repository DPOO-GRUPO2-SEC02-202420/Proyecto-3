package Lógica;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

//EXAMEN
public class Examen extends Actividad implements Serializable {
	
	
	private List<Pregunta> preguntasAbiertas;
	
    private List<Pregunta> preguntasCerradas; 

    // Constructor
    public Examen(String titulo, String descripcion, int duracion, String objetivo, List<Pregunta> preguntasAbiertas, List<Pregunta> preguntasCerradas) {
		super(0, titulo, descripcion, objetivo, "", duracion, "Pendiente", null, 0);
		
		this.preguntasAbiertas = preguntasAbiertas;
		
        this.preguntasCerradas = preguntasCerradas;
	}

    @Override
    public void completar() {
        this.resultado = "Completado";
        
        System.out.println("El examen ha sido completado.");
    }

    //preguntas abiertas
    public void agregarPreguntaAbierta(Pregunta pregunta) {
    	
        preguntasAbiertas.add(pregunta);  
    }

    //Preguntas cerradas
    public void agregarPreguntaCerrada(Pregunta pregunta) {
        preguntasCerradas.add(pregunta);
    }

    
    public void listarPreguntasAbiertas() {
    	
        System.out.println("Preguntas abiertas del examen:");
        
        for (Pregunta pregunta : preguntasAbiertas) {
        	
            System.out.println(pregunta);
        }
    }

	public List<Pregunta> getPreguntasAbiertas() {
		
		return preguntasAbiertas;
	}

	public void setPreguntasAbiertas(List<Pregunta> preguntasAbiertas) {
		
		this.preguntasAbiertas = preguntasAbiertas;
	}

	public List<Pregunta> getPreguntasCerradas() {
		
		return preguntasCerradas;
	}

	public void setPreguntasCerradas(List<Pregunta> preguntasCerradas) {
		
		this.preguntasCerradas = preguntasCerradas;
	}

	
    @Override
    public String[] toCSV() {
        String[] baseCSV = super.toCSV();  
        
        String[] examenCSV = new String[baseCSV.length + 2];
        
        System.arraycopy(baseCSV, 0, examenCSV, 0, baseCSV.length);  
        
        examenCSV[baseCSV.length] = String.valueOf(preguntasAbiertas.size());
        
        examenCSV[baseCSV.length + 1] = String.valueOf(preguntasCerradas.size());  
        
        return examenCSV;
    }


    public static Examen fromCSV(String[] datos) {
    	
        String titulo = datos[1];
        
        String descripcion = datos[2];
        
        String objetivo = datos[3];
        
        int duracion = Integer.parseInt(datos[5]);
        
        int numPreguntasAbiertas = Integer.parseInt(datos[10]);
        
        int numPreguntasCerradas = Integer.parseInt(datos[11]);  

        
        List<Pregunta> preguntasAbiertas = new ArrayList<>();
        
        List<Pregunta> preguntasCerradas = new ArrayList<>();

        
        Examen examen = new Examen(titulo, descripcion, duracion, objetivo, preguntasAbiertas, preguntasCerradas);
        
        return examen;
    }

   

	
}
