package Entregable2.Repository;

import Entregable2.DTO.CarreraDTO;
import Entregable2.DTO.CarreraInscriptosDTO;
import Entregable2.DTO.EstudianteDTO;
import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Carrera;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    //F) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @Override
    public List<CarreraInscriptosDTO> obtenerCarrerasConEstudiantesInscriptos() {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql ="SELECT new Entregable2.DTO.CarreraInscriptosDTO(c.carrera,COUNT(ec.estudiante)) FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c.carrera HAVING COUNT(ec.estudiante)>0  ORDER BY COUNT(ec.estudiante) DESC ";
        em.getTransaction().begin();
        List<CarreraInscriptosDTO> res = em.createQuery(jpql, CarreraInscriptosDTO.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;

    }

    @Override
    public Carrera buscarPorNombre(String nombreCarrera) {
        EntityManager em = JPAUtil.getEntityManager();
        Carrera carrera = null;
        try {
            carrera = em.createQuery(
                            "SELECT c FROM Carrera c WHERE c.carrera = :nombre", Carrera.class)
                    .setParameter("nombre", nombreCarrera)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró la carrera con nombre: " + nombreCarrera);
        } finally {
            em.close();
        }
        return carrera;
    }
}
