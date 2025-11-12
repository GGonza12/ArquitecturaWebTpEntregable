package org.example.msvcadministracion.client;


import org.example.msvcadministracion.dto.PrecioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-facturacion", url = "http://localhost:8085/api/facturacion")
public interface FacturacionClient {

    @PostMapping
    ResponseEntity<String> agregarPrecio(@RequestBody PrecioDTO precioDTO);

}
