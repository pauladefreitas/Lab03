package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.GerenciadorVantagens;

import lombok.Builder;

@Builder
public record GerenciadorVantagensResponseDTO(Long id,
                                              String descricao) {

    public static GerenciadorVantagensResponseDTO fromEntity(GerenciadorVantagens gerenciadorVantagens) {
        return GerenciadorVantagensResponseDTO.builder()
                .id(gerenciadorVantagens.getId())
                .descricao(gerenciadorVantagens.getVantagem().getDescricao())
                .build();
    }
}
