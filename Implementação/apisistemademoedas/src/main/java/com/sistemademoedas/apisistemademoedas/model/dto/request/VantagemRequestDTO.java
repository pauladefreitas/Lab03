package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VantagemRequestDTO(@NotBlank String nome,
                                 @NotNull Integer valor,
                                 @NotBlank String descricao) { }
