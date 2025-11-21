package org.example.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvcusuario.model.Plan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.example.msvcusuario.dto.UsuarioSimpleDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long id;
    private double fondos;
    private boolean deshabilitada;
    private Plan plan;
    private Timestamp fechaRegistro;
    private List<UsuarioSimpleDTO> usuarios;


}