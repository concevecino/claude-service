package com.ejemplo.claude.service;

import com.ejemplo.claude.config.AnthropicProperties;
import com.ejemplo.claude.dto.ChatMessage;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ClaudeService {

    private final RestClient client;
    private final AnthropicProperties props;

    public ClaudeService(RestClient.Builder builder, AnthropicProperties props) {
        this.props = props;
        String apiKey = props.apiKey() == null ? "" : props.apiKey().trim();
        this.client = builder
                .baseUrl(props.baseUrl())
                .defaultHeader("x-api-key", apiKey)
                .defaultHeader("anthropic-version", "2023-06-01")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /** Pregunta simple de un solo turno. */
    public String preguntar(String prompt) {
        return chat(List.of(new ChatMessage("user", prompt)), null);
    }

    /** Conversacion con historial y system prompt opcional. */
    public String chat(List<ChatMessage> messages, String system) {
        if (props.apiKey() == null || props.apiKey().isBlank()) {
            throw new IllegalStateException("Falta la variable de entorno ANTHROPIC_API_KEY");
        }

        Map<String, Object> body = new HashMap<>();
        body.put("model", props.model());
        body.put("max_tokens", props.maxTokens());
        body.put("messages", messages);
        if (system != null && !system.isBlank()) {
            body.put("system", system);
        }

        JsonNode response = client.post()
                .uri("/v1/messages")
                .body(body)
                .retrieve()
                .body(JsonNode.class);

        StringBuilder texto = new StringBuilder();
        if (response != null && response.has("content")) {
            for (JsonNode bloque : response.get("content")) {
                if ("text".equals(bloque.path("type").asText())) {
                    texto.append(bloque.path("text").asText());
                }
            }
        }
        return texto.toString();
    }
}
