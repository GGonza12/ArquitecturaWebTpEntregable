package org.example.chatservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "facturacionClient", url = "${services.facturacion.url}")
public interface FacturacionClient {
    @GetMapping("/api/facturacion")
    Double getPrecio();
}
