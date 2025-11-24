package org.example.chatservice.feign;


import org.example.chatservice.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "monopatinClient", url = "${services.monopatin.url}")
public interface MonopatinClient {
    @GetMapping("/api/monopatin/cerca")
    List<MonopatinDTO> getCerca(@RequestParam double lat, @RequestParam double lon, @RequestParam double radioKm, @RequestParam int cant);

    @GetMapping("/api/monopatin")
    List<MonopatinDTO> getAll();

    @GetMapping("/api/monopatin/{id}")
    MonopatinDTO getById(@PathVariable("id") long id);
}
