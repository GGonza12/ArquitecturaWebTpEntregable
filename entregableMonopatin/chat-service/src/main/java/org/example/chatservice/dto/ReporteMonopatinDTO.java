package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteMonopatinDTO {
    private double latitud;
    private double longitud;
    private EstadoMonopatin estado;
    private float kmRecorridos;
    private int tiempoDeUso;
}
