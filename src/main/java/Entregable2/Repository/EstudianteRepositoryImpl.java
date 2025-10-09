package Entregable2.Repository;

import Entregable2.DTO.EstudianteDTO;
import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Estudiante;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {


    @Override
    public void darDeAltaEstudiante(Estudiante e) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }



    @Override
    public List<EstudianteDTO> obtenerEstudiantesPorApellido() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        String jpql ="SELECT new Entregable2.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad,e.genero,e.ciudad,e.lu)" +
                " FROM Estudiante e ORDER BY e.apellido ASC";
        List<EstudianteDTO> res = em.createQuery(jpql, EstudianteDTO.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorNumeroLibreta(int lu) {
        String jpql = "SELECT new Entregable2.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad,e.genero,e.ciudad,e.lu) " +
                "FROM Estudiante e WHERE e.lu = :eLu";
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        EstudianteDTO dto = em.createQuery(jpql,EstudianteDTO.class).setParameter("eLu",lu).getSingleResult();
        em.getTransaction().commit();
        em.close();
        return dto;
    }

    @Override
    public List<EstudianteDTO> FindByGender(String genero) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql ="SELECT new Entregable2.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad,e.genero,e.ciudad,e.lu)" +
                " FROM Estudiante e WHERE e.genero LIKE :genero";
        em.getTransaction().begin();
        List<EstudianteDTO> res = em.createQuery(jpql, EstudianteDTO.class).setParameter("genero",genero).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;
    }
}
