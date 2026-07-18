package com.escoteiro.api.repository;

import com.escoteiro.api.model.InscricaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoEventoRepository extends JpaRepository<InscricaoEvento, Integer> {
}
