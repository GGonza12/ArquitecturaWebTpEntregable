package Entregable2.Model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Estudiante")
public class Estudiante {
    @Id
    private Integer dni;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 50, nullable = false)
    private String apellido;
    @Column()
    private int edad;
    @Column(length = 50, nullable = false)
    private String genero;
    @Column(length = 50, nullable = false)
    private String ciudad;
    @Column(name = "lu",unique = true)
    private int lu;
    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> estudianteCarreras;

    public Estudiante() {
        this.estudianteCarreras = new ArrayList<>();
    }
    public Estudiante(int dni,String nombre,String apellido,int edad,String genero,String ciudad,int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
        this.estudianteCarreras = new ArrayList<>();

    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public int getLu() {
        return lu;
    }
    public void setLu(int lu) {
        this.lu = lu;
    }

    public  List<EstudianteCarrera> getEstudianteCarreras() {
        return estudianteCarreras;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", lu=" + lu +
                '}';
    }
}
