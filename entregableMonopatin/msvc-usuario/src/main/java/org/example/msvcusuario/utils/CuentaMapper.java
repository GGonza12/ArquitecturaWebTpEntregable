package org.example.msvcusuario.utils;

import org.example.msvcusuario.dto.CuentaDTO;
import org.example.msvcusuario.model.Cuenta;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {
    private final UsuarioMapper usuarioMapper;

    public CuentaMapper(@Lazy UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public CuentaDTO toDTO(Cuenta c) {
        return new CuentaDTO(
                c.getId(),
                c.getFondos(),
                c.isDeshabilitada(),
                c.getPlan(),
                c.getFechaRegistro(),
                c.getUsuarios() == null ? java.util.List.of() : c.getUsuarios().stream().map(usuarioMapper::toDTOSimple).toList()
        );
    }

    public Cuenta toEntity(CuentaDTO dto) {
        // No mapear usuarios aquí para evitar recursión y porque mapper no debe acceder a repositorios.
        Cuenta c = new Cuenta(dto.getFondos(), dto.isDeshabilitada(), dto.getPlan(), dto.getFechaRegistro(), java.util.List.of());
        if (dto.getId() != null) {
            c.setId(dto.getId());
        }
        return c;
    }

    public void update(CuentaDTO dto, Cuenta c) {
        c.setFondos(dto.getFondos());
        c.setDeshabilitada(dto.isDeshabilitada());
        c.setPlan(dto.getPlan());
        c.setFechaRegistro(dto.getFechaRegistro());
        // No actualizar la lista de usuarios desde el mapper para evitar recursión y dejar
        // la gestión de relaciones al servicio (que puede resolver entidades por id).
    }

}