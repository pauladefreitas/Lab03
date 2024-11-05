package com.sistemademoedas.apisistemademoedas.model.dto.request.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TwoFactorCodeDTO(@NotNull int code,

                               @NotBlank String email) {
}