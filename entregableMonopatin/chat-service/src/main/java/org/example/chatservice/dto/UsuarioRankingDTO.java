package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRankingDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private Rol rol;
    private long cantidadViajes;
}
