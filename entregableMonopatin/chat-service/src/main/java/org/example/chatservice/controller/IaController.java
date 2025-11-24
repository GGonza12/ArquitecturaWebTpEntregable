package org.example.chatservice.controller;

import org.example.chatservice.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ia")
class IaController {

    private final ChatService chatService;

    public IaController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String,Object> body) {

        Long cuentaId = body.get("cuentaId") == null ?
                null : Long.valueOf(body.get("cuentaId").toString());

        String message = body.get("message") == null ?
                "" : body.get("message").toString();

        if (cuentaId == null)
            return ResponseEntity.badRequest().body("Falta cuentaId en el body");

        String resp = chatService.chat(cuentaId, message);

        return ResponseEntity.ok(Map.of("response", resp));
    }

    @PostMapping("/tool/{toolName}")
    public ResponseEntity<?> invokeTool(@PathVariable String toolName, @RequestBody(required = false) Map<String,Object> params) {
        var tool = chatService.getClass(); // solo para compilar; NO usar. Invocá herramientas via registry
        return ResponseEntity.status(501).body("Invocación directa de tool no implementada en este endpoint. Usar /api/ia/chat o implementar endpoint específico.");
    }
}
