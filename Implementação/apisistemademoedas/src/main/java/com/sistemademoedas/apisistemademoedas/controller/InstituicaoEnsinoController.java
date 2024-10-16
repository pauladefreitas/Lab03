package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.service.InstituicaoEnsinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoEnsinoController {

    @Autowired
    private InstituicaoEnsinoService instituicaoEnsinoService;

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoEnsino> findById(@PathVariable Long id) {
        InstituicaoEnsino obj = this.instituicaoEnsinoService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody InstituicaoEnsino obj) {
        this.instituicaoEnsinoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody InstituicaoEnsino obj) {
        obj.setId(id);
        this.instituicaoEnsinoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.instituicaoEnsinoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
