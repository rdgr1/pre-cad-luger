package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "classe")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Curso")
    @ToString.Exclude
    private Curso idCurso;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "turno", length = Integer.MAX_VALUE)
    private String turno;

    @Column(name = "te_id")
    private Long teId;

    @Column(name = "numero_vagas")
    private Long numeroVagas;

    @Column(name = "codigo_classe", length = Integer.MAX_VALUE)
    private String codigoClasse;

    @Column(name = "carga_horaria")
    private Double cargaHoraria;

    @Column(name = "localizacao", length = Integer.MAX_VALUE)
    private String localizacao;

    @Column(name = "sala", length = Integer.MAX_VALUE)
    private String sala;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao != null ? descricao.toUpperCase() : null;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status != null ? status.toUpperCase() : null;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno != null ? turno.toUpperCase() : null;
    }

    public Long getTeId() {
        return teId;
    }

    public void setTeId(Long teId) {
        this.teId = teId;
    }

    public Long getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(Long numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    public String getCodigoClasse() {
        return codigoClasse;
    }

    public void setCodigoClasse(String codigoClasse) {
        this.codigoClasse = codigoClasse != null ? codigoClasse.toUpperCase() : null;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao != null ? localizacao.toUpperCase() : null;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala != null ? sala.toUpperCase() : null;
    }
}