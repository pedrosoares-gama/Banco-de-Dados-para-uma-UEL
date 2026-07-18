package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "mensalidades")
@Data
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensalidade")
    private Integer idMensalidade;

    @Column(name = "id_membro", nullable = false)
    private Integer idMembro;

    @Column(name = "mes_referencia", nullable = false)
    private LocalDate mesReferencia;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento = StatusPagamento.pendente;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    public enum StatusPagamento {
        pendente, pago, atrasado, isento
    }

    public enum FormaPagamento {
        pix, dinheiro, transferencia, cartao, outro
    }
}
