package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmpresaParceiraRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.EmpresaParceiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresas_parceiras")
public class EmpresaParceiraController {

    @Autowired
    private EmpresaParceiraService empresaParceiraService;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceira> findById(@PathVariable Long id) {
        EmpresaParceira obj = this.empresaParceiraService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmpresaParceira obj) {
        this.empresaParceiraService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaParceiraResponseDTO> update(@PathVariable Long id, @RequestBody EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        return ResponseEntity.ok(empresaParceiraService.update(id, empresaParceiraRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.empresaParceiraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
