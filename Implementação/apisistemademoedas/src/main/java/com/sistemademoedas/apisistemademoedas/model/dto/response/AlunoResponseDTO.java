package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import lombok.Builder;

@Builder
public record AlunoResponseDTO(String email,
                               String RG,
                               String endereco,
                               int saldoMoedas,
                               InstituicaoEnsino instituicaoEnsino,
                               String curso) {
    public static AlunoResponseDTO fromEntity(Aluno aluno) {
        return AlunoResponseDTO.builder()
                .email(aluno.getEmail())
                .RG(aluno.getRG())
                .endereco(aluno.getEndereco())
                .saldoMoedas(aluno.getSaldoMoedas())
                .curso(aluno.getCurso())
                .instituicaoEnsino(aluno.getInstituicaoEnsino())
                .build();
    }
}
