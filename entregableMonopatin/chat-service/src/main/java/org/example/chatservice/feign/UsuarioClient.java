package org.example.chatservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;

@FeignClient(name = "usuarioClient", url = "${services.usuario.url}")
public interface UsuarioClient {
    @GetMapping("/api/usuario/{id}")
    Map<String, Object> getUsuarioById(@PathVariable("id") Long id);

    @GetMapping("/api/usuario")
    List<Map<String, Object>> getUsuarios();

    @GetMapping("/api/usuario/relacionados/{idUsuario}")
    List<Long> getUsuariosRelacionados(@PathVariable("idUsuario") Long idUsuario);
}
