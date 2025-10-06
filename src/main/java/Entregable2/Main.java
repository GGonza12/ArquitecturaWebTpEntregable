package Entregable2;

import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Repository.CarreraRepositoryImpl;
import Entregable2.Repository.EstudianteCarreraRepository;
import Entregable2.Repository.EstudianteCarreraRepositoryImpl;
import Entregable2.Repository.EstudianteRepositoryImpl;
import Entregable2.utils.HelperMySQL;

public class Main {
    public static void main(String[] args) {
        CarreraRepositoryImpl cr = new CarreraRepositoryImpl();
        EstudianteRepositoryImpl ed = new EstudianteRepositoryImpl();
        EstudianteCarreraRepository edc = new EstudianteCarreraRepositoryImpl();
        /*
        cr.insertarDesdeCSV("src/main/java/entregable2/resources/carreras.csv");
        ed.insertarDesdeCSV("src/main/java/entregable2/resources/estudiantes.csv");
        edc.insertarDesdeCSV("src/main/java/entregable2/resources/estudianteCarrera.csv");
        */
        HelperMySQL helper = new HelperMySQL();
        //helper.dropTables();
        //helper.populateDB();
        //a) dar de alta un estudiante
        Estudiante juan = new Estudiante(431312312,"Juan","XDDDDDDD",23,"Masculino","Guaralaja",323213);
        //ed.darDeAltaEstudiante(juan);
        //b) matricular un estudiante en una carrera
        Carrera tudai =cr.buscarPorNombre("TUDAI");
        //edc.matricularEstudiante(juan,tudai,2025);

        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        System.out.println(ed.obtenerEstudiantesPorApellido());
        //d) recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println(ed.obtenerEstudiantePorNumeroLibreta(323213));
        //e) recuperar todos los estudiantes, en base a su género.
        System.out.println(ed.FindByGender("Masculino"));
        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        System.out.println(cr.obtenerCarrerasConEstudiantesInscriptos());
        //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        System.out.println("G:");
        System.out.println(edc.getEstudiantesCarreraCiudad(tudai,"Rauch"));
        System.out.println("3: ");
        System.out.println(edc.getReporteCarreras());
        System.out.println(edc.getReporteCarreras().size());


    }
}
