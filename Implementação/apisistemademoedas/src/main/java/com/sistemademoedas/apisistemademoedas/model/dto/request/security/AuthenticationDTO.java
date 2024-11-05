package com.sistemademoedas.apisistemademoedas.model.dto.request.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank @Email
                                String email,

                                @NotBlank
                                String password) {
}
