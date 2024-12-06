package Lógica;
import java.util.HashMap;
import java.util.Map;

//AUTENTICADOR
public class Autenticador {
	
    private Map<String, Integer> intentosFallidos;
    
    private Map<String, Long> tiemposBloqueo; 

    // Constructor
    public Autenticador() {
        this.intentosFallidos = new HashMap<>();
        
        this.tiemposBloqueo = new HashMap<>();
    }

 // Este metodo te verifica tus credenciales. Si las ingresas mal te cuenta las veces que lo intentas y te bloquea la cuenta por segundos
    public boolean autenticar(Usuario usuario, String correo, String contraseña, String tipoUsuario) {
    	
        String claveUsuario = usuario.getCorreo();
        

        
        if (tiemposBloqueo.containsKey(claveUsuario)) {
        	
            long tiempoActual = System.currentTimeMillis();
            
            long tiempoBloqueo = tiemposBloqueo.get(claveUsuario);
            
            long tiempoEspera = calcularTiempoEspera(intentosFallidos.get(claveUsuario));

            if (tiempoActual - tiempoBloqueo < tiempoEspera) {
            	
                System.out.println("Usuario bloqueado. Por favor, espera " + (tiempoEspera / 1000) + " segundos.");
                
                return false;
                
            } else {
            	
                
                tiemposBloqueo.remove(claveUsuario);
            }
        }

        
        if (usuario.autenticar(correo, contraseña)) {
           
            if (tipoUsuario.equals("Estudiante") && usuario instanceof Estudiante) {
            
                intentosFallidos.remove(claveUsuario);
                
                System.out.println("Autenticación exitosa como Estudiante.");
                
                return true;
                
            } else if (tipoUsuario.equals("Profesor") && usuario instanceof Profesor) {
                
                intentosFallidos.remove(claveUsuario);
                
                System.out.println("Autenticación exitosa como Profesor.");
                
                return true;
                
            } else {
                System.out.println("Autenticación fallida. Tipo de usuario incorrecto.");
                
                return false;
            }
        } else {
            
            intentosFallidos.put(claveUsuario, intentosFallidos.getOrDefault(claveUsuario, 0) + 1);
            
            System.out.println("Autenticación fallida. Intentos: " + intentosFallidos.get(claveUsuario));

            
            if (intentosFallidos.get(claveUsuario) >= 3) {
            	
                tiemposBloqueo.put(claveUsuario, System.currentTimeMillis());
                
                System.out.println("Usuario bloqueado temporalmente.");
            }

            return false;
        }
    }

    // Método para calcular el tiempo de espera basado en los intentos fallidos
    private long calcularTiempoEspera(int intentosFallidos) {
        
        return (long) Math.min(15 * Math.pow(2, intentosFallidos - 3), 60) * 1000; // Máximo 1 min
    }
}
