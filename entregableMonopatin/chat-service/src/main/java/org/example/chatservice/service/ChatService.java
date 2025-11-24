package org.example.chatservice.service;

import org.example.chatservice.client.GroqClient;
import org.example.chatservice.feign.CuentaClient;
import org.example.chatservice.feign.UsuarioClient;
import org.example.chatservice.tool.Tool;
import org.example.chatservice.tool.ToolRegistry;
import org.example.chatservice.tool.ToolResult;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class ChatService {

    private final GroqClient groqClient;
    private final CuentaClient cuentaClient;
    private final ToolRegistry toolRegistry;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatService(GroqClient groqClient, CuentaClient cuentaClient, ToolRegistry toolRegistry) {
        this.groqClient = groqClient;
        this.cuentaClient = cuentaClient;
        this.toolRegistry = toolRegistry;
    }

    public String chat(Long cuentaId, String message) {

        // ==== 1) Validar cuenta PREMIUM ====
        Map<String, Object> cuenta = cuentaClient.getCuentaById(cuentaId);

        if (cuenta == null || !cuenta.containsKey("plan")) {
            return "No se encontró la cuenta solicitada.";
        }

        String plan = cuenta.get("plan").toString();
        boolean isPremium = plan.equals("PLAN_PREMIUM");

        if (!isPremium) {
            return "Funcionalidad disponible solo para cuentas PREMIUM.";
        }

        // ==== 2) Construir prompt con tools ====
        StringBuilder prompt = new StringBuilder();
        prompt.append("Eres un asistente para monopatines. Dispones de estas herramientas:\n");

        toolRegistry.getAll().forEach((name, tool) -> {
            prompt.append("- ").append(name).append(": ").append(tool.getDescription()).append("\n");
        });

        prompt.append("El usuario pregunta: ").append(message).append("\n");
        prompt.append("Si la respuesta requiere llamar a una tool, devolvé JSON EXACTO:\n");
        prompt.append("{\"tool\":\"<name>\",\"args\":{...}}\n");
        prompt.append("Si no, respondé en texto plano.");

        // ==== 3) Enviar a Groq ====
        String resp = groqClient.preguntar(prompt.toString());

        // ==== 4) Si Groq devolvió JSON con tool → ejecutarla ====
        try {
            JsonNode node = mapper.readTree(resp);
            if (node.has("tool")) {
                String toolName = node.get("tool").asText();
                JsonNode argsNode = node.get("args");

                Map<String,Object> args = mapper.convertValue(argsNode, Map.class);
                Tool tool = toolRegistry.getTool(toolName);

                if (tool == null) {
                    return "La tool solicitada no existe: " + toolName;
                }

                ToolResult result = tool.execute(args);

                return mapper.writeValueAsString(
                        Map.of(
                                "ok", result.isOk(),
                                "message", result.getMessage(),
                                "data", result.getData()
                        )
                );
            }
        } catch (Exception ignore) {
            // No es JSON → devolver texto plano
        }

        return resp;
    }
}