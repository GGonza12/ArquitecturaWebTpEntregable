package org.example.chatservice.tool;

import org.example.chatservice.dto.CantidadViajesMonopatinDTO;
import org.example.chatservice.feign.ViajeClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetMonopatinesConMasViajes implements Tool{
    private final ViajeClient viajeClient;

    public ToolGetMonopatinesConMasViajes(ViajeClient viajeClient) {
        this.viajeClient = viajeClient;
    }

    @Override
    public String getName() { return "get_top_scooters"; }

    @Override
    public String getDescription() { return "Devuelve monopatines con más viajes. Args: year, cantidadMinima"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        int year = Integer.parseInt(args.get("year").toString());
        long min = Long.parseLong(args.get("cantidadMinima").toString());
        List<CantidadViajesMonopatinDTO> res = viajeClient.getMonopatinesConMasViajes(year, min);
        return new ToolResult(true, "Monopatines frecuentes", res);
    }
}
