package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRequestDTO(@NotBlank @Email String email,
                              @NotBlank String RG,
                              @NotBlank String endereco,
                              @NotNull InstituicaoEnsino instituicaoEnsino,
                              @NotBlank String curso,
                              @NotNull int saldoMoedas) {
}
