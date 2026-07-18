package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(nullable = false, length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEvento tipo;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(length = 200)
    private String local;

    @Column(name = "valor_inscricao", precision = 10, scale = 2)
    private BigDecimal valorInscricao = BigDecimal.ZERO;

    @Column(name = "vagas_limite")
    private Integer vagasLimite;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public enum TipoEvento {
        acampamento, distrital, regional, nacional, internacional, outro
    }
}
