package Lógica;
import java.util.Date;

public class Progreso {
	private double porcentaje;  
	
    private int tiempo;      
    
    private boolean tasaExito;  
    
    private Date fechaIni;  
    
    private Date fechaFin;       

    // Constructor
    public Progreso(double porcentaje, int tiempo, boolean tasaExito, Date fechaIni, Date fechaFin) {
        this.porcentaje = porcentaje;
        
        this.tiempo = tiempo;
        
        this.tasaExito = tasaExito;
        
        this.fechaIni = fechaIni;
        
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isTasaExito() {
        return tasaExito;
    }

    public void setTasaExito(boolean tasaExito) {
        this.tasaExito = tasaExito;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Método para mostrar el progreso
    public void mostrarProgreso() {
        System.out.println("Progreso del Learning Path:");
        
        System.out.println("Porcentaje completado: " + porcentaje + "%");
        
        System.out.println("Tiempo dedicado: " + tiempo + " minutos");
        
        System.out.println("Tasa de éxito: " + (tasaExito ? "Alcanzado" : "No alcanzado"));
        
        System.out.println("Fecha de inicio: " + fechaIni);
        
        System.out.println("Fecha de finalización: " + fechaFin);
    }
}
