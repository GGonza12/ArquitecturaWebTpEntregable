package org.example.msvcusuario.service;

import org.example.msvcusuario.dto.UsuarioDTO;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.repository.UsuarioRepository;
import org.example.msvcusuario.utils.UsuarioMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UsuarioService {
        private final UsuarioRepository usuarioRepository;
        private final UsuarioMapper usuarioMapper;

        public UsuarioService(UsuarioRepository user, UsuarioMapper usuarioMapper) {
            this.usuarioRepository=user;
            this.usuarioMapper=usuarioMapper;
        }

        public List<UsuarioDTO> findAll() {
            return usuarioRepository.findAll().stream().map(this.usuarioMapper::toDTO).toList();
        }

        public void create(UsuarioDTO dto){
            Usuario u = this.usuarioMapper.toEntity(dto);
            this.usuarioRepository.save(u);
        }

        public UsuarioDTO findById(long id){
            return this.usuarioMapper.toDTO(this.usuarioRepository.findById(id).orElseThrow());
        }

        public void update(UsuarioDTO dto,long id){
            Usuario u = this.usuarioRepository.findById(id).orElseThrow();
            this.usuarioMapper.update(dto,u);
            this.usuarioRepository.save(u);
        }

        public void delete(long id){
            this.usuarioRepository.deleteById(id);
        }


}
