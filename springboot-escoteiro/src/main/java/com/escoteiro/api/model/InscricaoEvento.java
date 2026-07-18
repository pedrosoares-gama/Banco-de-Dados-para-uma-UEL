package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "inscricoes_eventos")
@Data
public class InscricaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscricao")
    private Integer idInscricao;

    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;

    @Column(name = "id_membro", nullable = false)
    private Integer idMembro;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento = StatusPagamento.pendente;

    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago = BigDecimal.ZERO;

    @Column(length = 255)
    private String observacoes;

    public enum StatusPagamento {
        pendente, pago, parcial, isento
    }
}
