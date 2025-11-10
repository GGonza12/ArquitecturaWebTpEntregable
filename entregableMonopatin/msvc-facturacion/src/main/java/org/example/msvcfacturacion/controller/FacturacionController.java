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

    @GetMapping
    public ResponseEntity<Double> getPrecio(){
        return  ResponseEntity.ok(this.precioService.getPrecio());
    }

    @PostMapping
    public ResponseEntity<String> agregarPrecio(@RequestBody PrecioDTO precioDTO){
        this.precioService.savePrecio(precioDTO);
        return ResponseEntity.ok("Precio agregado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPrecioPenalizacion(@PathVariable("id") long id, @RequestBody double precioNuevo){
        this.precioService.actualizarPrecioPenalizacion(id, precioNuevo);
        return ResponseEntity.ok("Precio actualizado");
    }

    @GetMapping("/totalFacturado")
    public ResponseEntity<Double> obtenerTotalFacturado(@RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin){
        Double total = precioService.calcularTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }


}
