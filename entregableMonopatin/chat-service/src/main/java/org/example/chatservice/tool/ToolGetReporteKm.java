package org.example.chatservice.tool;

import org.example.chatservice.dto.ReporteKmMonopatinDTO;
import org.example.chatservice.dto.ReporteKmMonopatinPausaDTO;
import org.example.chatservice.feign.ViajeClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetReporteKm implements Tool{
    private final ViajeClient viajeClient;

    public ToolGetReporteKm(ViajeClient viajeClient) {
        this.viajeClient = viajeClient;
    }

    @Override
    public String getName() { return "get_km_report"; }

    @Override
    public String getDescription() { return "Devuelve reporte km o km-pausa. Arg opcional tipo=km|km-pausa"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        String tipo = args.getOrDefault("tipo", "km").toString();
        if("km-pausa".equals(tipo)){
            List<ReporteKmMonopatinPausaDTO> res = viajeClient.getReporteKmPausa();
            return new ToolResult(true, "Reporte km-pausa", res);
        }
        List<ReporteKmMonopatinDTO> res = viajeClient.getReporteKm();
        return new ToolResult(true, "Reporte km", res);
    }
}
