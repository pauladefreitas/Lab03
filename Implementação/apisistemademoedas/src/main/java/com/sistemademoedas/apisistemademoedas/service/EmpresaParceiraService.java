package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.EmpresaParceiraNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmpresaParceiraRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.VantagemRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.VantagemResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.EmpresaParceiraRepository;
import com.sistemademoedas.apisistemademoedas.repository.VantagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpresaParceiraService {

    @Autowired
    private EmpresaParceiraRepository empresaParceiraRepository;

    @Autowired
    private VantagemService vantagemService;

    @Autowired
    private VantagemRepository vantagemRepository;

    public EmpresaParceira findByID(Long id){
        Optional<EmpresaParceira> empresaParceira = empresaParceiraRepository.findById(id);
        return empresaParceira.orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada. Id" + id));
    }

    @Transactional
    public EmpresaParceiraResponseDTO create(EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        var empresaParceira = EmpresaParceira.fromRequest(empresaParceiraRequestDTO);
        empresaParceiraRepository.save(empresaParceira);
        return EmpresaParceiraResponseDTO.fromEntity(empresaParceira);
    }

    @Transactional
    public void addVantagem(Long id, VantagemRequestDTO VantagemRequestDTO) {
        var empresaParceira = empresaParceiraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa parceira não encontrada. Id" + id));

        Vantagem vantagem = Vantagem.fromRequest(VantagemRequestDTO, empresaParceira);
        vantagemService.create(vantagem);
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
