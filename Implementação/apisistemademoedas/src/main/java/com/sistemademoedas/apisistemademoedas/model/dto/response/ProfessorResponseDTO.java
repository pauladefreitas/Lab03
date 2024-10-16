package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import lombok.Builder;

@Builder
public record ProfessorResponseDTO(String departamento,
                                   int saldoMoedas,
                                   InstituicaoEnsino instituicaoEnsino) {
    public static ProfessorResponseDTO fromEntity(Professor professor) {
        return ProfessorResponseDTO.builder()
                .departamento(professor.getDepartamento())
                .saldoMoedas(professor.getSaldoMoedas())
                .instituicaoEnsino(professor.getInstituicaoEnsino())
                .build();
    }
}
