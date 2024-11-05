package com.sistemademoedas.apisistemademoedas.model.dto.response.security;

import com.sistemademoedas.apisistemademoedas.model.security.Role;

public record LoginResponseDTO(
        Long userId,

        String token,

        Role role
) {
}

