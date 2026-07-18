package com.escoteiro.api.repository;

import com.escoteiro.api.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Integer> {
}
