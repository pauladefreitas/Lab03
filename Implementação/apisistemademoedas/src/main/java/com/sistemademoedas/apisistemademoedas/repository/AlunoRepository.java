package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
}
