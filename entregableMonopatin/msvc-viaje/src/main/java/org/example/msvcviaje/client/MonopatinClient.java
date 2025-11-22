package org.example.msvcviaje.client;

import org.example.msvcviaje.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-monopatin",url ="http://localhost:8080/api/monopatin")
public interface MonopatinClient {

    @GetMapping("/{id}")
    MonopatinDTO getMonopatinById(@PathVariable long id);

    @PutMapping("/{id}")
    String modificarMonopatin(@RequestBody MonopatinDTO monopatinDTO, @PathVariable long id);

}