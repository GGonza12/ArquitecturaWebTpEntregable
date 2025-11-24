package org.example.chatservice.tool;

import org.example.chatservice.dto.CuentaDTO;
import org.example.chatservice.feign.CuentaClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetCuentas implements Tool {
    private final CuentaClient cuentaClient;

    public ToolGetCuentas(CuentaClient cuentaClient) {
        this.cuentaClient = cuentaClient;
    }

    @Override
    public String getName() { return "get_accounts"; }

    @Override
    public String getDescription() { return "Devuelve todas las cuentas. Sin args."; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        List<CuentaDTO> cuentas = cuentaClient.obtenerTodasCuentas();
        return new ToolResult(true, "Cuentas obtenidas", cuentas);
    }
}
