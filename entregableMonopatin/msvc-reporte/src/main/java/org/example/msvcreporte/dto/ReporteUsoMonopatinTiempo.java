package org.example.msvcreporte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReporteUsoMonopatinTiempo {
    private Long idCuenta;
    private String fechaInicio;
    private String fechaFin;
    private double totalKmRecorridos;
    private long totalTiempoMinutos;
    private List<UsoUsuarioDTO> usuarios;
}
