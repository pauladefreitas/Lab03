package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
