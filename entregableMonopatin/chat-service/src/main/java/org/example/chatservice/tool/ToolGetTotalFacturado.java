package org.example.chatservice.tool;

import org.example.chatservice.feign.ViajeClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ToolGetTotalFacturado implements Tool{
    private final ViajeClient viajeClient;

    public ToolGetTotalFacturado(ViajeClient viajeClient) {
        this.viajeClient = viajeClient;
    }

    @Override
    public String getName() { return "get_total_billed"; }

    @Override
    public String getDescription() { return "Devuelve total facturado. Args: anio, mesInicio, mesFin"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        int anio = Integer.parseInt(args.get("anio").toString());
        int mesInicio = Integer.parseInt(args.get("mesInicio").toString());
        int mesFin = Integer.parseInt(args.get("mesFin").toString());
        Double total = viajeClient.getTotalFacturado(anio, mesInicio, mesFin);
        return new ToolResult(true, "Total facturado", total);
    }
}
