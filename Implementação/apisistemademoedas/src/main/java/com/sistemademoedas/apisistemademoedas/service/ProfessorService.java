package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.ProfessorNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import com.sistemademoedas.apisistemademoedas.model.dto.request.ProfessorRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.ProfessorResponseDTO;
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

    public ProfessorResponseDTO update(Long id, ProfessorRequestDTO professorRequestDTO) {
        var professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor não encontrado. Id " + id));
        professor.update(professorRequestDTO);
        professorRepository.save(professor);
        return ProfessorResponseDTO.fromEntity(professor);
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
