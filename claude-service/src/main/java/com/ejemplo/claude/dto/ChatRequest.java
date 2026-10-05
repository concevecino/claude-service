package com.ejemplo.claude.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * messages: historial completo (el ultimo debe ser del usuario).
 * system: instrucciones opcionales para Claude.
 */
public record ChatRequest(@NotEmpty @Valid List<ChatMessage> messages, String system) {
}
