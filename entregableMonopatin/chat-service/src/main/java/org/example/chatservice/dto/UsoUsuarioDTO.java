package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsoUsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private Rol rol;
    private double kmRecorridos;
    private long tiempoMinutos;
}
