package org.example.chatservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "usuarioClient", url = "${services.usuario.url}")
public interface UsuarioClient {
    @GetMapping("/api/usuario/{id}")
    Map<String, Object> getUsuarioById(@PathVariable("id") Long id);
}
