package org.example.msvcmonopatin.controller;

import org.example.msvcmonopatin.dto.MonopatinDTO;
import org.example.msvcmonopatin.service.MonopatinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/monopatin")
class MonopatinController {
    private final MonopatinService monopatinService;

    public MonopatinController(MonopatinService monoService){
        this.monopatinService = monoService;
    }

    @GetMapping
    public ResponseEntity<List<MonopatinDTO>> getAllMonopatines(){
        return ResponseEntity.ok(this.monopatinService.getAllMonopatines());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<MonopatinDTO> getMonopatinById(@PathVariable long id){
        MonopatinDTO monoDTO = this.monopatinService.getMonopatinById(id);
        return ResponseEntity.ok(monoDTO);
    }


    @PostMapping
    public ResponseEntity<String> agregarMonopatin(@RequestBody MonopatinDTO monopatinDTO){
        monopatinService.createMonopatin(monopatinDTO);
        return ResponseEntity.ok("Monopatin Creado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMonopatin(@PathVariable long id){
        this.monopatinService.deleteMonopatinById(id);
        return ResponseEntity.ok("Monopatin Eliminado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarMonopatin(@RequestBody MonopatinDTO monopatinDTO, @PathVariable long id){
        this.monopatinService.updateMonopatin(monopatinDTO, id);
        return ResponseEntity.ok("Monopatin Modificado");
    }

    @PutMapping("/mantenimiento/{id}")
    public ResponseEntity<String> monopatinParaMantenimiento(@PathVariable long id){
        this.monopatinService.setMonopatinMantenimiento(id);
        return ResponseEntity.ok("Monopatin en mantenimiento");
    }

    @PutMapping("/libre/{id}")
    public ResponseEntity<String> liberarMonopatin(@PathVariable long id){
        this.monopatinService.setMonopatinLibre(id);
        return ResponseEntity.ok("Monopatin libre");
    }

    @PutMapping("/uso/{id}")
    public ResponseEntity<String> ocuparMonopatin(@PathVariable long id){
        this.monopatinService.setMonopatinEnUso(id);
        return ResponseEntity.ok("Monopatin en uso");
    }

    @GetMapping("/cerca")
    public ResponseEntity<List<MonopatinDTO>> getCerca(@RequestParam double lat,@RequestParam long lon,@RequestParam double radio,@RequestParam int cant){
        return ResponseEntity.ok(this.monopatinService.getMonopatinesCerca(lat,lon,radio,cant));
    }












}
