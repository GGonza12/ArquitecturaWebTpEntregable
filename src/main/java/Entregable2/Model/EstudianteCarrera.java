package Entregable2.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EstudianteCarrera")
public class EstudianteCarrera {
    @EmbeddedId
    private InscriptionId id;
    @Column
    private LocalDate inscripcion;

    @Column
    private LocalDate graduacion;

    @Column
    private int antiguedad ;
    @MapsId("dni")
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    @MapsId("id_carrera")
    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    public EstudianteCarrera() {}
    public EstudianteCarrera(LocalDate inscripcion, Estudiante estudiante, Carrera carrera) {
        this.id = new InscriptionId(estudiante.getDni(), carrera.getId_carrera());
        this.inscripcion = inscripcion;
        this.graduacion = null;
        this.antiguedad = 0;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(LocalDate inscripcion, LocalDate Graduacion, Estudiante estudiante, Carrera carrera) {
        this.id = new InscriptionId(estudiante.getDni(), carrera.getId_carrera());
        this.inscripcion = inscripcion;
        this.graduacion = Graduacion;
        this.antiguedad = 0;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(InscriptionId id, LocalDate inscripcion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = new InscriptionId(estudiante.getDni(), carrera.getId_carrera());
        this.inscripcion = inscripcion;
        this.graduacion = null;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public EstudianteCarrera(InscriptionId id,LocalDate inscripcion, LocalDate graduacion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = id;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public InscriptionId getId() {
        return id;
    }
    public LocalDate getInscripcion() {
        return inscripcion;
    }
    public void setInscripcion(LocalDate inscripcion) {
        this.inscripcion = inscripcion;
    }
    public LocalDate getGraduacion() {
        return graduacion;
    }
    public void setGraduacion(LocalDate graduacion) {
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
