package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private Long idUsuario;
    private Long idMonopatin;
    private float kmRecorridos;
    private String fechaInicio;
    private String fechaFin;
    private List<Object> pausas = new ArrayList<>();
    private Double costoViaje;
    private long minutosPausa;
}
