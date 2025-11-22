package org.example.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvcusuario.model.Rol;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String nombre;
    private String apellido;
    private String userName;
    private String password;
    private String email;
    private long nroCelular;
    private double latitud;
    private double longitud;
    private Rol rol;
}