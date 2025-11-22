package org.example.msvcreporte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long id;
    private double fondos;
    private boolean deshabilitada;
    private Plan plan;
    private Timestamp fechaRegistro;
    private List<UsuarioSimpleDTO> usuarios = new ArrayList<>();


}