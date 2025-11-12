package org.example.msvcreporte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioViajeDTO {
    private Long idUsuario;
    private long cantidadViajes;
}