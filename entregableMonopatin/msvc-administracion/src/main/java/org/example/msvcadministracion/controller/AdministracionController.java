package org.example.msvcadministracion.controller;

import org.example.msvcadministracion.dto.PrecioDTO;
import org.example.msvcadministracion.service.AdministracionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administracion")
class AdministracionController {

    private final AdministracionService administracionService;

    public  AdministracionController(AdministracionService administracionService) {
        this.administracionService = administracionService;
    }

    @PutMapping("/deshabilitar-cuenta/{id}")
    public ResponseEntity<String> deshabilitarCuenta(@PathVariable long id) {
        String mensaje = administracionService.desactivarCuenta(id);
        return ResponseEntity.ok("Administración: " + mensaje);
    }

    @PostMapping("/agregar-precio")
    public ResponseEntity<String> agregarPrecio(@RequestBody PrecioDTO precioDTO) {
        this.administracionService.agregarPrecio(precioDTO);
        return ResponseEntity.ok("Precio Agregado");
    }

}
