package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.Professor;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record InstituicaoEnsinoRequestDTO(@NotBlank String nome,
                                          List<Professor> professores) {}