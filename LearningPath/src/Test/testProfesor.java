package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Lógica.Profesor;
import Lógica.Pregunta;
import Lógica.LearningPath;
import Lógica.Quiz;
import Lógica.Tarea;
import Consola.Consola;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class testProfesor {

    Profesor profesor;
    LearningPath learningPath;
    Quiz quiz; 
    Tarea tarea;

    @BeforeEach
    void setup() {
        // Inicializar objetos de prueba
        profesor = new Profesor("Juan Carlos", "carlos@uniandes.edu.co", "2468");
        learningPath = new LearningPath("Java", "Aprender Java y sus fundamentos", "Curso", "Introducción a Java");

        // Crear actividades de tipo Quiz y Tarea
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Java es un lenguaje de programación?", List.of("Verdadero", "Falso"), "Verdadero", 10, true)
        );
        quiz = new Quiz("Quiz de Java", "Preguntas sobre Java", 15, "Evaluar conocimientos básicos en Java", preguntas);
        tarea = new Tarea("Tarea 1", "Completar ejercicios básicos", 60, "Practicar conceptos iniciales", "No Entregada");

        // Agregar el Learning Path al profesor y a Consola para pruebas
        profesor.crearLearningPath(learningPath);
        Consola.agregarLearningPath(learningPath); 
    }

    @AfterEach
    void tearDown() {
        // Limpiar los LearningPaths y actividades al final de cada prueba
        profesor.getLearningPaths().clear();
        learningPath.getActividades().clear();
        quiz.clearFeedbacks();
        tarea.getFeedbacks().clear();
        Consola.limpiarLearningPaths();
    }

    /**
     * Verificar que el profesor pueda crear un Learning Path usando su método.
     */
    @Test
    void testCrearLearningPathProfesor() {
        LearningPath nuevoLearningPath = new LearningPath("Python", "Aprender Python y sus fundamentos", "Curso", "Introducción a Python");
        profesor.crearLearningPath(nuevoLearningPath);
        assertEquals(2, profesor.getLearningPaths().size(), "El profesor no ha agregado el nuevo Learning Path correctamente.");
        assertTrue(profesor.getLearningPaths().contains(nuevoLearningPath), "El nuevo Learning Path no se encuentra en la lista del profesor.");
        assertEquals("Python", profesor.getLearningPaths().get(1).getTitulo(), "El título del nuevo Learning Path no coincide.");
    }

    /**
     * Verificar que se pueda agregar una actividad a un Learning Path
     */
    @Test
    void testAgregarActividadProfesor() {
        learningPath.agregarActividad(tarea);
        assertTrue(learningPath.getActividades().contains(tarea), "La actividad no se agregó al Learning Path.");
        assertEquals(60, learningPath.getDuracion(), "La duración del Learning Path no se actualizó correctamente.");
    }

    /**
     * Verificar que el profesor pueda administrar un Learning Path (añadir y eliminar actividades)
     */
    @Test
    void testAdministrarLearningPaths() {
        learningPath.agregarActividad(quiz);
        assertTrue(learningPath.getActividades().contains(quiz), "El quiz no fue agregado al Learning Path.");
        learningPath.eliminarActividad(quiz);
        assertFalse(learningPath.getActividades().contains(quiz), "El quiz no fue eliminado correctamente.");
    }

    /**
     * Verificar que el profesor pueda buscar un Learning Path por título
     */
    @Test
    void testBuscarLearningPath() {
        LearningPath encontrado = Consola.buscarLearningPath("Java");
        assertNotNull(encontrado, "El Learning Path no fue encontrado.");
        assertEquals("Java", encontrado.getTitulo(), "El título del Learning Path encontrado no coincide.");
    }

    /**
     * Verificar que no se puedan agregar LearningPaths duplicados
     */
    @Test
    void testAgregarLearningPathDuplicado() {
        assertThrows(IllegalArgumentException.class, () -> profesor.crearLearningPath(learningPath), 
            "No se lanzó una excepción al intentar agregar un Learning Path duplicado.");
    }

    /**
     * Verificar que no se puedan buscar LearningPaths con entradas inválidas
     */
    @Test
    void testBuscarLearningPathEntradaInvalida() {
        assertNull(Consola.buscarLearningPath(null), "Se encontró un Learning Path con entrada nula.");
        assertNull(Consola.buscarLearningPath(""), "Se encontró un Learning Path con título vacío.");
    }

    /**
     * Verificar restricciones al eliminar LearningPaths con estudiantes inscritos
     */
    @Test
    void testEliminarLearningPathConRestricciones() {
        assertThrows(IllegalStateException.class, () -> Consola.eliminarLearningPath(learningPath), 
            "No se lanzó una excepción al intentar eliminar un Learning Path con estudiantes inscritos.");
    }
}
