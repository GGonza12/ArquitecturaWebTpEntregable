package org.example.msvcadministracion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "msvc-cuenta", url = "http://localhost:8083/api/cuenta")
public interface CuentaClient {

    @PutMapping("/desactivarCuenta/{id}")
    ResponseEntity<String> desactivarCuenta(@PathVariable("id") long id);


}