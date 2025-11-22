package org.example.msvcviaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO {
    private double latitud;
    private double longitud;
    private EstadoMonopatin estado;
    private float kmRecorridos;
    private int tiempoDeUso;

}
