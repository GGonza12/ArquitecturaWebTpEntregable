package org.example.msvcfacturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private String id;
    private Long idUsuario;
    private Long idMonopatin;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private float kmRecorridos;
    private long minutosPausa;
}
