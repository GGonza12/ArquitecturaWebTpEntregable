package org.example.msvcviaje.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Pausa {
    private Timestamp fechaInicio;
    private Timestamp fechaFin;


    public Pausa(Timestamp fechaInicio, Timestamp fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Pausa() {
        this.fechaInicio = Timestamp.valueOf(LocalDateTime.now());
    }


}
