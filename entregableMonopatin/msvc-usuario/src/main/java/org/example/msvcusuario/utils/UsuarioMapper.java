package org.example.msvcusuario.utils;

import lombok.NoArgsConstructor;
import org.example.msvcusuario.dto.UsuarioDTO;
import org.example.msvcusuario.dto.UsuarioSimpleDTO;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.model.Rol;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario s) {
        return new UsuarioDTO(s.getNombre(),s.getApellido(),s.getEmail(),s.getNroCelular(),s.getLatitud(),s.getLongitud(),s.getRol(),s.getCuentas(),s.getMonopatinesUsados());
    }

    public UsuarioSimpleDTO toDTOSimple(Usuario s) {
        Rol rol = s.getRol();
        return new UsuarioSimpleDTO(s.getId(),s.getNombre(),s.getApellido(),s.getEmail(),rol);
    }

    public Usuario toEntity(UsuarioDTO dto) {
        return new Usuario(dto.getNombre(),dto.getApellido(),dto.getEmail(),dto.getNroCelular(),dto.getRol(),dto.getLatitud(),dto.getLongitud(),dto.getCuentas(),dto.getMonopatinesUsados());
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
        s.setCuentas(dto.getCuentas());
    }
}
