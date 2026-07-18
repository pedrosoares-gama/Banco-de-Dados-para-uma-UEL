package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "progressao_classes")
@Data
public class ProgressaoClasse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progressao")
    private Integer idProgressao;

    @Column(name = "id_membro", nullable = false)
    private Integer idMembro;

    @Column(name = "nome_etapa", nullable = false, length = 100)
    private String nomeEtapa;

    @Column(name = "data_conquista", nullable = false)
    private LocalDate dataConquista;

    @Column(length = 255)
    private String observacoes;
}
