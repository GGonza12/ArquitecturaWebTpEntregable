package Entregable2.utils;

import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Model.EstudianteCarrera;
import Entregable2.Model.InscriptionId;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;

import java.io.FileReader;
import java.time.LocalDate;

public class HelperMySQL {

    public void populateDB(){
        EntityManager em = JPAUtil.getEntityManager();
        String ruta = "src/main/java/entregable2/resources/";
        System.out.println("Populating DB...");
        insertAllCarreras(ruta+"carreras.csv");

        insertAllEstudiantes(ruta+"estudiantes.csv");

        insertAllEstudiantesCarreras(ruta+"estudianteCarrera.csv");

        em.close();
    }

    public void dropTables() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS EstudianteCarrera").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS Estudiante").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS Carrera").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    private void insertAllEstudiantes(String rutaArchivo){
        EntityManager em = JPAUtil.getEntityManager();
        try(CSVReader reader = new CSVReader(new FileReader(rutaArchivo))){
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Integer dni = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                String apellido = linea[2];
                int edad = Integer.parseInt(linea[3]);
                String genero = linea[4];
                String ciudad = linea[5];
                int lu = Integer.parseInt(linea[6]);
                Estudiante est = new Estudiante(dni,nombre,apellido, edad,genero,ciudad,lu);
                        em.persist(est);
            }
            System.out.println("Estudiantes insertados exitosamente!");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    private void insertAllCarreras(String rutaArchivo){
        EntityManager em = JPAUtil.getEntityManager();
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();
            while ((linea = reader.readNext()) != null) {
                int id_carrera = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                int duracion = Integer.parseInt(linea[2]);
                Carrera carrera = new Carrera(id_carrera, nombre, duracion);
                em.persist(carrera);
            }
            System.out.println("Carreras insertadas exitosamente!");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    private void insertAllEstudiantesCarreras(String rutaArchivo){
        EntityManager em = JPAUtil.getEntityManager();
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();
            while ((linea = reader.readNext()) != null) {
                int id_estudiante = Integer.parseInt(linea[1]);
                int id_carrera = Integer.parseInt(linea[2]);
                LocalDate inscripcion = LocalDate.of(Integer.parseInt(linea[3]), 1, 1);
                LocalDate graduacion = (linea[4].equals("0") || linea[4].isEmpty()) ? null : LocalDate.of(Integer.parseInt(linea[4]), 1, 1);
                int antiguedad = Integer.parseInt(linea[5]);

                Estudiante estudiante = em.find(Estudiante.class, id_estudiante);
                Carrera carrera = em.find(Carrera.class, id_carrera);

                if (estudiante == null || carrera == null) {
                    System.out.println("No se encontró estudiante o carrera para la inscripción: " + id_estudiante + ", " + id_carrera);
                    continue;
                }

                InscriptionId id = new InscriptionId(id_estudiante, id_carrera);
                if (em.find(EstudianteCarrera.class, id) == null) {
                    EstudianteCarrera estCarr = new EstudianteCarrera(id, inscripcion, graduacion, antiguedad, estudiante, carrera);
                    em.persist(estCarr);
                }
            }
            System.out.println("EstudianteCarreras insertadas exitosamente!");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
