package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Lógica.Profesor;
import Lógica.Estudiante;
import Lógica.LearningPath;
import Lógica.Quiz;
import Lógica.Pregunta;
import Consola.Consola;
import Lógica.FeedbackProfesor;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class testProfesorEvaluacion {

    Profesor profesor;
    Estudiante estudiante;
    LearningPath learningPath;
    Quiz quiz;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setup() throws Exception {
        // Inicialización de objetos de prueba
        profesor = new Profesor("Juan Carlos", "carloss@uniandes.edu.co", "2468");
        estudiante = new Estudiante("Sebastian", "sebas@uniandes.edu.co", "1234");
        learningPath = new LearningPath("Java", "Aprender Java y sus fundamentos", "Curso", "Aprender Java de forma básica");

        // Crear preguntas para un quiz
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Java es un lenguaje de programación?", List.of("Verdadero", "Falso"), "Verdadero", 10, true)
        );
        quiz = new Quiz("Quiz de Java", "Evaluación básica de Java", 15, "Evaluar conocimientos básicos en Java", preguntas);

        // Agregar el quiz al Learning Path y al estudiante
        learningPath.agregarActividad(quiz);
        estudiante.inscribirseEnLearningPath(learningPath);

        // Agregar el LearningPath a la Consola para pruebas de eliminación
        Consola.agregarLearningPath(learningPath);

        // Redirigir la salida estándar a un flujo de captura para verificar la salida de la consola
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        // Limpiar los datos después de cada prueba
        learningPath.getActividades().clear();
        quiz.clearFeedbacks();
        
        // Restaurar la salida estándar
        System.setOut(System.out);

        // Limpiar los LearningPaths de la Consola después de cada prueba
        Consola.limpiarLearningPaths();
    }

    /*
     * Verificar que el profesor pueda buscar un usuario (estudiante) por correo
     */
    @Test
    void testBuscarUsuarioPorCorreo() throws Exception {
        Estudiante encontrado = (Estudiante) Consola.buscarUsuarioPorCorreo("sebas@uniandes.edu.co");

        assertNotNull(encontrado, "El estudiante no fue encontrado por correo.");
        assertEquals("Sebastian", encontrado.getUser(), "El nombre del estudiante encontrado no coincide.");
    }

    /*
     * Verificar que el profesor pueda revisar un quiz completado por el estudiante
     */
    @Test
    void testRevisarQuizEstudiante() throws Exception {
        // Simulación de respuestas correctas del estudiante
        List<Boolean> respuestas = Arrays.asList(true);
        estudiante.realizarQuiz(learningPath, "Quiz de Java", respuestas);

        // Revisar el quiz y verificar que el puntaje se ha registrado
        profesor.revisarQuizEstudiante(estudiante, learningPath, "Quiz de Java");

        assertEquals(10, quiz.obtenerPuntaje(), "El puntaje del Quiz no coincide con el esperado.");
        assertTrue(estudiante.getActividadesCompletadas().contains(quiz), "El Quiz no está marcado como completado por el estudiante.");

        // Verificar la salida en consola para la revisión del quiz
        assertTrue(outputStreamCaptor.toString().contains("El estudiante Sebastian obtuvo un puntaje de 10 en el quiz: Quiz de Java"),
                   "La revisión del quiz no se imprimió correctamente en la consola.");
    }

    /*
     * Verificar que el profesor pueda proporcionar feedback a una actividad
     */
    @Test
    void testProporcionarFeedback() throws Exception {
        // Crear un objeto FeedbackProfesor
        FeedbackProfesor feedbackprofesor = new FeedbackProfesor(4.5, "Buen trabajo, sigue practicando");

        // Agregar el feedback al quiz
        quiz.agregarFeedback(feedbackprofesor);

        // Verificar que el feedback se haya agregado correctamente
        assertEquals(1, quiz.getFeedbacks().size(), "El feedback no se agregó correctamente.");
        assertEquals("Buen trabajo, sigue practicando", quiz.getFeedbacks().get(0).getComentario(), "El comentario del feedback no coincide.");
        assertEquals(4.5, quiz.getFeedbacks().get(0).getRating(), "El rating del feedback no coincide.");

        // Ahora probamos la función de proporcionarFeedback
        profesor.proporcionarFeedback("Excelente trabajo en el quiz");

        // Verificar que el comentario fue impreso en la consola
        assertTrue(outputStreamCaptor.toString().contains("Profesor Juan Carlos ha proporcionado el siguiente feedback: Excelente trabajo en el quiz"), 
                   "El feedback no fue impreso correctamente en la consola.");
    }

    /*
     * Verificar que el profesor pueda eliminar un Learning Path
     */
    @Test
    void testEliminarLearningPath() throws Exception {
        // Agregar el Learning Path a la Consola
        Consola.agregarLearningPath(learningPath);

        // Eliminar el Learning Path
        Consola.eliminarLearningPath(learningPath);

        // Verificar que el Learning Path ya no está en la lista usando la función de búsqueda
        assertNull(Consola.buscarLearningPath(learningPath.getTitulo()), 
                   "El Learning Path no fue eliminado correctamente de Consola.");
    }
}