package org.example.msvcusuario.controller;

import org.example.msvcusuario.dto.UsuarioDTO;
import org.example.msvcusuario.model.Usuario;
import org.example.msvcusuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok(this.usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable long id){
        UsuarioDTO dto = this.usuarioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UsuarioDTO dto){
        this.usuarioService.create(dto);
        return ResponseEntity.ok("Usuario agregado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody UsuarioDTO dto){
        this.usuarioService.update(dto, id);
        return ResponseEntity.ok("Usuario modificado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        this.usuarioService.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }



}
