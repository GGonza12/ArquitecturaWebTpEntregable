package org.example.msvcusuario.utils;

import lombok.NoArgsConstructor;
import org.example.msvcusuario.dto.CuentaDTO;
import org.example.msvcusuario.dto.UsuarioDTO;
import org.example.msvcusuario.dto.UsuarioSimpleDTO;
import org.example.msvcusuario.model.Cuenta;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.model.Rol;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UsuarioMapper {

    private final CuentaMapper cuentaMapper;

    public UsuarioMapper(CuentaMapper cuentaMapper) {
        this.cuentaMapper = cuentaMapper;
    }

    public UsuarioDTO toDTO(Usuario s) {


        List<CuentaDTO> cuentasDTO = s.getCuentas() != null
                ? s.getCuentas().stream()
            .map(c -> new CuentaDTO(
                c.getId(),
                c.getFondos(),
                c.isDeshabilitada(),
                c.getPlan(),
                c.getFechaRegistro(),
                c.getUsuarios() == null ? java.util.List.of() : c.getUsuarios().stream().map(this::toDTOSimple).toList()

            ))
                .toList()
                : List.of();
        return new UsuarioDTO(s.getId(), s.getNombre(), s.getApellido(), s.getEmail(), s.getNroCelular(), s.getLatitud(), s.getLongitud(), s.getRol(), cuentasDTO, s.getMonopatinesUsados());
    }

    public UsuarioSimpleDTO toDTOSimple(Usuario s) {
        Rol rol = s.getRol();
        return new UsuarioSimpleDTO(s.getId(),s.getNombre(),s.getApellido(),s.getEmail(),rol);
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario u = new Usuario(dto.getNombre(), dto.getApellido(), dto.getEmail(), dto.getNroCelular(), dto.getRol(), dto.getLatitud(), dto.getLongitud(), dto.getCuentas() == null ? new ArrayList<>() : dto.getCuentas().stream().map(cuentaMapper::toEntity).collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new)), dto.getMonopatinesUsados());
        if (dto.getId() != null) {
            u.setId(dto.getId());
        }
        return u;
    }

    public void update(UsuarioDTO dto, Usuario s) {
        s.setLatitud(dto.getLatitud());
        s.setLongitud(dto.getLongitud());
        s.setRol(dto.getRol());
        s.setNombre(dto.getNombre());
        s.setApellido(dto.getApellido());
        s.setEmail(dto.getEmail());
        s.setNroCelular(dto.getNroCelular());
        s.setMonopatinesUsados(dto.getMonopatinesUsados());
        // Actualizar cuentas solo si la lista viene presente (no nula).
        if (dto.getCuentas() != null) {
            List<Cuenta> cuentas = dto.getCuentas().stream()
                .filter(java.util.Objects::nonNull)
                .map(cuentaMapper::toEntity)
                .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
            s.setCuentas(cuentas);
        }

    }

}
