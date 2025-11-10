package org.example.msvcviaje.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection  = "viajes")
@Data
@NoArgsConstructor
public class Viaje {
    @Id
    private String id;
    private Long idUsuario;
    private Long idMonopatin;
    private float kmRecorridos;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Pausa> pausas;


    public Viaje(Long idUsuario,Long idMonopatin,float kmRecorridos,Date fechaInicio,Date fechaFin,List<Pausa> pausas) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.kmRecorridos = kmRecorridos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pausas = new ArrayList<>(pausas);
    }

    public Viaje(Long idUsuario,Long idMonopatin) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.kmRecorridos = 0;
        this.fechaInicio = new Date();
        this.pausas = new ArrayList<>();
    }
}
