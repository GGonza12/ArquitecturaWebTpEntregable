package org.example.msvcparada.controller;

import org.example.msvcparada.dto.ParadaDTO;
import org.example.msvcparada.service.ParadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parada")
class ParadaController {
    private final ParadaService paradaService;

    public ParadaController(ParadaService paradaService) {
        this.paradaService = paradaService;
    }
    //Admin
    @GetMapping
    public ResponseEntity<List<ParadaDTO>> getParadas(){
        return ResponseEntity.ok(this.paradaService.getParadas());
    }
    //Todos
    @GetMapping("/{id}")
    public ResponseEntity<ParadaDTO> getParadaById(@PathVariable("id") Long id){
        ParadaDTO paradaDTO = this.paradaService.getParadaById(id);
        return ResponseEntity.ok(paradaDTO);
    }
    //Admin
    @PostMapping
    public ResponseEntity<String> createParada(@RequestBody ParadaDTO paradaDTO){
        this.paradaService.createParada(paradaDTO);
        return ResponseEntity.ok("Parada agregada");
    }
    //Admin
    @PutMapping("/{id}")
    public ResponseEntity<String> updateParada(@PathVariable("id") Long id, @RequestBody ParadaDTO paradaDTO){
        this.paradaService.updateParada(paradaDTO,id);
        return ResponseEntity.ok("Parada actualizada");
    }
    //Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParada(@PathVariable("id") Long id){
        this.paradaService.deleteParada(id);
        return ResponseEntity.ok("Parada eliminada");
    }

    //



}
