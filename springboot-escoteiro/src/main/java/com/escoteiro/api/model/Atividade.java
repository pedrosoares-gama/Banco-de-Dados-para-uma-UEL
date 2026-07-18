package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "atividades")
@Data
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Integer idAtividade;

    @Column(name = "id_secao")
    private Integer idSecao;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "data_atividade", nullable = false)
    private LocalDate dataAtividade;

    @Column(length = 150)
    private String local;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}
