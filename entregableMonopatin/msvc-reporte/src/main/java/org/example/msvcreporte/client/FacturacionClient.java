package org.example.msvcreporte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-facturacion", url = "http://localhost:8085/api/facturacion")
public interface FacturacionClient {

    @GetMapping("/totalFacturado")
    ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam("anio") int anio,
            @RequestParam("mesInicio") int mesInicio,
            @RequestParam("mesFin") int mesFin
    );
}