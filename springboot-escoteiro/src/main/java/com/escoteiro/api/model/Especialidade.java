package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "especialidades")
@Data
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Integer idEspecialidade;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 50)
    private String area;

    @Column(length = 255)
    private String descricao;
}
