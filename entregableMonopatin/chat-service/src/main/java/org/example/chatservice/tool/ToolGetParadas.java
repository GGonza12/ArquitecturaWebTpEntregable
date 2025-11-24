package org.example.chatservice.tool;

import org.example.chatservice.dto.ParadaDTO;
import org.example.chatservice.feign.ParadaClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetParadas implements Tool{
    private final ParadaClient paradaClient;

    public ToolGetParadas(ParadaClient paradaClient) {
        this.paradaClient = paradaClient;
    }

    @Override
    public String getName() { return "get_stops"; }

    @Override
    public String getDescription() { return "Devuelve paradas o una por id si se pasa arg id"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        if(args.containsKey("id")){
            Long id = Long.valueOf(args.get("id").toString());
            ParadaDTO p = paradaClient.getParadaById(id);
            return new ToolResult(true, "Parada", p);
        }
        List<ParadaDTO> list = paradaClient.getParadas();
        return new ToolResult(true, "Paradas", list);
    }
}
