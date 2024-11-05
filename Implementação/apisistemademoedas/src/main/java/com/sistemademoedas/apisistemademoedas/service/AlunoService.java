package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.AlunoNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.model.dto.request.AlunoRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.UserRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.AlunoResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.GerenciadorMoedasResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.AlunoRepository;
<<<<<<< HEAD
import com.sistemademoedas.apisistemademoedas.repository.GerenciadorMoedasRepository;
=======
import com.sistemademoedas.apisistemademoedas.repository.UserRepository;
>>>>>>> 221870883559fe18f2b4cbcdd02aee0b31dc683a
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
<<<<<<< HEAD
    private GerenciadorMoedasRepository gerenciadorMoedasRepository;
=======
    private UserRepository userRepository;
>>>>>>> 221870883559fe18f2b4cbcdd02aee0b31dc683a

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

    public List<AlunoResponseDTO> getAll() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponseDTO::fromEntity)
                .toList();
    }

    public List<GerenciadorMoedasResponseDTO> getAllTransactionsByAlunoId(Long id) {
        return gerenciadorMoedasRepository.findAllByAlunoId(id)
                .stream()
                .map(GerenciadorMoedasResponseDTO::fromEntity)
                .toList();
    }

    public AlunoResponseDTO update(Long id, AlunoRequestDTO alunoRequestDTO) {
        var aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado. Id " + id));
        aluno.update(alunoRequestDTO);
        alunoRepository.save(aluno);
        return AlunoResponseDTO.fromEntity(aluno);
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
