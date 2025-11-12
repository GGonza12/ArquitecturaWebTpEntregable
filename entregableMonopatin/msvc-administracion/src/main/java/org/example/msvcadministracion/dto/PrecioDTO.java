package org.example.msvcadministracion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecioDTO {
    private double precio;
    private double precioPenalizacion;
    private Timestamp fechaVigencia;
}
