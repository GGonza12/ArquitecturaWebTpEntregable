package org.example.msvccuenta.controller;

import org.example.msvccuenta.dto.CuentaDTO;
import org.example.msvccuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
class CuentaController {
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> findAll() {
        return ResponseEntity.ok(this.cuentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> findById(@PathVariable long id) {
        CuentaDTO dto = this.cuentaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> save(@PathVariable long id) {
        this.cuentaService.crearCuenta(id);
        return ResponseEntity.ok("Cuenta creada");
    }

    @PostMapping("/crearCuentaDTO")
    public ResponseEntity<String> saveDTO(@RequestBody CuentaDTO dto) {
        this.cuentaService.createConDTO(dto);
        return ResponseEntity.ok("Cuenta creada con DTO");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody CuentaDTO dto) {
        this.cuentaService.update(dto, id);
        return ResponseEntity.ok("Cuenta modificada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        this.cuentaService.delete(id);
        return ResponseEntity.ok("Cuenta eliminada");
    }

    @PutMapping("/desactivarCuenta/{id}")
    public ResponseEntity<String> desactivarCuenta(@PathVariable long id) {
        this.cuentaService.desactivarCuenta(id);
        return ResponseEntity.ok("Cuenta desactivada");
    }

    @PutMapping("/agregarFondos/{id}")
    public ResponseEntity<String> agregarFondos(@PathVariable long id,@RequestBody float fondos) {
        this.cuentaService.agregarFondos(id,fondos);
        return ResponseEntity.ok("fondos agregados");
    }


}
