package org.example.chatservice.feign;

import org.example.chatservice.dto.CuentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "cuentaClient", url = "${services.cuenta.url}")
public interface CuentaClient {
    @GetMapping("/api/cuenta/{id}")
    Map<String, Object> getCuentaById(@PathVariable("id") Long id);

    @GetMapping("/api/cuenta/{id}")
    CuentaDTO obtenerCuenta(@PathVariable("id") Long id);
}
