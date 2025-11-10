package org.example.msvcviaje.utils;

import lombok.NoArgsConstructor;
import org.example.msvcviaje.dto.ViajeDTO;
import org.example.msvcviaje.model.Viaje;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ViajeMapper {

    public ViajeDTO toDTO(Viaje v) {
        return new ViajeDTO(v.getIdUsuario(),v.getIdMonopatin(),v.getKmRecorridos(),v.getFechaInicio(),v.getFechaFin(),v.getPausas());
    }

    public Viaje toEntity(ViajeDTO dto) {
        return new Viaje(dto.getIdUsuario(),dto.getIdMonopatin(),dto.getKmRecorridos(),dto.getFechaInicio(),dto.getFechaFin(),dto.getPausas());
    }

    public void update(ViajeDTO dto, Viaje m) {
      m.setIdUsuario(dto.getIdUsuario());
      m.setIdMonopatin(dto.getIdMonopatin());
      m.setKmRecorridos(dto.getKmRecorridos());
      m.setFechaInicio(dto.getFechaInicio());
      m.setFechaFin(dto.getFechaFin());
      m.setPausas(dto.getPausas());

    }

}
