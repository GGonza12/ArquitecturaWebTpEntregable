package org.example.msvcparada.utils;

import lombok.NoArgsConstructor;
import org.example.msvcparada.dto.ParadaDTO;
import org.example.msvcparada.model.Parada;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ParadaMapper {
    public ParadaDTO toDTO(Parada p) {
        return new ParadaDTO(p.getNombreParada(),p.getLatitud(),p.getLongitud());
    }

    public Parada toEntity( ParadaDTO dto) {
        return new Parada(dto.getNombreParada(),dto.getLatitud(),dto.getLongitud());
    }

    public void update( ParadaDTO dto, Parada c) {
        c.setNombreParada(dto.getNombreParada());
        c.setLatitud(dto.getLatitud());
        c.setLongitud(dto.getLongitud());
    }
}
