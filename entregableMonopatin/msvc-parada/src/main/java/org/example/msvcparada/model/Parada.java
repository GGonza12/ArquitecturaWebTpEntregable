package org.example.msvcparada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombreParada;
    @Column
    private double latitud;
    @Column
    private double longitud;

    public Parada(String nombreParada, double latitud, double longitud) {
        this.nombreParada = nombreParada;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
