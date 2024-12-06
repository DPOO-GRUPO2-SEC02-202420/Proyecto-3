package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Interfaz.Feedback;

import Lógica.Estudiante;

import Lógica.FeedbackProfesor;
import Lógica.LearningPath;
import Lógica.Pregunta;
import Lógica.Quiz;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class testEstudiante {

    Estudiante estudiante;
    LearningPath learningPath;
    Quiz quiz; 

@BeforeEach

void setup() throws Exception {

    //Acá iniciamos objetos de prueba

    estudiante = new Estudiante("Sebitas", "sebitas@uniandes.edu.co", "1234");

    learningPath = new LearningPath("Java", "Aprender Java y sus fundamnetos", "Curso", "Aprender java y que te vaya bien en Java");

    //LISTA DE PREGUNTAS

    List<Pregunta> preguntas = Arrays.asList(
        new Pregunta ("Java es un lenguaje de programación ?", List.of( "Verdadero", "Falso"), "Verdadero", 10, true   
    ));

    quiz = new Quiz("Quiz de Java", "Son preguntas de verdadero y falso de Java", 15, "Que tenga un poco de conocimineto en Java", preguntas);

    learningPath.agregarActividad(quiz);

    estudiante.inscribirseEnLearningPath(learningPath);

    // Agregar feedback de ejemplo al Quiz
    Feedback feedbackProfesor = new FeedbackProfesor(8.5, "Buen trabajo, pero revisa el tema de excepciones.");
    quiz.agregarFeedback(feedbackProfesor);

}
@AfterEach
    void tearDown() {
        // Limpieza después de cada prueba para evitar acumulación de datos
        estudiante.getActividadesCompletadas().clear();

        learningPath.getActividades().clear();
        
        quiz.clearFeedbacks(); 
    }
/* 
 * Acá verificamos que el estudiante pueda inscribirse a un Learning Path
*/
@Test 

void testInscripcionLearningPath() throws Exception {

    assertTrue(estudiante.getLearningPaths().contains(learningPath), "El estudiante no está inscrito en el Learning Path esperado.");

}

/*
 * Acá el estundiante va a realizar un Quiz para evaluar su conocimiento en lo que lleva del curso
 */
@Test

void testRealizarQuiz() throws Exception{

    List<Boolean> respuestas = Arrays.asList(true);

    estudiante.realizarQuiz(learningPath, "Quiz de Java", respuestas);

    assertTrue(estudiante.getActividadesCompletadas().contains(quiz), " El quiz no está completado");

    assertEquals(10, quiz.obtenerPuntaje(), "El puntaje no coincide con el esperado ");

}

/*
 * Acá el estudiante va a ver su progreso en el Learning Path
 */
@Test

void visualizarProgresoLearningPath() throws Exception{

    List<Boolean> respuestas = Arrays.asList(true);

    estudiante.realizarQuiz(learningPath, "Quiz de Java", respuestas);

    double progreso = learningPath.calcularProgreso(estudiante);

    assertEquals(100.0, progreso," EL progreso debería ser de 100% ");

}

/*
 * Acá el estudiante completa la actividad del Learning Path 
 */
@Test 

void completarActividad() throws Exception{

    estudiante.completarActividad(quiz);

    assertTrue(estudiante.getActividadesCompletadas().contains(quiz), "La actividad debería estar completada");

}

/*
 * Acá el estudiante quiere ver el feedback proporcionado por el profesor
 */
@Test 

void verificarVisualizacionFeedback() {
     
     System.out.println("Feedbacks para la actividad: " + quiz.getTitulo());

     estudiante.verFeedbacks(quiz);
}

}
