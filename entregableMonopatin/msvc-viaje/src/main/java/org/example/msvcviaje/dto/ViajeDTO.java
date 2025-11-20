package org.example.msvcviaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msvcviaje.model.Pausa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViajeDTO {
    private Long idUsuario;
    private Long idMonopatin;
    private float kmRecorridos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date fechaFin;

    private List<Pausa> pausas = new ArrayList<>();
    private Double  costoViaje;

    public ViajeDTO(Long idUsuario, Long idMonopatin){
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.kmRecorridos = 0;
        this.fechaInicio = new Date();
        this.pausas = new ArrayList<>();

    }
}
