package com.ejemplo.claude.dto;

import jakarta.validation.constraints.NotBlank;

public record PromptRequest(@NotBlank String prompt) {
}
