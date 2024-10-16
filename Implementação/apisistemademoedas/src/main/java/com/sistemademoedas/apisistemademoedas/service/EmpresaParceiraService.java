package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.repository.EmpresaParceiraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaParceiraService {

    @Autowired
    private EmpresaParceiraRepository empresaParceiraRepository;

    public EmpresaParceira findByID(Long id){
        Optional<EmpresaParceira> empresaParceira = empresaParceiraRepository.findById(id);
        return empresaParceira.orElseThrow(() -> new RuntimeException("Empresa parceira não encontrado. Id" + id));
    }

    @Transactional
    public EmpresaParceira create(EmpresaParceira empresaParceira){
        empresaParceira.setId(null);
        empresaParceira = this.empresaParceiraRepository.save(empresaParceira);
        return empresaParceira;
    }

    @Transactional
    public EmpresaParceira update(EmpresaParceira empresaParceira){
        EmpresaParceira newempresaParceira = empresaParceiraRepository.findById(empresaParceira.getId()).get();
        return this.empresaParceiraRepository.save(newempresaParceira);
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
