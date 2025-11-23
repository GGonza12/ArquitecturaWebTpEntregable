package org.example.msvcusuario.client;

import org.example.msvcusuario.dto.PagoRequest;
import org.example.msvcusuario.dto.PagoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-mercadopago-mock", url = "http://localhost:8099/api/mp")
public interface MercadoPagoClient {

    @PostMapping("/pagar")
    PagoResponse procesarPago(@RequestBody PagoRequest request);
}


