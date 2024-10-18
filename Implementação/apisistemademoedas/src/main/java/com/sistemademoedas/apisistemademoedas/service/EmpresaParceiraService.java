package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.EmpresaParceiraNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmpresaParceiraRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.EmpresaParceiraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaParceiraService {

    @Autowired
    private EmpresaParceiraRepository empresaParceiraRepository;

    public EmpresaParceira findByID(Long id){
        Optional<EmpresaParceira> empresaParceira = empresaParceiraRepository.findById(id);
        return empresaParceira.orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada. Id" + id));
    }

    @Transactional
    public EmpresaParceira create(EmpresaParceira empresaParceira){
        empresaParceira.setId(null);
        empresaParceira = this.empresaParceiraRepository.save(empresaParceira);
        return empresaParceira;
    }

    public List<EmpresaParceiraResponseDTO> getAll() {
        return empresaParceiraRepository.findAll()
                .stream()
                .map(EmpresaParceiraResponseDTO::fromEntity)
                .toList();
    }

    public EmpresaParceiraResponseDTO update(Long id, EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        var empresaParceira = empresaParceiraRepository.findById(id)
                .orElseThrow(() -> new EmpresaParceiraNotFoundException("Empresa parceira não encontrada. Id " + id));
        empresaParceira.update(empresaParceiraRequestDTO);
        empresaParceiraRepository.save(empresaParceira);
        return EmpresaParceiraResponseDTO.fromEntity(empresaParceira);
    }

    public void delete(Long id){
        empresaParceiraRepository.findById(id);
        try {
            empresaParceiraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir uma empresa parceira.");
        }
    }
}
