package org.example.msvcmockmercadopago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponse {
    private String estado;
    private String idTransaccion;
    private String mensaje;
}