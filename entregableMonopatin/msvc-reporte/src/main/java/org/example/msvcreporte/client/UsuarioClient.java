package org.example.msvcreporte.client;

import org.example.msvcreporte.dto.ReporteUsoMonopatinDTO;
import org.example.msvcreporte.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuario", url = "http://localhost:8080/api/usuario")
public interface UsuarioClient {
    @PostMapping("/buscar-por-ids")
    List<UsuarioDTO> obtenerUsuariosPorIds(@RequestBody List<Long> ids);

    @GetMapping("/uso")
    ReporteUsoMonopatinDTO calcularUso(
            @RequestParam List<Long> idsUsuarios,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin);

    @GetMapping("/relacionados/{idUsuario}")
    List<Long> obtenerUsuariosRelacionados(@PathVariable Long idUsuario);

    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id);

}


