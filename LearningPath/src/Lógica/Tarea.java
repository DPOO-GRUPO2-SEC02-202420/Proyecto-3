package Lógica;

import java.io.Serializable;

public class Tarea extends Actividad implements Serializable {
	
private String estado;

public Tarea(String titulo, String descripcion, int duracion, String objetivo, String estadoEntrega) {
	
    
    super(0, titulo, descripcion, objetivo, "", duracion, "Pendiente", null, 0);
    this.estado = "No Entregada";
}



@Override
public void completar(){
	if("No Entregada".equals(estado)) {
		
		this.estado = "Entregado";
		
		this.resultado = "Completada"; //Esto marca la actividad como completada
		
		System.out.println("La tarea ha sido entregada y marcada como completada.");
	} else {
        System.out.println("La tarea ya ha sido entregada.");
    }
}


//Método para evaluar la tarea para que se simule esa evaluación
public void evaluarTarea(String evaluacion) {
	
    if ("Entregada".equals(estado)) {
    	
        this.estado = "Evaluada";
        System.out.println("La tarea ha sido evaluada: " + evaluacion);
        
    } else {
        System.out.println("La tarea no ha sido entregada aún.");
    }
}

// Getters y Setters
public String getEstadoEntrega() {
    return estado;
}

public void setEstadoEntrega(String estadoEntrega) {
    this.estado = estadoEntrega;
}


//Sobrescribimos el método toCSV()  de nuevo
@Override
public String[] toCSV() {
    String[] baseCSV = super.toCSV();  
    
    String[] tareaCSV = new String[baseCSV.length + 1]; 
    
    System.arraycopy(baseCSV, 0, tareaCSV, 0, baseCSV.length);
    
    tareaCSV[baseCSV.length] = this.estado;
    
    return tareaCSV;
}

//Método para crear una instancia de Tarea desde una línea CSV
public static Tarea fromCSV(String[] datos) {
	
    String titulo = datos[0];
    
    String descripcion = datos[1];
    
    String objetivo = datos[2];
    
    String dificultad = datos[3];
    
    int duracion = Integer.parseInt(datos[4]);
    
    String resultado = datos[5];
    
    int orden = Integer.parseInt(datos[6]);
    String estado = datos[7];  

    
    return new Tarea(titulo, descripcion,duracion, objetivo, estado);
}
}
