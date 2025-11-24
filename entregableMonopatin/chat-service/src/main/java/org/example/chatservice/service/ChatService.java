package org.example.chatservice.service;

import org.example.chatservice.client.GroqClient;
import org.example.chatservice.dto.ChatResponse;
import org.example.chatservice.feign.CuentaClient;
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

    public ChatResponse chat(Long cuentaId, String message) {

        // ==== 1) Validar cuenta PREMIUM ====
        Map<String, Object> cuenta = cuentaClient.getCuentaById(cuentaId);

        if (cuenta == null || !cuenta.containsKey("plan")) {
            return new ChatResponse(
                    message,
                    null,
                    null,
                    "No se encontró la cuenta solicitada."
            );
        }

        boolean isPremium = "PLAN_PREMIUM".equals(cuenta.get("plan").toString());

        if (!isPremium) {
            return new ChatResponse(
                    message,
                    null,
                    null,
                    "Funcionalidad disponible solo para cuentas PREMIUM."
            );
        }

        // ==== 2) Construir prompt ====
        StringBuilder prompt = new StringBuilder();
        prompt.append("Eres un asistente para monopatines. Dispones de las siguientes herramientas:\n");

        toolRegistry.getAll().forEach((name, tool) -> {
            prompt.append("- ").append(name).append(": ").append(tool.getDescription()).append("\n");
        });

        prompt.append("El usuario pregunta: ").append(message).append("\n\n");
        prompt.append("""
                Si la respuesta requiere llamar a una tool, devolvé JSON EXACTO:
                {"tool":"<name>","args":{...}}
                Si no, respondé solo en texto plano.
                """);

        String promptFinal = prompt.toString();

        // ==== 3) Enviar prompt a Groq ====
        String rawReply = groqClient.preguntar(promptFinal);

        // ==== 4) Intentar parsear JSON de tools ====
        try {
            JsonNode node = mapper.readTree(rawReply);

            if (node.has("tool")) {
                String toolName = node.get("tool").asText();
                JsonNode argsNode = node.get("args");

                Map<String,Object> args = mapper.convertValue(argsNode, Map.class);
                Tool tool = toolRegistry.getTool(toolName);

                if (tool == null) {
                    return new ChatResponse(
                            message,
                            promptFinal,
                            rawReply,
                            "La tool solicitada no existe: " + toolName
                    );
                }

                ToolResult result = tool.execute(args);

                return new ChatResponse(
                        message,
                        promptFinal,
                        rawReply,
                        Map.of(
                                "ok", result.isOk(),
                                "message", result.getMessage(),
                                "data", result.getData()
                        )
                );
            }
        } catch (Exception ignore) {}

        // ==== 5) Devolver respuesta normal ====
        return new ChatResponse(
                message,
                promptFinal,
                rawReply,
                rawReply
        );
    }
}