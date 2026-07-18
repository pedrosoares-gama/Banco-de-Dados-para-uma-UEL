package com.escoteiro.api.repository;

import com.escoteiro.api.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
}
