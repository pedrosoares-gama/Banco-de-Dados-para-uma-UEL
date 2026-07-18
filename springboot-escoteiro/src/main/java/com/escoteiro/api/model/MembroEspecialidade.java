package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "membro_especialidades")
@Data
public class MembroEspecialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membro_especialidade")
    private Integer idMembroEspecialidade;

    @Column(name = "id_membro", nullable = false)
    private Integer idMembro;

    @Column(name = "id_especialidade", nullable = false)
    private Integer idEspecialidade;

    @Column(name = "data_conquista", nullable = false)
    private LocalDate dataConquista;

    @Column(length = 255)
    private String observacoes;
}
