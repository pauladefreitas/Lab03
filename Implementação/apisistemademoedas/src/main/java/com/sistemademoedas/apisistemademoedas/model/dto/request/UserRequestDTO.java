package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@NotBlank String email,
                             @NotNull RoleEnum role,
                             @NotBlank  String nome) {}
