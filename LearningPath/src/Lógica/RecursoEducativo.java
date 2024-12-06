package Lógica;

public class RecursoEducativo extends Actividad {
	
	private String tipoRecurso;
	
	public RecursoEducativo(String titulo, String descripcion, int duracion, String objetivo, String tipoRecurso) {
        
        super(0, titulo, descripcion, objetivo, "", duracion, "Completada", null, 0);
        this.tipoRecurso = tipoRecurso;
    }

    // Getter y Setter para el tipo de recurso
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public void completar() {
        
        this.resultado = "Completada";
        
        System.out.println("El recurso educativo ha sido completado.");
    }
    
 // Sobrescribimos el método toCSV() para incluir el atributo tipoRecurso porque pues es de actividad
    @Override
    public String[] toCSV() {
    	
        String[] baseCSV = super.toCSV();  
        
        String[] recursoCSV = new String[baseCSV.length + 1];
        
        System.arraycopy(baseCSV, 0, recursoCSV, 0, baseCSV.length); 
        
        recursoCSV[baseCSV.length] = this.tipoRecurso;  
        
        return recursoCSV;
    }

    // Método para crear una instancia de RecursoEducativo desde una línea CSV
    public static RecursoEducativo fromCSV(String[] datos) {
    	
        String titulo = datos[1];
        
        String descripcion = datos[2];
        
        String objetivo = datos[3];
        
        int duracion = Integer.parseInt(datos[5]);
        
        String tipoRecurso = datos[10];  

        // Creamos el RecursoEducativo
        return new RecursoEducativo(titulo, descripcion, duracion, objetivo,tipoRecurso);
    }

  
}
