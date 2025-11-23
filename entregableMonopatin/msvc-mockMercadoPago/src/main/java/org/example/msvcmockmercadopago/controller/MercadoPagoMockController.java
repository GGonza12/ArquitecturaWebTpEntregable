package org.example.msvcmockmercadopago.controller;

import org.example.msvcmockmercadopago.PagoRequest;
import org.example.msvcmockmercadopago.PagoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/mp")
public class MercadoPagoMockController {

    @PostMapping("/pagar")
    public ResponseEntity<PagoResponse> procesarPago(@RequestBody PagoRequest request) {

        PagoResponse response = new PagoResponse(
                "APROBADO",
                "TEST-" + System.currentTimeMillis(),
                "Pago simulado correctamente"
        );

        return ResponseEntity.ok(response);
    }
}
