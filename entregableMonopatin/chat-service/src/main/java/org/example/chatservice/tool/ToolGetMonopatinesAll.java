package org.example.chatservice.tool;

import org.example.chatservice.dto.MonopatinDTO;
import org.example.chatservice.feign.MonopatinClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetMonopatinesAll implements Tool{
    private final MonopatinClient monopatinClient;

    public ToolGetMonopatinesAll(MonopatinClient monopatinClient) {
        this.monopatinClient = monopatinClient;
    }

    @Override
    public String getName() { return "get_scooters"; }

    @Override
    public String getDescription() { return "Devuelve todos los monopatines. Sin args."; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        List<MonopatinDTO> list = monopatinClient.getAll();
        return new ToolResult(true, "Monopatines", list);
    }
}
