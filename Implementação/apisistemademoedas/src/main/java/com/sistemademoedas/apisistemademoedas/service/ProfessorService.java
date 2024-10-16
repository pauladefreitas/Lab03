package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.model.Professor;
import com.sistemademoedas.apisistemademoedas.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor findByID(Long id){
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.orElseThrow(() -> new RuntimeException("Professor não encontrado. Id" + id));
    }

    @Transactional
    public Professor create(Professor professor){
        professor.setId(null);
        professor = this.professorRepository.save(professor);
        return professor;
    }

    @Transactional
    public Professor update(Professor professor){
        Professor newProfessor = professorRepository.findById(professor.getId()).get();
        return this.professorRepository.save(newProfessor);
    }

    public void delete(Long id){
        professorRepository.findById(id);
        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir um professor.");
        }
    }
}
