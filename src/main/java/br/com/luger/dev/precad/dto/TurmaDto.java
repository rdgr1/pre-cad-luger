package br.com.luger.dev.precad.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TurmaDto {

    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private String turno;
    private Long numeroVagas;
    private String codigoClasse;
    private Double cargaHoraria;
    private String localizacao;
    private String sala;
}
