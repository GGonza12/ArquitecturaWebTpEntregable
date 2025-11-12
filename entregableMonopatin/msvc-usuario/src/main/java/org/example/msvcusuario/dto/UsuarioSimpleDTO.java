package org.example.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvcusuario.model.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimpleDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Rol rol;
}