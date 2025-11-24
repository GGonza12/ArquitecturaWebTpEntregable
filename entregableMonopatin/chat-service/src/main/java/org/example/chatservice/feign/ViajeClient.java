package org.example.chatservice.feign;

import org.example.chatservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "viajeClient", url = "${services.viaje.url}")
public interface ViajeClient {
    @GetMapping("/api/viaje/entre-fechas")
    List<ViajeDTO> getViajesEntreFechas(@RequestParam("desde") String desde, @RequestParam("hasta") String hasta);

    @GetMapping("/api/viaje")
    List<ViajeDTO> getAllViajes();

    @GetMapping("/api/viaje/{id}")
    ViajeDTO getViajeById(@PathVariable("id") String id);

    @GetMapping("/api/viaje/reporte/km")
    List<ReporteKmMonopatinDTO> getReporteKm();

    @GetMapping("/api/viaje/reporte/km-pausa")
    List<ReporteKmMonopatinPausaDTO> getReporteKmPausa();

    @GetMapping("/api/viaje/monopatin-cantidad-viajes")
    List<CantidadViajesMonopatinDTO> getMonopatinesConMasViajes(@RequestParam int year, @RequestParam long cantidadMinima);

    @GetMapping("/api/viaje/total-facturado")
    Double getTotalFacturado(@RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin);

    @GetMapping("/api/viaje/ranking-usuarios")
    List<UsuarioViajeDTO> getRankingUsuarios(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin);

    @GetMapping("/api/viaje/uso")
    UsoMonopatinDTO getUso(@RequestParam List<Long> idsUsuarios, @RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin);
}
