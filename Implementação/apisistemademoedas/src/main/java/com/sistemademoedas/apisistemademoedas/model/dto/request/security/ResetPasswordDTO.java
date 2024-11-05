package com.sistemademoedas.apisistemademoedas.model.dto.request.security;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDTO(@NotBlank String password,

                               @NotBlank String email) {
}