package org.example.chatservice.tool;

import org.springframework.stereotype.Component;
import org.example.chatservice.feign.UsuarioClient;

import java.util.List;
import java.util.Map;

@Component
public class ToolGetUsuarios implements Tool{
    private final UsuarioClient usuarioClient;

    public ToolGetUsuarios(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public String getName() { return "get_users"; }

    @Override
    public String getDescription() { return "Devuelve lista de usuarios. Sin args."; }

    @Override
    public ToolResult execute(Map<String, Object> args) {
        List<Map<String,Object>> usuarios = usuarioClient.getUsuarios();
        return new ToolResult(true, "Usuarios obtenidos", usuarios);
    }
}
