package Consola;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Lógica.*;
import Persistencia.*;
import java.text.SimpleDateFormat;
import Interfaz.*;
//CONSOLA
public class Consola {
	
    private static List<Usuario> usuarios = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    
    private static Usuario usuarioAutenticado;
    
    private static List<LearningPath> learningPaths = new ArrayList<>();
    
    private static Autenticador autenticador = new Autenticador();

    // Acá este con este método inicias sesión
    
    public static void iniciarSesion() {
    	
        System.out.print("Ingrese su correo: ");
        
        String correo = scanner.nextLine();
        
        System.out.print("Ingrese su contraseña: ");
        
        String contraseña = scanner.nextLine();

        Usuario usuarioEncontrado = buscarUsuarioPorCorreo(correo);
        
        if (usuarioEncontrado == null) {
        	
            System.out.println("Usuario no encontrado. Regresando al menú principal.");
            
            return;  // Esto regresa al menú principal sin detener el programa
        }
        
        String tipoUsuario = (usuarioEncontrado instanceof Estudiante) ? "Estudiante" : "Profesor";
        
        boolean autenticado = autenticador.autenticar(usuarioEncontrado, correo, contraseña, tipoUsuario);
        
        if (autenticado) 
        {
            usuarioAutenticado = usuarioEncontrado;
            
            if (usuarioAutenticado instanceof Profesor) 
            {
                mostrarMenuProfesor();
                
            } else 
            	
            {
                mostrarMenuEstudiante();
                
            }
            
        } else 
        	
        {
            System.out.println("Credenciales incorrectas.");
        }
    }

