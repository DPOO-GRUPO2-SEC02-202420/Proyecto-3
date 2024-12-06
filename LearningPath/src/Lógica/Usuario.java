package Lógica;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
public abstract class Usuario implements Serializable {
	
protected String user;

protected String password;

protected String correo;


public Usuario(String user, String password, String correo) {
	super();
	
	this.user = user;
	
	this.password = password;
	
	this.correo = correo;
}


public String getUser() {	
	return user;
}


public void setUser(String user) {
	this.user = user;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getCorreo() {
	return correo;
}


public void setCorreo(String correo) {
	this.correo = correo;
}

//Método para autenticación; para comprobar que si sea las mismas credenciales
public boolean autenticar(String correo, String contraseña) {
    
    return this.correo.equals(correo) && this.password.equals(contraseña);
}

// Método para guardar este usuario en un archivo CSV
    public void guardarEnCSV() throws IOException {
        String archivo = "usuarios.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) { // Append: true
            if (this instanceof Estudiante) {
                writer.write("Estudiante," + user + "," + correo + "," + password);
            } else if (this instanceof Profesor) {
                writer.write("Profesor," + user + "," + correo + "," + password);
            } else {
                writer.write("Usuario," + user + "," + correo + "," + password);
            }
            writer.newLine();
        }
    }
}

