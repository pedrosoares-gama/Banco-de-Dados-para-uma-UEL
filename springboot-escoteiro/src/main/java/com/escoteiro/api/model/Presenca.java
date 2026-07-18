package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "presencas")
@Data
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presenca")
    private Integer idPresenca;

    @Column(name = "id_atividade", nullable = false)
    private Integer idAtividade;

    @Column(name = "id_membro", nullable = false)
    private Integer idMembro;

    @Column(nullable = false)
    private Boolean presente = false;

    @Column(length = 255)
    private String justificativa;
}
