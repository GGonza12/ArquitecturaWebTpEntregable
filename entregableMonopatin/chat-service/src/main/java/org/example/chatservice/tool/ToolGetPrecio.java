package org.example.chatservice.tool;

import org.example.chatservice.feign.FacturacionClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ToolGetPrecio  implements Tool{
    private final FacturacionClient facturacionClient;

    public ToolGetPrecio(FacturacionClient facturacionClient) {
        this.facturacionClient = facturacionClient;
    }

    @Override
    public String getName() { return "get_price"; }

    @Override
    public String getDescription() { return "Devuelve el precio por minuto / tarifa actual. Sin args."; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        Double precio = facturacionClient.getPrecio();
        return new ToolResult(true, "Precio actual", precio);
    }
}
