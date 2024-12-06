package Interfaces_Graficas;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
public class GraficaActividadesRealizadas extends JFrame {
    public GraficaActividadesRealizadas(Map<String, Integer> actividadesPorDia) {
        // Configuración de la ventana
        setTitle("Gráfica de Actividades Realizadas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal para la gráfica
        JPanel panelGrafica = new JPanel(new GridLayout(7, 52)); // 7 días x 52 semanas

        // Generar las celdas para la gráfica
        for (int i = 0; i < 7; i++) { // 7 días de la semana
            for (int j = 0; j < 52; j++) { // 52 semanas del año
                JPanel celda = new JPanel();
                String claveDia = generarClaveDia(i, j); // Crear la clave única para cada día
                int valor = actividadesPorDia.getOrDefault(claveDia, 0);

                // Asignar color según el valor
                celda.setBackground(obtenerColorPorValor(valor));
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                panelGrafica.add(celda);
            }
        }

        // Agregar el panel de la gráfica a la ventana
        add(panelGrafica, BorderLayout.CENTER);
    }

    // Método para generar una clave única para cada día basado en día y semana
    private String generarClaveDia(int diaSemana, int semana) {
        return "W" + semana + "D" + diaSemana; // Ejemplo: W1D1 (Semana 1, Lunes)
    }

    // Método para asignar un color basado en la intensidad de actividades realizadas
    private Color obtenerColorPorValor(int valor) {
        if (valor == 0) return Color.WHITE;
        else if (valor < 5) return Color.LIGHT_GRAY;
        else if (valor < 10) return Color.GREEN;
        else if (valor < 20) return Color.BLUE;
        else return Color.RED;
    }
}
