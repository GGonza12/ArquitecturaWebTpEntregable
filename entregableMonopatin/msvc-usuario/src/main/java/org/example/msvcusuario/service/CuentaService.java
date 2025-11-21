package org.example.msvcusuario.service;

import org.example.msvcusuario.dto.CuentaDTO;
import org.example.msvcusuario.model.Cuenta;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.repository.CuentaRepository;
import org.example.msvcusuario.repository.UsuarioRepository;
import org.example.msvcusuario.utils.CuentaMapper;
import org.example.msvcusuario.utils.UsuarioMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CuentaMapper cuentaMapper;
    private final UsuarioMapper usuarioMapper;

    public CuentaService(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public void createConDTO(CuentaDTO dto){
        // Si el DTO trae usuarios (solo IDs en UsuarioSimpleDTO), resolver entidades desde el repositorio
        List<Usuario> usuarios = java.util.List.of();
        if (dto.getUsuarios() != null && !dto.getUsuarios().isEmpty()) {
            var ids = dto.getUsuarios().stream().map(org.example.msvcusuario.dto.UsuarioSimpleDTO::getId).toList();
            usuarios = usuarioRepository.findAllById(ids);
        }
        Cuenta c = new Cuenta(dto.getFondos(), dto.getPlan(), usuarios);
        if (dto.getId() != null) {
            c.setId(dto.getId());
        }
        cuentaRepository.save(c);
    }

    public void crearCuenta(Long idUsuario){
        Usuario u = usuarioRepository.getReferenceById(idUsuario);
        Cuenta c = new Cuenta(u);
        cuentaRepository.save(c);
    }

    public List<CuentaDTO> findAll(){
        return this.cuentaRepository.findAll().stream().map(this.cuentaMapper::toDTO).toList();
    }

    public CuentaDTO findById(long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        return this.cuentaMapper.toDTO(cuenta);
    }

    public void agregarUsuarioACuenta(Long idCuenta, Long idUsuario) {

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!cuenta.getUsuarios().contains(usuario)) {
            cuenta.getUsuarios().add(usuario);
            usuario.getCuentas().add(cuenta);
        }

        usuarioRepository.save(usuario);
        cuentaRepository.save(cuenta);
    }

    public void update(CuentaDTO dto,long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        this.cuentaMapper.update(dto, cuenta);
        this.cuentaRepository.save(cuenta);
    }

    public void delete(long id){
        this.cuentaRepository.deleteById(id);
    }

    public void desactivarCuenta(long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        cuenta.setDeshabilitada(true);
        this.cuentaRepository.save(cuenta);
    }

    public void agregarFondos(Long id,double fondos){
        Cuenta c = this.cuentaRepository.findById(id).orElseThrow();
        float total = (float) (c.getFondos() + fondos);
        c.setFondos(total);
        this.cuentaRepository.save(c);
    }



}
