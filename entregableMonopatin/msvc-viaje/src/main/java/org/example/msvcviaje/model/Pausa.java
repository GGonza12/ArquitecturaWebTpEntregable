package org.example.msvcviaje.model;


import lombok.Data;

import java.util.Date;

@Data
public class Pausa {
    private Date fechaInicio;
    private Date fechaFin;

    public Pausa(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Pausa() {
        this.fechaInicio = new Date();
    }

}
