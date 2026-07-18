package com.escoteiro.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "membros")
@Data
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membro")
    private Integer idMembro;

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 14, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_membro", nullable = false)
    private TipoMembro tipoMembro = TipoMembro.jovem;

    @Column(name = "id_secao")
    private Integer idSecao;

    @Column(name = "id_patrulha")
    private Integer idPatrulha;

    @Column(length = 20)
    private String telefone;

    @Column(length = 120)
    private String email;

    @Column(name = "nome_responsavel", length = 150)
    private String nomeResponsavel;

    @Column(name = "telefone_responsavel", length = 20)
    private String telefoneResponsavel;

    @Column(name = "data_ingresso", nullable = false)
    private LocalDate dataIngresso;

    @Column(nullable = false)
    private Boolean ativo = true;

    public enum TipoMembro {
        jovem, escotista, dirigente
    }
}
