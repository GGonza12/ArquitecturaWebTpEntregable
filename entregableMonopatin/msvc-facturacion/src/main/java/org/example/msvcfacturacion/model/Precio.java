package org.example.msvcfacturacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Double precio;
    @Column
    private Double precioPenalizacion;
    @Column
    Timestamp fechaVigencia;


    public Precio(double precio, double precioPenalizacion, Timestamp fechaVigencia) {
        this.precio = precio;
        this.precioPenalizacion = precioPenalizacion;
        this.fechaVigencia = fechaVigencia;
    }
}
