package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsoMonopatinDTO {
    private double totalKmRecorridos;
    private long totalTiempoMinutos;
    private List<UsoUsuarioDTO> usuarios;
}
