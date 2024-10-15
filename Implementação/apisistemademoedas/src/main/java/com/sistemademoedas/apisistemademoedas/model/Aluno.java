package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.dto.request.AlunoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends User {

    @Column(unique = true)
    private String email;
    private String RG;
    private String endereco;
    private int saldoMoedas;

    @ManyToOne
    @JoinColumn(name = "instituicoes_id")
    private InstituicaoEnsino instituicaoEnsino;

    private String curso;

    public static Aluno fromRequest(AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(alunoRequestDTO, aluno);
        return aluno;
    }

    public void update(AlunoRequestDTO alunoRequestDTO) {
        this.curso = alunoRequestDTO.curso() != null ? alunoRequestDTO.curso() : this.curso;
        this.RG = alunoRequestDTO.RG() != null ? alunoRequestDTO.RG() : this.RG;
        this.email = alunoRequestDTO.email() != null ? alunoRequestDTO.email() : this.email;
        this.saldoMoedas = alunoRequestDTO.saldoMoedas() != 0 ? alunoRequestDTO.saldoMoedas() : this.saldoMoedas;
        this.instituicaoEnsino = alunoRequestDTO.instituicaoEnsino() != null ? alunoRequestDTO.instituicaoEnsino() : this.instituicaoEnsino;
        this.endereco = alunoRequestDTO.endereco() != null ? alunoRequestDTO.endereco() : this.endereco;
    }
}