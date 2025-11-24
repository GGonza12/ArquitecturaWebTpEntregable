package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKmMonopatinDTO {
    private Long idMonopatin;
    private float kmRecorridos;
}
