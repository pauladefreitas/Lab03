package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.dto.request.AlunoRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ALUNO")
public class Aluno extends User {

    @Column(unique = true)
    private String email;
    private String RG;
    private String endereco;
    private Integer saldoMoedas;

    private String curso;

    public static Aluno fromRequest(AlunoRequestDTO alunoRequestDTO, InstituicaoEnsino instituicaoEnsino) {
        Aluno aluno = new Aluno();
        aluno.setInstituicaoEnsino(instituicaoEnsino);
        BeanUtils.copyProperties(alunoRequestDTO, aluno);
        return aluno;
    }

    public void update(AlunoRequestDTO alunoRequestDTO) {

        this.CPF = alunoRequestDTO.CPF() != null ? alunoRequestDTO.CPF() : this.CPF;
        this.nome = alunoRequestDTO.nome() != null ? alunoRequestDTO.nome() : this.nome;
        this.curso = alunoRequestDTO.curso() != null ? alunoRequestDTO.curso() : this.curso;
        this.RG = alunoRequestDTO.RG() != null ? alunoRequestDTO.RG() : this.RG;
        this.email = alunoRequestDTO.email() != null ? alunoRequestDTO.email() : this.email;
        this.saldoMoedas = alunoRequestDTO.saldoMoedas() != null ? alunoRequestDTO.saldoMoedas() : this.saldoMoedas;
        if (alunoRequestDTO.instituicaoEnsino() != null) {
            this.instituicaoEnsino.update(alunoRequestDTO.instituicaoEnsino());
        }
        this.endereco = alunoRequestDTO.endereco() != null ? alunoRequestDTO.endereco() : this.endereco;
    }
}