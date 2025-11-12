package org.example.msvcmonopatin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private double latitud;
    @Column(nullable = false)
    private double longitud;
    @Column
    private EstadoMonopatin estado;
    @Column
    private float kmRecorridos;
    @Column
    private int tiempoDeUso;


    public Monopatin(double latitud, double longitud, EstadoMonopatin estado, float kmRecorridos, int tiempoDeUso) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.kmRecorridos = kmRecorridos;
        this.tiempoDeUso = tiempoDeUso;
    }
}


