package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;

public record UserRequestDTO(String email,
                              RoleEnum role,
                              String nome,
                              Long id) {}
