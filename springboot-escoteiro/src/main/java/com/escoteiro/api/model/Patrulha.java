package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patrulhas")
@Data
public class Patrulha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patrulha")
    private Integer idPatrulha;

    @Column(name = "id_secao", nullable = false)
    private Integer idSecao;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 100)
    private String lema;
}
