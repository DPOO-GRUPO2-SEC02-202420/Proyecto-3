package Lógica;
import java.io.Serializable;
import java.util.List;
//PREGUNTA
public class Pregunta implements Serializable {

	private String enunciado; 
	
    private List<String> opciones;
    
    private String respuestaCorrecta; 
    
    private int puntaje; 

    private boolean esVerdaderoFalso;

    // Constructor
    public Pregunta(String enunciado, List<String> opciones, String respuestaCorrecta, int puntaje, boolean esVerdaderoFalso)  {
        this.enunciado = enunciado;
        
        this.opciones = opciones;
        
        this.respuestaCorrecta = respuestaCorrecta;
        
        this.puntaje = puntaje;

        this.esVerdaderoFalso = esVerdaderoFalso;
        
    }
    
    public Pregunta(String enunciado) {
    	
        this.enunciado = enunciado;
        
        this.opciones = null; 
        
        this.respuestaCorrecta = null;
        
        this.puntaje = 0; 
    }

    // Getters y Setters
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }


    public List<String> getOpciones() {
		return opciones;
	}

	public boolean isEsVerdaderoFalso() {
        return esVerdaderoFalso;
    }

    public void setEsVerdaderoFalso(boolean esVerdaderoFalso) {
        this.esVerdaderoFalso = esVerdaderoFalso;
    }

    public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
