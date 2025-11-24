package org.example.chatservice.config;

import org.example.chatservice.tool.ToolDistanceNearby;
import org.example.chatservice.tool.ToolGetPrecio;
import org.example.chatservice.tool.ToolGetUsoUsuario;
import org.example.chatservice.tool.ToolRegistry;
import org.example.chatservice.tool.ToolGetCuentas;
import org.example.chatservice.tool.ToolGetUsuarios;
import org.example.chatservice.tool.ToolGetMonopatinesAll;
import org.example.chatservice.tool.ToolGetViajes;
import org.example.chatservice.tool.ToolGetParadas;
import org.example.chatservice.tool.ToolGetReporteKm;
import org.example.chatservice.tool.ToolGetUsuariosMasActivos;
import org.example.chatservice.tool.ToolGetMonopatinesConMasViajes;
import org.example.chatservice.tool.ToolGetTotalFacturado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ToolConfig {
    @Bean
    public ToolRegistry toolRegistry(
            ToolDistanceNearby t1,
            ToolGetPrecio t2,
            ToolGetUsoUsuario t3,
            ToolGetCuentas t4,
            ToolGetUsuarios t5,
            ToolGetMonopatinesAll t6,
            ToolGetViajes t7,
            ToolGetParadas t8,
            ToolGetReporteKm t9,
            ToolGetUsuariosMasActivos t10,
            ToolGetMonopatinesConMasViajes t11,
            ToolGetTotalFacturado t12
    ) {
        ToolRegistry registry = new ToolRegistry();
        registry.register(t1);
        registry.register(t2);
        registry.register(t3);
        registry.register(t4);
        registry.register(t5);
        registry.register(t6);
        registry.register(t7);
        registry.register(t8);
        registry.register(t9);
        registry.register(t10);
        registry.register(t11);
        registry.register(t12);
        return registry;
    }

}
