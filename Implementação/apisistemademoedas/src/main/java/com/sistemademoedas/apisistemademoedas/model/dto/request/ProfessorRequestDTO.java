package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(@NotBlank String departamento,
                                  @NotNull int saldoMoedas,
                                  @NotNull InstituicaoEnsino instituicaoEnsino) {}
