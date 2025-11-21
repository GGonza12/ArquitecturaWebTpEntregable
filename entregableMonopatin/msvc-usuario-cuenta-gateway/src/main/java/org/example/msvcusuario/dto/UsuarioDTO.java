package org.example.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvcusuario.model.Cuenta;
import org.example.msvcusuario.model.Rol;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private long nroCelular;
    private double latitud;
    private double longitud;
    private Rol rol;
    private List<CuentaDTO> cuentas = new ArrayList<>();
    private List<Long> monopatinesUsados = new ArrayList<>();


    public UsuarioDTO(String nombre, String apellido, String email, long nroCelular, double latitud, double longitud) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nroCelular = nroCelular;
        this.latitud = latitud;
        this.longitud = longitud;
        this.rol=Rol.ROL_USUARIO;
        this.cuentas=new ArrayList<>();
        this.monopatinesUsados=new ArrayList<>();
    }


}
