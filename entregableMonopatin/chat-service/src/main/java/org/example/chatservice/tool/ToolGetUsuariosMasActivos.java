package org.example.chatservice.tool;

import org.example.chatservice.dto.UsuarioRankingDTO;
import org.example.chatservice.feign.ReporteClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetUsuariosMasActivos implements Tool{
    private final ReporteClient reporteClient;

    public ToolGetUsuariosMasActivos(ReporteClient reporteClient) {
        this.reporteClient = reporteClient;
    }

    @Override
    public String getName() { return "get_top_users"; }

    @Override
    public String getDescription() { return "Devuelve usuarios mas activos. Args: fechaInicio, fechaFin (strings)"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        String fi = args.get("fechaInicio").toString();
        String ff = args.get("fechaFin").toString();
        List<UsuarioRankingDTO> res = reporteClient.getUsuariosMasActivos(fi, ff);
        return new ToolResult(true, "Usuarios mas activos", res);
    }
}
