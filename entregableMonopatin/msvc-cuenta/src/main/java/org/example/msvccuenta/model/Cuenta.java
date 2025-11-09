package org.example.msvccuenta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double fondos;
    @Column
    private boolean deshabilitada;
    @Column
    private Plan plan;
    @Column
    private Timestamp fechaRegistro;
    @Column
    private List<Long> usuarios;

    public Cuenta(Plan plan,long idUsuario) {
        this.plan = plan;
        this.fechaRegistro = new Timestamp(System.currentTimeMillis());
        this.usuarios=new ArrayList<>();
        this.usuarios.add(idUsuario);
        this.deshabilitada=false;
        this.fondos=0;
    }

    public Cuenta(long idUsuario) {
        this.plan = Plan.PLAN_BASICO;
        this.fechaRegistro = new Timestamp(System.currentTimeMillis());
        this.usuarios=new ArrayList<>();
        this.usuarios.add(idUsuario);
        this.deshabilitada=false;
        this.fondos=0;
    }

    public Cuenta(double fondos, boolean deshabilitada, Plan plan, Timestamp fechaRegistro, List<Long> usuarios) {
        this.fondos = fondos;
        this.deshabilitada = deshabilitada;
        this.plan = plan;
        this.fechaRegistro = fechaRegistro;
        this.usuarios = new ArrayList<>(usuarios);
    }
}
