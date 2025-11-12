package org.example.msvcreporte.client;

import org.example.msvcreporte.dto.ReporteMonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-monopatin",url ="http://localhost:8081/api/monopatin")
public interface MonopatinClient {

    @GetMapping("/cerca")
    List<ReporteMonopatinDTO> getCerca(@RequestParam double lat, @RequestParam double lon, @RequestParam double radioKm, @RequestParam int cant);

}
