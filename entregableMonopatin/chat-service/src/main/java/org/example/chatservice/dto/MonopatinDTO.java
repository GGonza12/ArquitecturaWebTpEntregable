package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO {
    private double latitud;
    private double longitud;
    private EstadoMonopatin estado;
    private float kmRecorridos;
    private int tiempoDeUso;


    public MonopatinDTO(double latitud,double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado= EstadoMonopatin.ESTADO_LIBRE;
        this.kmRecorridos=0;
        this.tiempoDeUso=0;
    }
}


