package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VantagemRequestDTO(@NotBlank String nome,
                                 @NotNull int valor,
                                 @NotBlank String descricao) { }
