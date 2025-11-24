package org.example.chatservice.tool;

import org.example.chatservice.dto.MonopatinDTO;
import org.example.chatservice.feign.MonopatinClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolDistanceNearby implements Tool{
    private final MonopatinClient monopatinClient;

    public ToolDistanceNearby(MonopatinClient monopatinClient) {
        this.monopatinClient = monopatinClient;
    }

    @Override
    public String getName() { return "nearby_scooters"; }

    @Override
    public String getDescription() {
        return "Devuelve lista de monopatines cercanos. Args: lat, lon, radioKm, cant";
    }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        double lat = Double.parseDouble(args.get("lat").toString());
        double lon = Double.parseDouble(args.get("lon").toString());
        double radioKm = Double.parseDouble(args.get("radioKm").toString());
        int cant = Integer.parseInt(args.get("cant").toString());
        List<MonopatinDTO> list = monopatinClient.getCerca(lat, lon, radioKm, cant);
        return new ToolResult(true, "Monopatines cercanos", list);
    }
}
