package org.example.chatservice.feign;

import org.example.chatservice.dto.ParadaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "paradaClient", url = "${services.parada.url}")
public interface ParadaClient {
    @GetMapping("/api/parada")
    List<ParadaDTO> getParadas();

    @GetMapping("/api/parada/{id}")
    ParadaDTO getParadaById(@PathVariable("id") Long id);
}
