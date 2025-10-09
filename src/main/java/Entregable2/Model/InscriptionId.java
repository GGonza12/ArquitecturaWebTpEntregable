package Entregable2.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class InscriptionId implements Serializable {
    private Integer dni;
    private Integer id_carrera;

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Integer id_carrera) {
        this.id_carrera = id_carrera;
    }

    public InscriptionId() {
    }

    public InscriptionId(Integer dni, Integer id_carrera) {
        this.dni = dni;
        this.id_carrera = id_carrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscriptionId)) return false;
        InscriptionId inscriptionId = (InscriptionId) o;

        return Objects.equals(id_carrera,inscriptionId.id_carrera) && Objects.equals(dni,inscriptionId.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, id_carrera);
    }


}