    // Este es el menú del profesor
    public static void mostrarMenuProfesor() {
    	
        int opcion;
        
        do {
            System.out.println("\n--- Menú Profesor ---");
            
            System.out.println("1. Crear Learning Path");
            
            System.out.println("2. Administrar Learning Path");
            
            System.out.println("3. Guardar Learning Paths");
            
            System.out.println("4. Cargar Learning Paths");
            
            System.out.println("5. Dar Feedback a estudiante");
            
            System.out.println("6. Cerrar Sesión");
            
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            
            scanner.nextLine();  // Consumir la nueva línea

            switch (opcion) 
            {
                case 1:
                	
                    crearLearningPath();
                    
                    break;
                    
                case 2:
                	
                    administrarLearningPaths();
                    
                    break;
                    
                case 3:
                	
                    guardarLearningPaths();
                    
                    break;
                    
                case 4:
                	
                    cargarLearningPaths();
                    
                    break;
                    
                case 5:
                	
                	agregarFeedbackActividad(); // Nueva función para dar feedback
                	
                    break;
                    
                case 6:
                	
                    System.out.println("Sesión cerrada.");
                    
                    usuarioAutenticado = null;
                    
                    break;
                    
                default:
                	
                    System.out.println("Opción no válida.");
                    
            }
            
        }
        while (opcion != 6);
        
    }
    
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
    // Este es el menú de los estudiantes
    public static void mostrarMenuEstudiante()
    {
    	int opcion = 0;
    	
        boolean continuar = true;
        
        while (continuar) {
            try {
                System.out.println("\n--- Menú Estudiante ---");
                
                System.out.println("1. Ver Learning Paths");
                
                System.out.println("2. Realizar Learning Path");
                
                System.out.println("3. Ver Feedback");
                
                System.out.println("4. Dejar Rating en un Learning Path");
                
                System.out.println("5. Cerrar Sesión");
                
                System.out.print("Seleccione una opción: ");
                
                opcion = scanner.nextInt();
                
                scanner.nextLine(); // Consumir la nueva línea
                
                switch (opcion) {
                
                    case 1:
                    	
                        listarLearningPaths();
                        
                        break;
                        
                    case 2:
                    	
                        realizarLearningPath();
                        
                        break;
                        
                    case 3:
                    	
                        verFeedbacksEstudiante();
                        
                        break;
                        
                    case 4:
                    	
                        dejarRatingEstudiante();
                        
                        break;
                        
                    case 5:
                    	
                        System.out.println("Sesión cerrada.");
                        
                        return;
                        
                    default:
                    	
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e)
            {
                System.out.println("Opción no válida. Ingrese un número.");
                
                scanner.nextLine(); // Consumir entrada inválida
            }
        } while (opcion != 5);
    }
    
    // Acá un profesor puede crear un Learning Path
    public static void crearLearningPath() 
    {
        if (!(usuarioAutenticado instanceof Profesor)) 
        {
            System.out.println("Solo los profesores pueden crear Learning Paths.");
            
            return;
        }

        System.out.println("\n--- Crear Learning Path ---");
        
        System.out.print("Título: ");
        
        String titulo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        
        String descripcion = scanner.nextLine();
        
        System.out.print("Tipo (Estándar, Intermedio, Avanzado): ");
        
        String tipo = scanner.nextLine();
        
        System.out.print("Objetivo: ");
        
        String objetivo = scanner.nextLine();  // Aquí se añade el objetivo


        LearningPath nuevoPath = new LearningPath(titulo, descripcion, tipo, objetivo);
        
        learningPaths.add(nuevoPath);
        
        System.out.println("Learning Path creado exitosamente.");
        // Guardar en el CSV
        guardarLearningPaths();
    }

    // Aca se realiza este metodo para que el profesor pueda agregar o borrar el Learning Path
    public static void administrarLearningPaths() 
    {
        if (!(usuarioAutenticado instanceof Profesor))
        {
            System.out.println("Solo los profesores pueden administrar Learning Paths."); //Para que un estudiante no se cole
            
            return;
        }

        listarLearningPaths();
        
        System.out.print("Seleccione el título del Learning Path a administrar: ");
        
        String titulo = scanner.nextLine();
        
        LearningPath learningPath = buscarLearningPath(titulo);

        if (learningPath == null) 
        {
            System.out.println("Learning Path no encontrado.");
            
            return;
        }

        int opcion;
        
        do {
            System.out.println("\n--- Administrar Learning Path ---");
            
            System.out.println("1. Agregar Actividad");
            
            System.out.println("2. Eliminar Learning Path");
            
            System.out.println("3. Volver al menú principal");
            
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                	
                    agregarActividad(learningPath);
                    
                    break;
                    
                case 2:
                	
                    eliminarLearningPath(learningPath);
                    
                    break;
                    
                case 3:
                	
                    return;
                    
                default:
                	
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
    
    public static void agregarLearningPath(LearningPath nuevoPath) {
        learningPaths.add(nuevoPath);
    }

    // Limpiar la lista de Learning Paths (opcional, útil para pruebas)
    public static void limpiarLearningPaths() {
        learningPaths.clear();
    }

    // Acá empezamos a crear la actividad del Learning Path 
    
    public static void agregarActividad(LearningPath learningPath)
    {
        System.out.print("Título de la actividad: ");
        
        String titulo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        
        String descripcion = scanner.nextLine();
        
        System.out.print("Objetivo de la actividad: ");
        
        String objetivo = scanner.nextLine();
        
        System.out.print("Duración (en minutos) : ");
        
        int duracion = scanner.nextInt();
        
        scanner.nextLine();
        
        System.out.print("Dificultad (Fácil, Medio, Difícil): ");
        
        String dificultad = scanner.nextLine();
        
        System.out.print("Tipo de actividad (Tarea, Quiz, Examen, Encuesta, Recurso): ");
        
        String tipo = scanner.nextLine();

        Actividad nuevaActividad = null;
        
        switch (tipo.toLowerCase()) {
            case "tarea":
                nuevaActividad = new Tarea(titulo, descripcion, duracion, objetivo, "No Entregada");
                break;
            case "quiz":
                nuevaActividad = crearQuiz(titulo, descripcion, duracion, objetivo);
                break;
            case "examen":
                nuevaActividad = crearExamen(titulo, descripcion, duracion, objetivo);
                break;
            case "encuesta":
                nuevaActividad = crearEncuesta(titulo, descripcion, duracion, objetivo);
                break;
            case "recurso":
            	System.out.print("Ingrese el tipo de recurso (PDF, Video, etc.): ");
                String tipoRecurso = scanner.nextLine();
                nuevaActividad = new RecursoEducativo(titulo, descripcion, duracion, objetivo, tipoRecurso);
                break;
            default:
                System.out.println("Tipo de actividad no válido.");
                
                return;
                
        }
     // Acá decimos si es un prerrequisitos o no cierta actividad
        System.out.print("¿Esta actividad tiene un prerrequisito? (Sí/No): ");
        
        
        String tienePrerequisito = scanner.nextLine().toLowerCase();
        
        if (tienePrerequisito.equals("sí") || tienePrerequisito.equals("si"))
        	
        {
            System.out.print("Indique el título del prerrequisito: ");
            
            String tituloPrerequisito = scanner.nextLine();
            
            Actividad actividadPrerequisito = learningPath.buscarActividad(tituloPrerequisito);
            
            if (actividadPrerequisito != null) 
            {
                nuevaActividad.setPrerequisito(actividadPrerequisito);
            } else 
            {
                System.out.println("Prerrequisito no encontrado.");
            }
        }
        
        if (nuevaActividad != null) 
        {
            learningPath.agregarActividad(nuevaActividad);
            
            System.out.println("Actividad agregada con éxito.");
            
            
         // Guardar actividades en CSV después de agregar
            try {
                ArchivoCSV.guardarActividadesCSV(learningPath.getActividades(), "actividades.csv");
                // Guardar actividades
                System.out.println("Actividades guardadas en CSV correctamente.");
                
            } catch (IOException e) 
            {
                System.out.println("Error al guardar las actividades.");
                
                e.printStackTrace();
            
            
        }
        
        
    }
    }
    // Aca creamos un quiz con sus preguntas cerradas y sus 4 opciones
    public static Quiz crearQuiz(String titulo, String descripcion, int duracion, String objetivo) 
    {
    	Scanner scanner = new Scanner(System.in);
    
    System.out.print("Ingrese cuántas preguntas quiere en el quiz: ");

    int numPreguntas = scanner.nextInt();
    
    scanner.nextLine(); // Consumir la nueva línea

    List<Pregunta> preguntas = new ArrayList<>();
    
    for (int i = 1; i <= numPreguntas; i++) {

        System.out.print("¿La pregunta es de verdadero/falso? (true/false): ");

        boolean esVerdaderoFalso = scanner.nextBoolean();

        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese el enunciado de la pregunta " + i + ": ");

        String enunciado = scanner.nextLine();

        List<String> opciones = new ArrayList<>();
        
        if (esVerdaderoFalso) {
            // Si es de verdadero/falso, añadimos solo dos opciones: "Verdadero" y "Falso"
            opciones.add("Verdadero");

            opciones.add("Falso");
        } else {
            // Para preguntas de opción múltiple, pedimos cuatro opciones
            for (int j = 1; j <= 4; j++) {

                System.out.print("Ingrese la opción " + j + ": ");

                opciones.add(scanner.nextLine());
            }
        }

        System.out.print("Ingrese el número de la opción correcta (1 a " + opciones.size() + "): ");

        int opcionCorrectaIndex = scanner.nextInt();

        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese el puntaje de la pregunta: ");

        int puntaje = scanner.nextInt();

        scanner.nextLine(); // Consumir la nueva línea

        // Crear la pregunta con el tipo adecuado

        Pregunta pregunta = new Pregunta(enunciado, opciones, opciones.get(opcionCorrectaIndex - 1), puntaje, esVerdaderoFalso);

        preguntas.add(pregunta);
    }

    System.out.print("Ingrese la calificación mínima para pasar el quiz: ");

    double calificacionMinima = scanner.nextDouble();

    scanner.nextLine(); // Consumir la nueva línea

    Quiz quiz = new Quiz(titulo, descripcion, duracion, objetivo, preguntas);

    quiz.setCalificacionMinima(calificacionMinima);

    return quiz;
    }

    // Creamos un examen con preguntas abiertas
    public static Examen crearExamen(String titulo, String descripcion, int duracion, String objetivo) {
    	
        System.out.print("Número de preguntas abiertas: ");
        
        int numPreguntasAbiertas = scanner.nextInt();
        
        scanner.nextLine();
        
        List<Pregunta> preguntasAbiertas = new ArrayList<>();

        for (int i = 0; i < numPreguntasAbiertas; i++) {
        	
            System.out.print("Pregunta abierta " + (i + 1) + ": ");
            
            String textoPregunta = scanner.nextLine();
            
            preguntasAbiertas.add(new Pregunta(textoPregunta));
        }

        return new Examen(titulo, descripcion, duracion, objetivo, preguntasAbiertas, new ArrayList<>());
    }

    // Creamos una encuesta 
    public static Encuesta crearEncuesta(String titulo, String descripcion, int duracion, String objetivo) {
    	
        System.out.print("Número de preguntas: ");
        
        int numPreguntas = scanner.nextInt();
        
        scanner.nextLine();

        List<Pregunta> preguntas = new ArrayList<>();
        
        for (int i = 0; i < numPreguntas; i++) {
        	
            System.out.print("Pregunta " + (i + 1) + ": ");
            
            String textoPregunta = scanner.nextLine();
            
            preguntas.add(new Pregunta(textoPregunta));
        }

        return new Encuesta(titulo, descripcion, duracion, objetivo, preguntas);
    }

    // Este metodo ayuda a buscar el título del Learning Path
    public static LearningPath buscarLearningPath(String titulo) 
    {
        for (LearningPath lp : learningPaths) 
        {
            if (lp.getTitulo().equalsIgnoreCase(titulo)) 
            {
                return lp;
            }
        }
        return null;
    }

    // Te muestra una pequeña lista de los Learning Paths que hay guardados
    public static void listarLearningPaths() 
    {
        if (learningPaths.isEmpty()) 
        {
            System.out.println("No hay Learning Paths disponibles.");
            
            return;
        }

        for (LearningPath lp : learningPaths) 
        {
            System.out.println("Título: " + lp.getTitulo() + " - Objetivo: " + lp.getObjetivo());
        }
    }

    // Es el metodo para que el estudiante realice el Learning Path
    public static void realizarLearningPath() 
    {
    	listarLearningPaths();
    	
        System.out.print("Seleccione el título del Learning Path: ");
        
        String titulo = scanner.nextLine();
        
        LearningPath learningPath = buscarLearningPath(titulo);

        if (learningPath == null)
        {
            System.out.println("Learning Path no encontrado.");
            
            return;
        }

        for (Actividad actividad : learningPath.getActividades()) {
        	
            if (actividad.getPrerequisito() != null && !actividad.getPrerequisito().estaCompleta((Estudiante) usuarioAutenticado)) {
            	
                System.out.println("Debe completar la actividad prerrequisito antes de continuar: " + actividad.getPrerequisito().getTitulo());
                
                return;
            }

            System.out.println("Realizando actividad: " + actividad.getTitulo());
            
            System.out.println("Objetivo: " + actividad.getObjetivo());
            
            actividad.completar();
        }
    }

    // Guardar Learning Paths en el CSV de learningpaths.csv
    public static void guardarLearningPaths() 
    {
    	String archivo = "learningPaths.csv";
    	
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) 
        {
            for (LearningPath lp : learningPaths) 
            {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Este formato te ayuda a poner la fecha actual y no una de muchos años como me pasó
                writer.write(lp.getTitulo() + "," + lp.getDescripcion() + "," + lp.getDuracion() + "," +
                             lp.getRating() + "," + lp.getTipo() + "," + sdf.format(lp.getFechaCreacion()) + "," +
                             sdf.format(lp.getFechaModificacion())	
                             + "," + lp.getObjetivo());
                
                writer.newLine(); // Nueva línea después de cada LearningPath
            }
            System.out.println("Learning Paths guardados en CSV.");
            
        } catch (IOException e) {
            System.out.println("Error al guardar Learning Paths.");
            
            e.printStackTrace();
        }
    }

    // Cargar Learning Paths (es necesario porque a veces no los encuentras si no los cargas :c )
    public static void cargarLearningPaths() {
    	try {
            List<String[]> datos = ArchivoCSV.leerCSV("learningpaths.csv"); 
            
            
            System.out.println("Learning Paths cargados exitosamente.");
            
        } catch (IOException e) 
    	{
            System.out.println("Error al cargar Learning Paths.");
            
            e.printStackTrace();
        }
    }

    // Eliminar un Learning Path
    public static void eliminarLearningPath(LearningPath learningPath)
    {
        learningPaths.remove(learningPath);
        
        System.out.println("Learning Path eliminado exitosamente.");
    }

    // EL METODO MAIN QUE INCIA LA CONSOLA
    
    public static void main(String[] args)
    {
    	 boolean continuar = true;
    	 
    	    while (continuar) 
    	    {
    	        mostrarMenuPrincipal();
    	        
    	        try {
    	        	
    	            int opcion = Integer.parseInt(scanner.nextLine()); // Leer como string y luego convertir a int
    	            
    	            switch (opcion)
    	            {
    	                case 1:
    	                    crearUsuario();
    	                    
    	                    break;
    	                    
    	                case 2:
    	                	
    	                    iniciarSesion();
    	                    
    	                    break;
    	                    
    	                case 3:
    	                	
    	                    cargarUsuarios();
    	                    
    	                    break;
    	                    
    	                case 4:
    	                	
    	                    continuar = false;
    	                    
    	                    break;
    	                    
    	                default:
    	                	
    	                    System.out.println("Opción no válida.");
    	                    
    	            }
    	        } catch (NumberFormatException e) 
    	        {
    	            System.out.println("Error: Debes ingresar un número válido.");
    	            
    	        }
    	    }
    	    System.out.println("Saliendo del sistema...");
            }
    // Mostrar menú principal. El primer menú de todos
    public static void mostrarMenuPrincipal() 
    {
        System.out.println("\n--- Menú Principal ---");
        
        System.out.println("1. Crear Usuario");
        
        System.out.println("2. Iniciar Sesión");
        
        System.out.println("3. Cargar Usuarios");
        
        System.out.println("4. Salir");
        
        System.out.print("Seleccione una opción: ");
    }

    // Creas un usuario que puede ser profesor o estudiante
    public static void crearUsuario() 
    {
        System.out.print("Nombre de usuario: ");
        
        String user = scanner.nextLine();
        
        System.out.print("Correo: ");
        
        String correo = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        
        String contraseña = scanner.nextLine();
        
        System.out.print("¿Es estudiante o profesor? (E/P): ");
        
        String tipoUsuario = scanner.nextLine().toUpperCase();

        Usuario nuevoUsuario;
        
        if (tipoUsuario.equals("E")) {
        	
            nuevoUsuario = new Estudiante(user, correo, contraseña);
            
        } else if (tipoUsuario.equals("P")) 
        {
            nuevoUsuario = new Profesor(user, correo, contraseña);
            
        } else {
            System.out.println("Tipo de usuario inválido.");
            
            return;
        }

        usuarios.add(nuevoUsuario);
        
        System.out.println("Usuario creado exitosamente.");
        
     // Apenas se crea el usuario se guarda en usuarios.csv
        try {
            guardarUsuariosCSV(); 
            
        } catch (IOException e) {
        	
            System.out.println("Error al guardar los usuarios.");
            
            e.printStackTrace();
        }
    }

//MÁS MÉTODOS
    
    //Te ayuda a buscar un usuario por correo
public static Usuario buscarUsuarioPorCorreo(String correo) 
{
    for (Usuario usuario : usuarios) { 
    	
        if (usuario.getCorreo().equals(correo)) {
        	
            return usuario;
        }
    }
 
    System.out.println("Usuario no encontrado.");
    
    return null; 
}

//Guarda los ususarios en el usuarios.csv
public static void guardarUsuariosCSV() throws IOException {
	
    String archivo = "usuarios.csv"; 
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
    	
        for (Usuario usuario : usuarios) {
        	
            if (usuario instanceof Estudiante) {
            	
                writer.write("Estudiante," + usuario.getUser() + "," + usuario.getCorreo() + "," + usuario.getPassword());
            } else if (usuario instanceof Profesor) {
                writer.write("Profesor," + usuario.getUser() + "," + usuario.getCorreo() + "," + usuario.getPassword());
            }
            
            writer.newLine();
        }
    }
    System.out.println("Usuarios guardados en " + archivo);
}

//Carga los usuarios (muy importante porque después no encuentra usuarios)
public static int cargarUsuarios() {
	int usuariosCargados = 0;
    try {
    	
        List<String[]> datos = ArchivoCSV.leerCSV("usuarios.csv");
        
        for (String[] fila : datos) {
        	
            if (fila.length == 4) {
            	
                String tipoUsuario = fila[0];
                
                String user = fila[1];
                
                String correo = fila[2];
                
                String contraseña = fila[3];
                
                Usuario nuevoUsuario;
                if (tipoUsuario.equalsIgnoreCase("Estudiante")) {
                	
                    nuevoUsuario = new Estudiante(user, correo, contraseña);
                    
                } else if (tipoUsuario.equalsIgnoreCase("Profesor")) {
                	
                    nuevoUsuario = new Profesor(user, correo, contraseña);
                } else {
                    System.out.println("Tipo de usuario no reconocido: " + tipoUsuario);
                    
                    continue;
                }
                
                usuarios.add(nuevoUsuario);
                usuariosCargados++;
            }
        }
        System.out.println("Usuarios cargados desde el archivo CSV.");
        
    } catch (IOException e) {
    	
        System.out.println("Error al cargar usuarios: " + e.getMessage());
    }
    return usuariosCargados;
}

//Para que el profesor agruegue su feedback

public static void agregarFeedbackActividad() {
	
    System.out.print("Seleccione el título del Learning Path: ");
    
    String titulo = scanner.nextLine();
    
    LearningPath lp = buscarLearningPath(titulo);

    if (lp != null) {
    	
        System.out.print("Seleccione la actividad para dar feedback: ");
        
        String actividadTitulo = scanner.nextLine();
        
        Actividad actividadSeleccionada = lp.buscarActividad(actividadTitulo);

        if (actividadSeleccionada != null) {
        	
            double rating = 0.0;
            
            boolean inputValido = false;

            
            while (!inputValido) {
                try {
                    System.out.print("Ingrese el rating (1 a 5): ");
                    rating = scanner.nextDouble();
                    
                    scanner.nextLine(); 
                    
                    if (rating >= 0.0 && rating <= 5.0) {
                    	
                        inputValido = true;
                        
                    } else {
                    	
                        System.out.println("El rating debe estar entre 1 y 5 Inténtelo nuevamente.");
                    }
                } catch (InputMismatchException e) {
                	
                    System.out.println("Error: debe ingresar un número válido. Inténtelo nuevamente.");
                    
                    scanner.next(); 
                }
            }

            
            System.out.print("Ingrese el comentario: ");
            
            String comentario = scanner.nextLine();

            // Crear el feedback y agregarlo
            Feedback feedback = new FeedbackEstudiante(rating, comentario);
            
            actividadSeleccionada.agregarFeedback(feedback);

            System.out.println("Feedback agregado con éxito.");
        } else {
        	
            System.out.println("Actividad no encontrada.");
            
        }
    } else {
        System.out.println("Learning Path no encontrado.");
    }
}

//Para asignar un prerrequisito pero es mejor en base al titulo de la actividad

public static void asignarPrerrequisito(Actividad actividad, LearningPath learningPath, String string) {
	
    System.out.println("Actividades actuales en el Learning Path:");
    
    for (Actividad act : learningPath.getActividades()) {
    	
        System.out.println("- " + act.getTitulo());
    }

    System.out.print("Escriba el título de la actividad que será el prerrequisito (o deje en blanco si no aplica): ");
    
    String prerrequisitoTitulo = scanner.nextLine();

    if (!prerrequisitoTitulo.isBlank()) {
    	
        Actividad prerrequisito = learningPath.buscarActividad(prerrequisitoTitulo);
        
        if (prerrequisito != null) {
        	
            actividad.setPrerequisito(prerrequisito);
            
            System.out.println("Prerrequisito asignado correctamente.");
            
        } else {
            System.out.println("Actividad prerrequisito no encontrada.");
            
        }
    } else {
        System.out.println("No se asignó un prerrequisito.");
        
    }
}

//El estudiante puede ver el los feedbacks que le puso el profesor
public static void verFeedbacksEstudiante() {
	
    if (usuarioAutenticado instanceof Estudiante) {
    	
        Estudiante estudiante = (Estudiante) usuarioAutenticado;
        
        estudiante.verFeedbacks(null); 
        
    } else {
    	
        System.out.println("Esta opción solo está disponible para estudiantes.");
    }
}

public static List<Usuario> getUsers() {
    return usuarios;
}

// ES EL RATING DE ESTUDIANTE SOLO ESTUDIANTE
public static void dejarRatingEstudiante() {
	
    if (!(usuarioAutenticado instanceof Estudiante)) {
    	
        System.out.println("Solo los estudiantes pueden dejar un rating.");
        return;
    }

    Estudiante estudiante = (Estudiante) usuarioAutenticado;
    
    listarLearningPaths(); 

    System.out.print("Seleccione el título del Learning Path para dejar un rating: ");
    
    String titulo = scanner.nextLine();
    
    LearningPath learningPath = buscarLearningPath(titulo);
    

    if (learningPath != null) {
        System.out.print("Ingrese su rating para el Learning Path (1 a 5): ");
        
        double rating = scanner.nextDouble();
        
        scanner.nextLine(); 

        learningPath.agregarFeedback(new FeedbackEstudiante(rating, "Reseña de estudiante"));
        
        System.out.println("Gracias por calificar el Learning Path.");
    } else {
        System.out.println("Learning Path no encontrado.");
    }
}
public static List<LearningPath> getLearningPaths() {
    return learningPaths;
}

public static Map<String, Integer> obtenerActividadesRealizadasPorDia() {
    Map<String, Integer> actividadesPorDia = new HashMap<>();

    for (LearningPath lp : learningPaths) { // Iterar sobre los estudiantes
        for (Actividad actividad : lp.getActividades()) {
            String fecha = lp.obtenerFechaCompletada(actividad); // Obtener la fecha de finalización
            actividadesPorDia.put(fecha, actividadesPorDia.getOrDefault(fecha, 0) + 1);
        }
    }

    return actividadesPorDia;
}

public static List<Estudiante> getEstudiantes() {
    // Filtrar usuarios para devolver solo los estudiantes
    return usuarios.stream()
        .filter(u -> u instanceof Estudiante) // Asegúrate de que 'usuarios' contiene objetos Usuario
        .map(u -> (Estudiante) u) // Convertir de Usuario a Estudiante
        .collect(Collectors.toList());
}
}
