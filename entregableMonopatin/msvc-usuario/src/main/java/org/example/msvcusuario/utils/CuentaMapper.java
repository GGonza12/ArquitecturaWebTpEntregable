package org.example.msvcusuario.utils;

import lombok.NoArgsConstructor;
import org.example.msvcusuario.dto.CuentaDTO;
import org.example.msvcusuario.model.Cuenta;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CuentaMapper {
    public CuentaDTO toDTO(Cuenta c) {
        return new CuentaDTO(c.getFondos(),c.isDeshabilitada(),c.getPlan(),c.getFechaRegistro(),c.getUsuarios());
    }

    public Cuenta toEntity(CuentaDTO dto) {
        return new Cuenta(dto.getFondos(),dto.isDeshabilitada(),dto.getPlan(),dto.getFechaRegistro(),dto.getUsuarios());
    }

    public void update(CuentaDTO dto, Cuenta c) {
        c.setFondos(dto.getFondos());
        c.setDeshabilitada(dto.isDeshabilitada());
        c.setPlan(dto.getPlan());
        c.setFechaRegistro(dto.getFechaRegistro());
        c.setUsuarios(dto.getUsuarios());
    }

}