package Entregable2.utils;

import Entregable2.Factory.JPAUtil;
import Entregable2.Model.Carrera;
import Entregable2.Model.Estudiante;
import Entregable2.Model.EstudianteCarrera;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;

import java.io.FileReader;

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
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    private void insertAllCarreras(String rutaArchivo){
        EntityManager em = JPAUtil.getEntityManager();
        try(CSVReader reader = new CSVReader(new FileReader(rutaArchivo))){
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                //Integer id_carrera = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                int duracion = Integer.parseInt(linea[2]);
                Carrera carr = new Carrera(nombre, duracion);
                em.persist(carr);
            }
            System.out.println("Carreras insertados exitosamente!");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    private void insertAllEstudiantesCarreras(String rutaArchivo){
        EntityManager em = JPAUtil.getEntityManager();
        try(CSVReader reader = new CSVReader(new FileReader(rutaArchivo))){
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                //Integer id = Integer.parseInt(linea[0]);
                int id_estudiante = Integer.parseInt(linea[1]);
                int id_carrera = Integer.parseInt(linea[2]);
                int inscripcion = Integer.parseInt(linea[3]);
                int graduacion = Integer.parseInt(linea[4]);
                int antiguedad = Integer.parseInt(linea[5]);
                Estudiante e = em.find(Estudiante.class, id_estudiante);
                Carrera c = em.find(Carrera.class, id_carrera);
                if(e!=null && c!=null){
                    EstudianteCarrera estCarr = new EstudianteCarrera(inscripcion,graduacion,antiguedad,e,c);
                    em.persist(estCarr);
                }
            }
            System.out.println("estudiantesCarreras  insertados exitosamente!");

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
