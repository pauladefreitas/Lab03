package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GerenciadorVantagensRequestDTO(@NotNull Long vantagemId,
                                             @NotBlank String descricao) {
}
