package Entregable2.Repository;

import Entregable2.DTO.ReporteCarreraDTO;
import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface EstudianteCarreraRepository {
    void matricularEstudiante(Estudiante e, Carrera c, LocalDate inscripcion);
    List<Estudiante> getEstudiantesCarreraCiudad(Carrera c,String ciudad);
    List<ReporteCarreraDTO> getReporteCarreras();

}
