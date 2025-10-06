package Entregable2.Repository;

import Entregable2.DTO.CarreraInscriptosDTO;
import Entregable2.Model.Carrera;

import java.util.List;

public interface CarreraRepository {

     List<CarreraInscriptosDTO> obtenerCarrerasConEstudiantesInscriptos();
    Carrera buscarPorNombre(String nombre);
}
