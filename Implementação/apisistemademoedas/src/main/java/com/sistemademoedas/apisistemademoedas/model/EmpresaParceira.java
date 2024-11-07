package com.sistemademoedas.apisistemademoedas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmpresaParceiraRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_empresas_parceiras")
public class EmpresaParceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "empresaParceira", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore //com isso aqui, está retornando apenas id e nome da empresa
    private List<Vantagem> vantagens = new ArrayList<>();

    public static EmpresaParceira fromRequest(EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        EmpresaParceira empresaParceira = new EmpresaParceira();
        BeanUtils.copyProperties(empresaParceiraRequestDTO, empresaParceira);
        return empresaParceira;
    }

    public void update (EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        this.vantagens = empresaParceiraRequestDTO.vantagens() != null ? empresaParceiraRequestDTO.vantagens() : this.vantagens;
        this.nome = empresaParceiraRequestDTO.nome() != null ? empresaParceiraRequestDTO.nome() : this.nome;
    }
}
