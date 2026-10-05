package com.ejemplo.claude.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/** Un mensaje de la conversacion. role = "user" o "assistant". */
public record ChatMessage(
        @NotBlank @Pattern(regexp = "user|assistant", message = "role debe ser 'user' o 'assistant'") String role,
        @NotBlank String content) {
}
