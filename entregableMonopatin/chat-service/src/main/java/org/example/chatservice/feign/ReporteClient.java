package org.example.chatservice.feign;


import org.example.chatservice.dto.*;
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

    @GetMapping("/api/reporte/km")
    List<ReporteKmMonopatinDTO> getKmPorMonopatin();

    @GetMapping("/api/reporte/km-pausa")
    List<ReporteKmMonopatinPausaDTO> getKmPausaPorMonopatin();

    @GetMapping("/api/reporte/facturacion")
    Double getTotalFacturado(@RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin);

    @GetMapping("/api/reporte/monopatin-cantidad-viajes")
    List<ReporteCantidadViajesMonopatinDTO> getMonopatinesConMasViajes(@RequestParam int year, @RequestParam long cantidadMinima);

    @GetMapping("/api/reporte/monopatin-cerca")
    List<ReporteMonopatinDTO> getMonopatinesCerca(@RequestParam double lat, @RequestParam double lon, @RequestParam double radioKm, @RequestParam int cant);

    @GetMapping("/api/reporte/usuarios-mas-activos")
    List<UsuarioRankingDTO> getUsuariosMasActivos(@RequestParam String fechaInicio, @RequestParam String fechaFin);
}
