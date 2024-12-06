package Lógica;
import java.util.Date;

//ADVERTENCIA
public class Advertencia {
	private int id;
	
    private String tipo;
    
    private String mensaje;
    
    private Date fechaGeneracion; 
    
    private boolean estado;     
    
    private Actividad actividadRelacionada; 

    // Constructor
    public Advertencia(int id, String tipo, String mensaje, Date fechaGeneracion, boolean estado, Actividad actividadRelacionada) {
        this.id = id;
        
        this.tipo = tipo;
        
        this.mensaje = mensaje;
        
        this.fechaGeneracion = fechaGeneracion;
        
        this.estado = estado;
        
        this.actividadRelacionada = actividadRelacionada;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Actividad getActividadRelacionada() {
        return actividadRelacionada;
    }

    public void setActividadRelacionada(Actividad actividadRelacionada) {
        this.actividadRelacionada = actividadRelacionada;
    }

    // Método para mostrar la advertencia que se verá si hay prerrequsito
    public void mostrarAdvertencia() {
    	
        System.out.println("Advertencia ID: " + id);
        
        System.out.println("Tipo: " + tipo);
        
        System.out.println("Mensaje: " + mensaje);
        
        System.out.println("Fecha de generación: " + fechaGeneracion);
        
        System.out.println("Estado: " + (estado ? "Activa" : "Inactiva"));
        
        System.out.println("Actividad relacionada: " + (actividadRelacionada != null ? actividadRelacionada.getTitulo() : "Ninguna"));
    }
}

