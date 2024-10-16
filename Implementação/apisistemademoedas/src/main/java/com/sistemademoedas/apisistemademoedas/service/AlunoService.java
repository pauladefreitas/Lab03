package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findByID(Long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElseThrow(() -> new RuntimeException("Aluno não encontrado. Id" + id));
    }

    @Transactional
    public Aluno create(Aluno aluno){
        aluno.setId(null);
        aluno = this.alunoRepository.save(aluno);
        return aluno;
    }

    @Transactional
    public Aluno update(Aluno aluno){
        Aluno newAluno = alunoRepository.findById(aluno.getId()).get();
        return this.alunoRepository.save(newAluno);
    }

    public void delete(Long id){
        alunoRepository.findById(id);
        try {
            alunoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir um aluno.");
        }
    }
}
