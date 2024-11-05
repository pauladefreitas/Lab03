package com.sistemademoedas.apisistemademoedas.model.dto.response.security;

import com.sistemademoedas.apisistemademoedas.model.security.Role;

public record UserResponseDTO(Long userId,

                              String email,

                              Role role) {
}
