package org.example.chatservice.tool;

import org.example.chatservice.dto.ViajeDTO;
import org.example.chatservice.feign.ViajeClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetViajes implements Tool{
    private final ViajeClient viajeClient;

    public ToolGetViajes(ViajeClient viajeClient) {
        this.viajeClient = viajeClient;
    }

    @Override
    public String getName() { return "get_trips"; }

    @Override
    public String getDescription() { return "Opciones: all (sin args) o entre-fechas: args desde,hasta (ISO string)"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        if(args.containsKey("desde") && args.containsKey("hasta")){
            String desde = args.get("desde").toString();
            String hasta = args.get("hasta").toString();
            List<ViajeDTO> list = viajeClient.getViajesEntreFechas(desde, hasta);
            return new ToolResult(true, "Viajes entre fechas", list);
        } else {
            List<ViajeDTO> list = viajeClient.getAllViajes();
            return new ToolResult(true, "Todos los viajes", list);
        }
    }
}
