package org.example.msvccuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvccuenta.model.Plan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private double fondos;
    private boolean deshabilitada;
    private Plan plan;
    private Timestamp fechaRegistro;
    private List<Long> usuarios;


    //ideal para crear una cuenta nueva
    public CuentaDTO(Plan p,Long usuario){
        this.plan=p;
        this.usuarios=new ArrayList<>();
        this.usuarios.add(usuario);
    }
}
