package Entregable2.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "EstudianteCarrera")
public class EstudianteCarrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private int inscripcion;
    @Column
    private int graduacion ;
    @Column
    private int antiguedad ;
    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "dni")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    private Carrera carrera;

    public EstudianteCarrera() {}
    public EstudianteCarrera(int inscripcion, Estudiante estudiante, Carrera carrera) {
        this.id = null;
        this.inscripcion = inscripcion;
        this.graduacion = 0;
        this.antiguedad = 0;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(int inscripcion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = null;
        this.inscripcion = inscripcion;
        this.graduacion = 0;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(int inscripcion, int graduacion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = null;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(Integer id, int inscripcion, int graduacion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = id;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public int getId() {
        return id;
    }
    public int getInscripcion() {
        return inscripcion;
    }
    public void setInscripcion(int inscripcion) {
        this.inscripcion = inscripcion;
    }
    public int getGraduacion() {
        return graduacion;
    }
    public void setGraduacion(int graduacion) {
        this.graduacion = graduacion;
    }
    public int getAntiguedad() {
        return antiguedad;
    }
    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    public Carrera getCarrera() {
        return carrera;
    }
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }


}
