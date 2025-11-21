package org.example.msvcviaje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Document(collection  = "viajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    @Id
    private String id;
    private Long idUsuario;
    private Long idMonopatin;
    private float kmRecorridos;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Pausa> pausas;
    private Double costoViaje;

    public Viaje(Long idUsuario,Long idMonopatin,float kmRecorridos,Date fechaInicio,Date fechaFin,List<Pausa> pausas, Double costoViaje) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.kmRecorridos = kmRecorridos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausas = new ArrayList<>(pausas);
        this.costoViaje=costoViaje;
    }

    public Viaje(Long idUsuario,Long idMonopatin) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.kmRecorridos = 0;
        this.fechaInicio = new Date();
        this.pausas = new ArrayList<>();
        this.costoViaje = 0.0;
    }

    public long getTotalMinutosPausa() {
        if (pausas == null || pausas.isEmpty()) {
            return 0;
        }

        long totalMillis = 0;

        for (Pausa p : pausas) {
            if (p.getFechaInicio() != null && p.getFechaFin() != null) {
                totalMillis += p.getFechaFin().getTime() - p.getFechaInicio().getTime();
            }
        }

        return TimeUnit.MILLISECONDS.toMinutes(totalMillis);
    }

    public long getDuracionEnMinutos() {
        if (fechaInicio == null || fechaFin == null) return 0;
        long diff = fechaFin.getTime() - fechaInicio.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diff);
    }


}
