package com.escoteiro.api.repository;

import com.escoteiro.api.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
}
