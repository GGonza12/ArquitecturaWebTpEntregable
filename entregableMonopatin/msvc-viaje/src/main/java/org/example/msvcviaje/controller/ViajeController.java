package org.example.msvcviaje.controller;

import org.example.msvcviaje.dto.*;
import org.example.msvcviaje.model.Pausa;
import org.example.msvcviaje.service.ViajeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/viaje")
class ViajeController {

    private final ViajeService viajeService;
    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping("/entre-fechas")
    public ResponseEntity<List<ViajeDTO>> obtenerViajesEntreFechas(
            @RequestParam Timestamp desde,
            @RequestParam Timestamp hasta) {

        List<ViajeDTO> viajes = viajeService.obtenerViajesEntreFechas(desde, hasta);
        return ResponseEntity.ok(viajes);
    }

    @GetMapping
    public ResponseEntity<List<ViajeDTO>> obtenerViajes(){
        List<ViajeDTO> viajes = viajeService.findAll();
        return ResponseEntity.ok(viajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> obtenerViaje(@PathVariable String id){
        ViajeDTO dto = this.viajeService.findByID(id);
        return  ResponseEntity.ok(dto);
    }



    @PostMapping
    public ResponseEntity<String> crearViaje(@RequestBody ViajeDTO dto){
        this.viajeService.crearViajeConDTO(dto);
        return ResponseEntity.ok("Viaje creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarViaje(@RequestBody ViajeDTO dto, @PathVariable String id){
        this.viajeService.update(dto, id);
        return ResponseEntity.ok("Viaje actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarViaje(@PathVariable String id){
        this.viajeService.delete(id);
        return ResponseEntity.ok("Viaje eliminado");
    }

    @PutMapping("/pausa/{id}")
    public ResponseEntity<String> agregarPausaViaje(@PathVariable String id,@RequestBody Pausa pausa){
        this.viajeService.agregarPausa(id,pausa);
        return ResponseEntity.ok("pausa agregada");
    }

    @GetMapping("/reporte/km")
    public ResponseEntity<List<ReporteKmMonopatinDTO>> obtenerKmPorMonopatin() {
        List<ReporteKmMonopatinDTO> resultado = viajeService.obtenerKmRecorridosPorMonopatin();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/reporte/km-pausa")
    public ResponseEntity<List<ReporteKmMonopatinPausaDTO>> obtenerKmYTiempoPausaPorMonopatin() {
        List<ReporteKmMonopatinPausaDTO> resultado = viajeService.obtenerKmYTiempoPausasPorMonopatin();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/monopatin-cantidad-viajes")
    public ResponseEntity<List<CantidadViajesMonopatinDTO>> obtenerMonopatinesFrecuentes(
            @RequestParam int year,
            @RequestParam long cantidadMinima
    ) {
        List<CantidadViajesMonopatinDTO> resultado =
                viajeService.obtenerMonopatinesConMasDeXViajes(year, cantidadMinima);
        return ResponseEntity.ok(resultado);
    }

    //remplazado de msvc-facturacion
    @GetMapping("/total-facturado")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin) {

        return ResponseEntity.ok(viajeService.calcularTotalFacturado(anio, mesInicio, mesFin));
    }

    //Finalizar Viaje de Monopatin pasando por parametro el precio
    @PutMapping("/{id}/finalizarViaje/{precio}/")
    public ResponseEntity<String> finalizarViaje(@PathVariable String id,@PathVariable double precio){
        this.viajeService.finalizarViaje(id,precio);
        return ResponseEntity.ok("Viaje finalizado");
    }

    //Finalizar Viaje de Monopatin pasando por parametro la ubicacion y precio
    @PutMapping("/{id}/finalizarViaje/{precio}/{latitud}/{longitud}")
    public ResponseEntity<String> finalizarViajeCompleto(@PathVariable String id,@PathVariable double precio,@PathVariable double latitud, @PathVariable double longitud){
        this.viajeService.finalizarViajeCompleto(id,precio,latitud,longitud);
        return ResponseEntity.ok("Viaje finalizado");
    }

    //4.e
    @GetMapping("/ranking-usuarios")
    public ResponseEntity<List<UsuarioViajeDTO>> obtenerRankingUsuariosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {

        List<UsuarioViajeDTO> ranking = viajeService.obtenerRankingUsuariosPorPeriodo(fechaInicio, fechaFin);
        return ResponseEntity.ok(ranking);
    }
    //4.H
    @GetMapping("/uso")
    public ResponseEntity<UsoMonopatinDTO> obtenerUso(
            @RequestParam List<Long> idsUsuarios,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {
        return ResponseEntity.ok(viajeService.calcularUso(idsUsuarios, fechaInicio, fechaFin));
    }






}
