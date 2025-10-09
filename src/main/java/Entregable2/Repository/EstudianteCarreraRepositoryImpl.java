package Entregable2.Repository;

import Entregable2.DTO.ReporteCarreraDTO;
import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Model.EstudianteCarrera;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {


    @Override
    public void matricularEstudiante(Estudiante e, Carrera c, LocalDate inscripcion) {
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
    public List<ReporteCarreraDTO> getReporteCarreras() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        List<ReporteCarreraDTO> resultado = em.createQuery(
                "SELECT new Entregable2.DTO.ReporteCarreraDTO(" +
                "c.carrera, YEAR(ec.inscripcion), COUNT(ec), " +
                "SUM(CASE WHEN ec.graduacion IS NOT NULL THEN 1 ELSE 0 END)) " +
                "FROM EstudianteCarrera ec JOIN ec.carrera c " +
                "GROUP BY c.carrera, YEAR(ec.inscripcion) " +
                "ORDER BY c.carrera ASC, YEAR(ec.inscripcion) ASC",
                ReporteCarreraDTO.class
        ).getResultList();
        em.getTransaction().commit();
        em.close();
        return resultado;

    }
}
