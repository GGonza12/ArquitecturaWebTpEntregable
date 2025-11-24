package org.example.chatservice.feign;


import org.example.chatservice.dto.ReporteUsoMonopatinTiempo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "reporteClient", url = "${services.reporte.url}")
public interface ReporteClient {
    @GetMapping("/api/reporte/uso-usuario")
    ReporteUsoMonopatinTiempo getUsoUsuario(@RequestParam("idUsuario") Long idUsuario,
                                            @RequestParam("fechaInicio") String fechaInicio,
                                            @RequestParam("fechaFin") String fechaFin,
                                            @RequestParam(value = "incluirRelacionados", required = false) boolean incluirRelacionados);
    @GetMapping("/api/reporte/uso")
    Map<String, Object> getUso(@RequestParam List<Long> idsUsuarios,
                               @RequestParam("fechaInicio") String fechaInicio,
                               @RequestParam("fechaFin") String fechaFin);
}
