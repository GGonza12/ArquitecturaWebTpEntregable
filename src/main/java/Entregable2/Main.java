package Entregable2;

import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Repository.CarreraRepositoryImpl;
import Entregable2.Repository.EstudianteCarreraRepository;
import Entregable2.Repository.EstudianteCarreraRepositoryImpl;
import Entregable2.Repository.EstudianteRepositoryImpl;
import Entregable2.utils.HelperMySQL;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CarreraRepositoryImpl cr = new CarreraRepositoryImpl();
        EstudianteRepositoryImpl ed = new EstudianteRepositoryImpl();
        EstudianteCarreraRepository edc = new EstudianteCarreraRepositoryImpl();

        HelperMySQL helper = new HelperMySQL();
        //helper.dropTables();
        helper.populateDB();

        // a) Dar de alta un estudiante
        System.out.println("a) Dar de alta un estudiante:");
        Estudiante juan = new Estudiante(431312312,"Juan","XDDDDDDD",23,"Masculino","Guaralaja",323213);
        ed.darDeAltaEstudiante(juan);
        System.out.println("Estudiante creado: " + juan);

        // b) Matricular un estudiante en una carrera
        System.out.println("\nb) Matricular un estudiante en una carrera:");
        Carrera tudai = cr.buscarPorNombre("TUDAI");
        LocalDate hoy = LocalDate.now();
        edc.matricularEstudiante(juan, tudai, hoy);
        System.out.println("Estudiante " + juan.getNombre() + " matriculado en " + tudai.getCarrera() + " el " + hoy);

        // c) Recuperar todos los estudiantes, ordenados por apellido
        System.out.println("\nc) Todos los estudiantes ordenados por apellido:");
        ed.obtenerEstudiantesPorApellido().forEach(System.out::println);
        System.out.println("Cantidad: "+ed.obtenerEstudiantesPorApellido().size());

        // d) Recuperar un estudiante por número de libreta universitaria
        System.out.println("\nd) Estudiante por número de libreta universitaria:");
        System.out.println(ed.obtenerEstudiantePorNumeroLibreta(323213));

        // e) Recuperar todos los estudiantes por género
        System.out.println("\ne) Estudiantes por género 'Masculino':");
        ed.FindByGender("Masculino").forEach(System.out::println);
        System.out.println("Cantidad: "+ed.FindByGender("Masculino").size());

        // f) Carreras con estudiantes inscriptos, ordenadas por cantidad
        System.out.println("\nf) Carreras con estudiantes inscriptos (ordenadas por cantidad):");
        cr.obtenerCarrerasConEstudiantesInscriptos().forEach(System.out::println);
        System.out.println("Cantidad: "+cr.obtenerCarrerasConEstudiantesInscriptos().size());

        // g) Estudiantes de una carrera filtrados por ciudad
        System.out.println("\ng) Estudiantes de '" + tudai.getCarrera() + "' en ciudad 'Rauch':");
        edc.getEstudiantesCarreraCiudad(tudai, "Rauch").forEach(System.out::println);
        System.out.println("\n3) Cantidad de Estudiantes: "+  edc.getEstudiantesCarreraCiudad(tudai, "Rauch").size());

        // 3) Reporte de carreras: inscriptos y egresados por año
        System.out.println("\n3) Cantidad de reportes: "+ edc.getReporteCarreras().size());
        System.out.println("\n3) Reporte de carreras (inscriptos y egresados por año):");
        edc.getReporteCarreras().forEach(System.out::println);
    }
}
