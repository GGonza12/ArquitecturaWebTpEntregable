package org.example.chatservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "viajeClient", url = "${services.viaje.url}")
public interface ViajeClient {
    @GetMapping("/api/viaje/monopatin-cantidad-viajes")
    List<Map<String, Object>> getMonopatinesConMasViajes(@RequestParam int year, @RequestParam long cantidadMinima);

    @GetMapping("/api/viaje/total-facturado")
    Double getTotalFacturado(@RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin);
}
