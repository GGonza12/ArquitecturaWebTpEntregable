package org.example.msvcreporte.controller;

import org.example.msvcreporte.dto.*;
import org.example.msvcreporte.sercice.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
    //4.A Admin
    @GetMapping("/km")
    public ResponseEntity<List<ReporteKmMonopatinDTO>> getKmPorMonopatin() {
        return ResponseEntity.ok(reporteService.obtenerKmPorMonopatin());
    }
    //4.A Admin
    @GetMapping("/km-pausa")
    public ResponseEntity<List<ReporteKmMonopatinPausaDTO>> getKmYTiempoPausasPorMonopatin() {
        return ResponseEntity.ok(reporteService.obtenerKmYTiempoPausasPorMonopatin());
    }
    //4.D Admin
    @GetMapping("/facturacion")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFin
    ) {
        double total = reporteService.obtenerTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }


    //4.C Admin
    @GetMapping("/monopatin-cantidad-viajes")
    public ResponseEntity<List<ReporteCantidadViajesMonopatinDTO>> obtenerMonopatinesFrecuentes(
            @RequestParam int year,
            @RequestParam long cantidadMinima
    ){
        return ResponseEntity.ok(this.reporteService.obtenerCantidadViajesPorMonopatin(year, cantidadMinima));
    }
    //4.G Usuario
    @GetMapping("/monopatin-cerca")
    public ResponseEntity<List<ReporteMonopatinDTO>> obtenerMonopatinesCerca(
            @RequestParam double lat, @RequestParam double lon,@RequestParam double radioKm, @RequestParam int cant
    ){
        return ResponseEntity.ok(this.reporteService.obtenerMonopatinesCerca(lat, lon, radioKm, cant));
    }

    //4.E Admin
    @GetMapping("/usuarios-mas-activos")
    public ResponseEntity<List<UsuarioRankingDTO>> obtenerUsuariosMasActivos(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return ResponseEntity.ok(reporteService.obtenerUsuariosMasActivos(fechaInicio, fechaFin));
    }
    //4.H Usuario (todos)
    @GetMapping("/uso-usuario")
    public ResponseEntity<ReporteUsoMonopatinTiempo> obtenerUsoUsuario(
            @RequestParam Long idUsuario,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam(defaultValue = "false") boolean incluirRelacionados) {

        return ResponseEntity.ok(
                reporteService.obtenerUsoUsuarios(idUsuario, fechaInicio, fechaFin, incluirRelacionados)
        );

    }

}
