package org.example.msvcchat.controller;

import org.example.msvcchat.service.IaService;
import org.example.msvcchat.dto.PromptRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
public class IaController { // IaController exponene el endpoint REST que recibe prompts y delega a IaService.
    /** que va a hacer mi app en conjunto
     * IaController recibe prompt →
     * IaService añade esquema + manda a Ollama →
     * OllamaClient se conecta a la API →
     * Respuesta: IA devuelve consulta SQL →
     * IaService la ejecuta →
     * Respuesta JSON con resultados.
     */
    @Autowired
    private IaService iaService;
    @PostMapping(value = "/prompt", produces = "application/json") // Define endpoint POST /api/ia/prompt que recibe un prompt como cuerpo JSON.,→
    public ResponseEntity<?> procesarPrompt(@RequestBody String prompt) {
        try {
            return iaService.procesarPrompt(prompt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el prompt: " + e.getMessage());
        }
    }
}