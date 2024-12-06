package Persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Lógica.Actividad;

//CSVs. Se guarda el objeto y despues se pasa a los csvs
public class ArchivoCSV {
	// Método para escribir datos en un archivo CSV
    public static void escribirCSV(String rutaArchivo, List<String[]> datos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String[] fila : datos) {
                writer.write(String.join(",", fila));
                writer.newLine();
            }
        }
    }

    // Método para leer datos desde un archivo CSV
    public static List<String[]> leerCSV(String rutaArchivo) throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] fila = linea.split(",");
                datos.add(fila);
            }
        }
        return datos;
    }
    
    public static void guardarActividadesCSV(List<Actividad> actividades, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Actividad actividad : actividades) {
                String[] datosActividad = actividad.toCSV();
                writer.write(String.join(",", datosActividad));
                writer.newLine();
            }
        }
    }
    
    public static List<Actividad> cargarActividadesCSV(String rutaArchivo) throws IOException {
        List<Actividad> actividades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Actividad actividad = Actividad.fromCSV(linea);
                actividades.add(actividad);
            }
        }
        return actividades;
    }
}
