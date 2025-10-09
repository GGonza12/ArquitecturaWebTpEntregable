package Entregable2.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Carrera")

public class Carrera {
    @Id
    @Column(name = "id_carrera")
    private Integer id_carrera;
    @Column(name = "carrera", length = 50, nullable = false)
    private String carrera;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes;

    public Carrera() {
        this.estudiantes = new ArrayList<>();
    }
    public Carrera( String carrera,int duracion) {
        this.id_carrera = null;
        this.carrera = carrera;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<>();
    }

    public Carrera(Integer id_carrera,String carrera, int duracion) {
        this.id_carrera = id_carrera;
        this.carrera = carrera;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<>();
    }
    public Integer getId_carrera() {
        return id_carrera;
    }
    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public ArrayList<EstudianteCarrera> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

}
