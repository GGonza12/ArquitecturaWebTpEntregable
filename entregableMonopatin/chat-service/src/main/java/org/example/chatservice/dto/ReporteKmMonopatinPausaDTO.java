package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKmMonopatinPausaDTO {
    private Long idMonopatin;
    private float kmRecorridos;
    private int tiempoUsoPausa;
}
