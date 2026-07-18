package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "secoes")
@Data
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secao")
    private Integer idSecao;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(name = "faixa_etaria_min", nullable = false)
    private Integer faixaEtariaMin;

    @Column(name = "faixa_etaria_max", nullable = false)
    private Integer faixaEtariaMax;

    @Column(length = 255)
    private String descricao;
}
