package org.example.msvcusuario.service;

import org.example.msvcusuario.dto.UsuarioDTO;
import org.example.msvcusuario.dto.UsuarioSimpleDTO;
import org.example.msvcusuario.model.Cuenta;
import org.example.msvcusuario.model.Rol;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.repository.CuentaRepository;
import org.example.msvcusuario.repository.UsuarioRepository;
import org.example.msvcusuario.utils.UsuarioMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@Component
public class UsuarioService {
        private final UsuarioRepository usuarioRepository;
        private final UsuarioMapper usuarioMapper;
        private final CuentaRepository cuentaRepository;
        private final PasswordEncoder passwordEncoder;

        public UsuarioService(UsuarioRepository user, UsuarioMapper usuarioMapper, CuentaRepository cuentaRepository, PasswordEncoder passwordEncoder) {
            this.usuarioRepository = user;
            this.usuarioMapper = usuarioMapper;
            this.cuentaRepository = cuentaRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public List<UsuarioSimpleDTO> obtenerUsuariosPorIds(List<Long> ids) {
            return usuarioRepository.findAllById(ids).stream().map(this.usuarioMapper::toDTOSimple).toList();
        }

        public List<UsuarioDTO> findAll() {
            return usuarioRepository.findAll().stream().map(this.usuarioMapper::toDTO).toList();
        }

         public List<Usuario> findAllUsuario() {
            return usuarioRepository.findAll().stream().toList();
        }

        public void create(UsuarioDTO dto){
            Usuario u = this.usuarioMapper.toEntity(dto);
            // Asegurar userName y password estén seteados y codificados
            if (dto.getUserName() != null) {
                u.setUserName(dto.getUserName());
            }
            if (dto.getPassword() != null) {
                u.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            this.usuarioRepository.save(u);
        }

        public void agregarCuentaAUsuario(Long idUsuario, Long idCuenta) {

            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Cuenta cuenta = cuentaRepository.findById(idCuenta)
                    .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

            // Evitar duplicados
            if (!usuario.getCuentas().contains(cuenta)) {
                usuario.getCuentas().add(cuenta);
                cuenta.getUsuarios().add(usuario);
            }

            usuarioRepository.save(usuario);
        }

        public UsuarioDTO findById(long id){
            return this.usuarioMapper.toDTO(this.usuarioRepository.findById(id).orElseThrow());
        }

        public Usuario findByIdUsuario(long id){
            return this.usuarioRepository.findById(id).orElseThrow();
        }

        public void update(UsuarioDTO dto,long id){
            Usuario u = this.usuarioRepository.findById(id).orElseThrow();
            this.usuarioMapper.update(dto,u);
            // Si se envió una nueva contraseña, codificarla antes de guardar
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                u.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            this.usuarioRepository.save(u);
        }

        public void delete(long id){
            this.usuarioRepository.deleteById(id);
        }

        public void cambiarRol(long id, Rol rol){
            Usuario u = this.usuarioRepository.findById(id).orElseThrow();
            u.setRol(rol);
            this.usuarioRepository.save(u);
        }

        public List<Long> obtenerUsuariosRelacionados(Long idUsuario) {
            return usuarioRepository.obtenerUsuariosRelacionados(idUsuario);
        }

        public Usuario findByIdUsuario(Long id) {
            return usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }



}
