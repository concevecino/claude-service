package com.ejemplo.claude.controller;

import com.ejemplo.claude.dto.ChatRequest;
import com.ejemplo.claude.dto.ChatResponse;
import com.ejemplo.claude.dto.PromptRequest;
import com.ejemplo.claude.service.ClaudeService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/claude")
public class ClaudeController {

    private final ClaudeService claude;

    public ClaudeController(ClaudeService claude) {
        this.claude = claude;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    /** Pregunta simple: { "prompt": "..." } */
    @PostMapping("/preguntar")
    public ChatResponse preguntar(@Valid @RequestBody PromptRequest req) {
        return new ChatResponse(claude.preguntar(req.prompt()));
    }

    /** Conversacion: { "messages": [{role, content}, ...], "system": "..." } */
    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest req) {
        return new ChatResponse(claude.chat(req.messages(), req.system()));
    }
}
