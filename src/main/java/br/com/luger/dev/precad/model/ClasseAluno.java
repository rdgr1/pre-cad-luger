package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "classe_aluno")
public class ClasseAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno idAluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "te_id")
    private Long teId;

    @Column(name = "data_matricula")
    private OffsetDateTime dataMatricula;

    @Column(name = "matriculado_por")
    private UUID matriculadoPor;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "pagamento_valor")
    private Double pagamentoValor;

    @Column(name = "pago_em")
    private OffsetDateTime pagoEm;

    @Column(name = "metodo_pagamento", length = Integer.MAX_VALUE)
    private String metodoPagamento;

    @Column(name = "numero_pagamento", length = Integer.MAX_VALUE)
    private String numeroPagamento;

    @Column(name = "numero_parcelas")
    private Long numeroParcelas;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Aluno getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Aluno idAluno) {
        this.idAluno = idAluno;
    }

    public Classe getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Classe idClasse) {
        this.idClasse = idClasse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTeId() {
        return teId;
    }

    public void setTeId(Long teId) {
        this.teId = teId;
    }

    public OffsetDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(OffsetDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public UUID getMatriculadoPor() {
        return matriculadoPor;
    }

    public void setMatriculadoPor(UUID matriculadoPor) {
        this.matriculadoPor = matriculadoPor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Double getPagamentoValor() {
        return pagamentoValor;
    }

    public void setPagamentoValor(Double pagamentoValor) {
        this.pagamentoValor = pagamentoValor;
    }

    public OffsetDateTime getPagoEm() {
        return pagoEm;
    }

    public void setPagoEm(OffsetDateTime pagoEm) {
        this.pagoEm = pagoEm;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroPagamento() {
        return numeroPagamento;
    }

    public void setNumeroPagamento(String numeroPagamento) {
        this.numeroPagamento = numeroPagamento;
    }

    public Long getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Long numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}