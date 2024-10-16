package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import com.sistemademoedas.apisistemademoedas.repository.VantagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    public Vantagem findByID(Long id){
        Optional<Vantagem> vantagem = vantagemRepository.findById(id);
        return vantagem.orElseThrow(() -> new RuntimeException("Vantagem não encontrado. Id" + id));
    }

    @Transactional
    public Vantagem create(Vantagem vantagem){
        vantagem.setId(null);
        vantagem = this.vantagemRepository.save(vantagem);
        return vantagem;
    }

    @Transactional
    public Vantagem update(Vantagem vantagem){
        Vantagem newVantagem = vantagemRepository.findById(vantagem.getId()).get();
        return this.vantagemRepository.save(newVantagem);
    }

    public void delete(Long id){
        vantagemRepository.findById(id);
        try {
            vantagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir uma empresa parceira.");
        }
    }
}
