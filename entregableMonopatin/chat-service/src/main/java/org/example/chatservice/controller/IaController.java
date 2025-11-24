package org.example.chatservice.controller;

import org.example.chatservice.dto.ChatResponse;
import org.example.chatservice.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ia")
public class IaController {

    private final ChatService chatService;

    public IaController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String,Object> body) {

        Long cuentaId = body.get("cuentaId") == null
                ? null : Long.valueOf(body.get("cuentaId").toString());

        String message = body.get("message") == null
                ? "" : body.get("message").toString();

        if (cuentaId == null)
            return ResponseEntity.badRequest().body("Falta cuentaId en el body");

        ChatResponse response = chatService.chat(cuentaId, message);

        return ResponseEntity.ok(response);
    }
}