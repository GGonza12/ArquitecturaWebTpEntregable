package org.example.chatservice.tool;

import org.example.chatservice.dto.ReporteUsoMonopatinTiempo;
import org.example.chatservice.feign.ReporteClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ToolGetUsoUsuario implements Tool{
    private final ReporteClient reporteClient;

    public ToolGetUsoUsuario(ReporteClient reporteClient) {
        this.reporteClient = reporteClient;
    }

    @Override
    public String getName() { return "get_user_usage"; }

    @Override
    public String getDescription() { return "Devuelve uso por usuario: args: idUsuario, fechaInicio, fechaFin, incluirRelacionados"; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        Long idUsuario = Long.valueOf(args.get("idUsuario").toString());
        String fi = args.get("fechaInicio").toString();
        String ff = args.get("fechaFin").toString();
        boolean inc = Boolean.parseBoolean(args.getOrDefault("incluirRelacionados", "false").toString());
        ReporteUsoMonopatinTiempo reporte = reporteClient.getUsoUsuario(idUsuario, fi, ff, inc);
        return new ToolResult(true, "Reporte de uso", reporte);
    }
}
