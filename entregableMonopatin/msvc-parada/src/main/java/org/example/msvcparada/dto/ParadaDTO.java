package org.example.msvcparada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    private String nombreParada;
    private double latitud;
    private double longitud;
}
