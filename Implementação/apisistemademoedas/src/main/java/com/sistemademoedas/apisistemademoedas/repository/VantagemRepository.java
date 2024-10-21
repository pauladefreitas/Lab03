package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem,Long> {
}
