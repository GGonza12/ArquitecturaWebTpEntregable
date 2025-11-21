package org.example.msvcfacturacion.utils;

import org.example.msvcfacturacion.dto.ViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@FeignClient(name = "msvc-viaje", url = "http://localhost:8080/api/viaje")
public interface ViajeClient {
    @GetMapping("/entre-fechas")
    List<ViajeDTO> obtenerViajesEntreFechas(
            @RequestParam Timestamp desde,
            @RequestParam Timestamp hasta);
    @GetMapping("/{id}")
     ViajeDTO obtenerViaje(@PathVariable String id);

    @PutMapping("/{id}/finalizarViaje/{precio}/")
    String finalizarViaje(@PathVariable String id,@PathVariable double precio);

}
