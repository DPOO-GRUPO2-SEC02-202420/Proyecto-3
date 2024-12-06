package Persistencia;
import java.io.*;


//Guarda los datos en un objeto

public class PersistenciaSerializable {
	// Método para serializar y guardar un objeto en un archivo
    public static void guardarObjeto(Object objeto, String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(objeto);
            System.out.println("Objeto guardado correctamente.");
        }
    }

    // Método para cargar un objeto desde un archivo serializado
    public static Object cargarObjeto(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            System.out.println("Objeto cargado correctamente.");
            return ois.readObject();
        }
    }
}

