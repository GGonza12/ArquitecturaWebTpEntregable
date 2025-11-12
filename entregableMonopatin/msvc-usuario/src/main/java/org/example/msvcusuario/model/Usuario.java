package org.example.msvcusuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private long nroCelular;
    @Column
    private Rol rol;
    @Column
    private double latitud;
    @Column
    private double longitud;
    @ElementCollection
    @CollectionTable(name = "usuario_cuentas", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "cuenta_id")
    private List<Long> cuentas;
    @ElementCollection
    @CollectionTable(name = "usuario_monopatines", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "monopatin_id")
    private List<Long> monopatinesUsados;


    public Usuario(String nombre, String apellido, String email, long nroCelular, Rol rol, double latitud, double longitud, List<Long> cuentas, List<Long> monopatinesUsados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nroCelular = nroCelular;
        this.rol = rol;
        this.latitud= latitud;
        this.longitud = longitud;
        this.cuentas=new ArrayList<>(cuentas);
        this.monopatinesUsados=new ArrayList<>(monopatinesUsados);
    }
}