package org.example.msvcfacturacion.controller;

import org.example.msvcfacturacion.dto.PrecioDTO;
import org.example.msvcfacturacion.service.PrecioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturacion")
class FacturacionController {
    private final PrecioService precioService;

    public FacturacionController(PrecioService precioService) {
        this.precioService = precioService;
    }
    //Todos
    @GetMapping
    public ResponseEntity<Double> getPrecio(){
        return  ResponseEntity.ok(this.precioService.getPrecio());
    }
    //Admin
    @PostMapping
    public ResponseEntity<String> agregarPrecio(@RequestBody PrecioDTO precioDTO){
        this.precioService.savePrecio(precioDTO);
        return ResponseEntity.ok("Precio agregado");
    }

    //Admin 4.F
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPrecioPenalizacion(@PathVariable("id") long id, @RequestBody double precioNuevo){
        this.precioService.actualizarPrecioPenalizacion(id, precioNuevo);
        return ResponseEntity.ok("Precio actualizado");
    }

    //Todos
    @PutMapping("/finalizarViaje/{id}")
    public ResponseEntity<String> finalizarViaje(@PathVariable String id){
        this.precioService.finalizarViaje(id);
        return ResponseEntity.ok("Viaje finalizado");
    }
    //Todos
    @PutMapping("/finalizarViaje/{id}/{latitud}/{longitud}")
    public ResponseEntity<String> finalizarViaje(@PathVariable String id,@PathVariable double latitud,@PathVariable double longitud){
        this.precioService.finalizarViajeCompleto(id,latitud,longitud);
        return ResponseEntity.ok("Viaje finalizado");
    }


}
