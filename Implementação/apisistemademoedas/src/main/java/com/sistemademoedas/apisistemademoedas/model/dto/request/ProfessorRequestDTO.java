package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(@NotBlank String departamento,
                                  @NotBlank String nome,
                                  @NotNull String CPF,
                                  @NotNull int saldoMoedas,
                                  @NotNull InstituicaoEnsinoRequestDTO instituicaoEnsino) {}
