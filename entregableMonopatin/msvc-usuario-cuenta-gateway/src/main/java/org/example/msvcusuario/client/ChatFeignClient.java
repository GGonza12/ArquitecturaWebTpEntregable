package org.example.msvcusuario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-chat", url = "http://localhost:8090/api/ia")
public interface ChatFeignClient {

    @PostMapping("/prompt")
    String enviarPrompt(@RequestBody String prompt);
}
