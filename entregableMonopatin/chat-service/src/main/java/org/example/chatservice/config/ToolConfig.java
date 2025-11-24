package org.example.chatservice.config;

import org.example.chatservice.tool.ToolDistanceNearby;
import org.example.chatservice.tool.ToolGetPrecio;
import org.example.chatservice.tool.ToolGetUsoUsuario;
import org.example.chatservice.tool.ToolRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ToolConfig {
    @Bean
    public ToolRegistry toolRegistry(ToolDistanceNearby t1, ToolGetPrecio t2, ToolGetUsoUsuario t3) {
        ToolRegistry registry = new ToolRegistry();
        registry.register(t1);
        registry.register(t2);
        registry.register(t3);
        return registry;
    }

}
