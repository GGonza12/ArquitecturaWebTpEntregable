package org.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ChatResponse {
    private String userMessage;     // Mensaje enviado por el usuario
    private String modelPrompt;     // Prompt completo enviado al LLM
    private String rawModelReply;   // Texto crudo devuelto por Groq
    private Object finalResponse;   // Respuesta final (texto o JSON de herramienta)
}
