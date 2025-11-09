package org.example.msvcmonopatin.utils;

import lombok.NoArgsConstructor;
import org.example.msvcmonopatin.dto.MonopatinDTO;
import org.example.msvcmonopatin.model.Monopatin;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MonopatinMapper {
    
    public MonopatinDTO toDTO(Monopatin m) {
        return new MonopatinDTO(m.getLatitud(),m.getLongitud(),m.getEstado(),m.getKmRecorridos(),m.getTiempoDeUso());
    }

    public Monopatin toEntity(MonopatinDTO dto) {
        return new Monopatin(dto.getLatitud(),dto.getLongitud(),dto.getEstado(),dto.getKmRecorridos(),dto.getTiempoDeUso());
    }

    public void update(MonopatinDTO dto, Monopatin m) {
        m.setLatitud(dto.getLatitud());
        m.setLongitud(dto.getLongitud());
        m.setEstado(dto.getEstado());
        m.setKmRecorridos(dto.getKmRecorridos());
        m.setTiempoDeUso(dto.getTiempoDeUso());
    }
}
