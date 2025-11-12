package org.example.msvcadministracion.service;

import org.example.msvcadministracion.client.CuentaClient;
import org.example.msvcadministracion.client.FacturacionClient;
import org.example.msvcadministracion.dto.PrecioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdministracionService {
    private final CuentaClient cuentaClient;
    private final FacturacionClient facturacionClient;

    public AdministracionService(CuentaClient cuentaClient, FacturacionClient facturacionClient) {
        this.cuentaClient = cuentaClient;
        this.facturacionClient = facturacionClient;
    }

    public String desactivarCuenta(long idCuenta) {
        ResponseEntity<String> respuesta = cuentaClient.desactivarCuenta(idCuenta);
        return respuesta.getBody();
    }

    public void agregarPrecio(PrecioDTO precioDTO) {
        this.facturacionClient.agregarPrecio(precioDTO);
    }
}
