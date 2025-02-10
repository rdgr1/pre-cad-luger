package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = Integer.MAX_VALUE)
    private String titulo;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "carga_horaria")
    private Double cargaHoraria;

    @Column(name = "data_inaugural")
    private LocalDate dataInaugural;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "te_id")
    private Long teId;
}