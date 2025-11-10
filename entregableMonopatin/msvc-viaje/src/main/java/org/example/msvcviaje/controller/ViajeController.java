package org.example.msvcviaje.controller;

import org.example.msvcviaje.dto.ViajeDTO;
import org.example.msvcviaje.model.Pausa;
import org.example.msvcviaje.service.ViajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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






}
