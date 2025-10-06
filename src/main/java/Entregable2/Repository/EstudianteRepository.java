package Entregable2.Repository;

import Entregable2.DTO.CarreraDTO;
import Entregable2.DTO.EstudianteDTO;
import Entregable2.Model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public interface EstudianteRepository {
    void darDeAltaEstudiante(Estudiante e);
    List<EstudianteDTO> obtenerEstudiantesPorApellido();
    EstudianteDTO obtenerEstudiantePorNumeroLibreta(int lu);
    List<EstudianteDTO> FindByGender(String genero);

}
