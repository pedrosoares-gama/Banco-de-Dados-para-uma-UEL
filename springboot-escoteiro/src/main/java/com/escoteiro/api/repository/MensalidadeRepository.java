package com.escoteiro.api.repository;

import com.escoteiro.api.model.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, Integer> {
}
