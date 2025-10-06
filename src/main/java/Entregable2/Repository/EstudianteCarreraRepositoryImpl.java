package Entregable2.Repository;

import Entregable2.DTO.EstudianteDTO;
import Entregable2.DTO.ReporteDTO;
import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Model.EstudianteCarrera;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {


    @Override
    public void matricularEstudiante(Estudiante e, Carrera c, int inscripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        EstudianteCarrera ec = new EstudianteCarrera(inscripcion,e,c);
        em.persist(ec);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<Estudiante> getEstudiantesCarreraCiudad(Carrera c, String ciudad) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "SELECT e FROM Estudiante e JOIN e.estudianteCarreras ec WHERE ec.carrera.id_carrera = :idCarrera AND e.ciudad = :ciudad";
        em.getTransaction().begin();
        List<Estudiante> res = em.createQuery(jpql,Estudiante.class).setParameter("idCarrera",c.getId_carrera()).setParameter("ciudad",ciudad).getResultList();
        em.getTransaction().commit();
        em.close();

        return res;
    }

    @Override
    public ArrayList<ReporteDTO> getReportes() {
        return null;
    }
}
