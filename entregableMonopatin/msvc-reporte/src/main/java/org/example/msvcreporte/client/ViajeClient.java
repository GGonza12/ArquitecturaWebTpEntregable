package org.example.msvcreporte.client;


import org.example.msvcreporte.dto.ReporteCantidadViajesMonopatinDTO;
import org.example.msvcreporte.dto.ReporteKmMonopatinDTO;
import org.example.msvcreporte.dto.ReporteKmMonopatinPausaDTO;
import org.example.msvcreporte.dto.UsuarioViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-viaje", url = "http://localhost:8086/api/viaje")
public interface ViajeClient {

    @GetMapping("/reporte/km")
    List<ReporteKmMonopatinDTO> obtenerKmPorMonopatin();

    @GetMapping("/reporte/km-pausa")
    List<ReporteKmMonopatinPausaDTO> obtenerKmYTiempoPausasPorMonopatin();


    @GetMapping("/monopatin-cantidad-viajes")
     List<ReporteCantidadViajesMonopatinDTO> obtenerMonopatinesFrecuentes(
            @RequestParam int year,
            @RequestParam long cantidadMinima
    );

    @GetMapping("/ranking-usuarios")
    List<UsuarioViajeDTO> obtenerRankingUsuariosPorPeriodo(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin);
}
