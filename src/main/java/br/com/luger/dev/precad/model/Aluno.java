package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "documento_identidade", length = Integer.MAX_VALUE)
    private String documentoIdentidade;//-

    @Column(name = "uf_documento_identidade", length = Integer.MAX_VALUE)
    private String ufDocumentoIdentidade;//-

    @Column(name = "identidade_possui_cpf")
    private Boolean identidadePossuiCpf;//-


    @Column(name = "orgao_expedidor_documento_identidade", length = Integer.MAX_VALUE)
    private String orgaoExpedidorDocumentoIdentidade;//-

    @Column(name = "nome", length = Integer.MAX_VALUE)
    private String nome;//-

    @Column(name = "sexo", length = Integer.MAX_VALUE)
    private String sexo;//-

    @Column(name = "nome_social", length = Integer.MAX_VALUE)
    private String nomeSocial;//-

    @Column(name = "nome_pai", length = Integer.MAX_VALUE)
    private String nomePai;//-

    @Column(name = "nome_mae", length = Integer.MAX_VALUE)
    private String nomeMae;//-

    @Column(name = "cpf", length = Integer.MAX_VALUE)
    private String cpf;//-

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;//-

    @Column(name = "naturalidade_municipio", length = Integer.MAX_VALUE)
    private String naturalidadeMunicipio;//-

    @Column(name = "naturalidade_estado", length = Integer.MAX_VALUE)
    private String naturalidadeEstado;//-

    @Column(name = "naturalidade_pais")
    private UUID naturalidadePais;//-

    @Column(name = "titulo_eleitor", length = Integer.MAX_VALUE)
    private String tituloEleitor;//-

    @Column(name = "telefone1", length = Integer.MAX_VALUE)
    private String telefone1;//-

    @Column(name = "telefone2", length = Integer.MAX_VALUE)
    private String telefone2;//-

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;//-


    @Column(name = "endereco_cep", length = Integer.MAX_VALUE)
    private String enderecoCep;//-

    @Column(name = "endereco", length = Integer.MAX_VALUE)
    private String endereco;//-

    @Column(name = "endereco_bairro", length = Integer.MAX_VALUE)
    private String enderecoBairro;//-

    @Column(name = "endereco_municipio", length = Integer.MAX_VALUE)
    private String enderecoMunicipio;//-

    @Column(name = "endereco_uf", length = Integer.MAX_VALUE)
    private String enderecoUf;//-

    @Column(name = "endereco_complemento", length = Integer.MAX_VALUE)
    private String enderecoComplemento;//-

    @Column(name = "endereco_numero", length = Integer.MAX_VALUE)
    private String enderecoNumero;//-
    @Column(name = "empresa_atual", length = Integer.MAX_VALUE)
    private String empresaAtual;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "cnv", length = Integer.MAX_VALUE)
    private String cnv;

    @Column(name = "registro", length = Integer.MAX_VALUE)
    private String registro;

    @Column(name = "data_expedicao_cnv")
    private LocalDate dataExpedicaoCnv;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocumentoIdentidade() {
        return documentoIdentidade;
    }

    public void setDocumentoIdentidade(String documentoIdentidade) {
        this.documentoIdentidade = documentoIdentidade != null ? documentoIdentidade.toUpperCase() : null;
    }

    public String getUfDocumentoIdentidade() {
        return ufDocumentoIdentidade;
    }

    public void setUfDocumentoIdentidade(String ufDocumentoIdentidade) {
        this.ufDocumentoIdentidade = ufDocumentoIdentidade != null ? ufDocumentoIdentidade.toUpperCase() : null;
    }

    public Boolean getIdentidadePossuiCpf() {
        return identidadePossuiCpf;
    }

    public void setIdentidadePossuiCpf(Boolean identidadePossuiCpf) {
        this.identidadePossuiCpf = identidadePossuiCpf;
    }

    public String getOrgaoExpedidorDocumentoIdentidade() {
        return orgaoExpedidorDocumentoIdentidade;
    }

    public void setOrgaoExpedidorDocumentoIdentidade(String orgaoExpedidorDocumentoIdentidade) {
        this.orgaoExpedidorDocumentoIdentidade = orgaoExpedidorDocumentoIdentidade != null ? orgaoExpedidorDocumentoIdentidade.toUpperCase() : null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : null;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo != null ? sexo.toUpperCase() : null;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial != null ? nomeSocial.toUpperCase() : null;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai != null ? nomePai.toUpperCase() : null;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae != null ? nomeMae.toUpperCase() : null;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf != null ? cpf.toUpperCase() : null;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidadeMunicipio() {
        return naturalidadeMunicipio;
    }

    public void setNaturalidadeMunicipio(String naturalidadeMunicipio) {
        this.naturalidadeMunicipio = naturalidadeMunicipio != null ? naturalidadeMunicipio.toUpperCase() : null;
    }

    public String getNaturalidadeEstado() {
        return naturalidadeEstado;
    }

    public void setNaturalidadeEstado(String naturalidadeEstado) {
        this.naturalidadeEstado = naturalidadeEstado != null ? naturalidadeEstado.toUpperCase() : null;
    }

    public UUID getNaturalidadePais() {
        return naturalidadePais;
    }

    public void setNaturalidadePais(UUID naturalidadePais) {
        this.naturalidadePais = naturalidadePais;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor != null ? tituloEleitor.toUpperCase() : null;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1 != null ? telefone1.toUpperCase() : null;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2 != null ? telefone2.toUpperCase() : null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.toUpperCase() : null;
    }

    public String getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep != null ? enderecoCep.toUpperCase() : null;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco != null ? endereco.toUpperCase() : null;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro != null ? enderecoBairro.toUpperCase() : null;
    }

    public String getEnderecoMunicipio() {
        return enderecoMunicipio;
    }

    public void setEnderecoMunicipio(String enderecoMunicipio) {
        this.enderecoMunicipio = enderecoMunicipio != null ? enderecoMunicipio.toUpperCase() : null;
    }

    public String getEnderecoUf() {
        return enderecoUf;
    }

    public void setEnderecoUf(String enderecoUf) {
        this.enderecoUf = enderecoUf != null ? enderecoUf.toUpperCase() : null;
    }

    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }

    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento != null ? enderecoComplemento.toUpperCase() : null;
    }

    public String getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero != null ? enderecoNumero.toUpperCase() : null;
    }

    public String getEmpresaAtual() {
        return empresaAtual;
    }

    public void setEmpresaAtual(String empresaAtual) {
        this.empresaAtual = empresaAtual != null ? empresaAtual.toUpperCase() : null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status != null ? status.toUpperCase() : null;
    }

    public String getCnv() {
        return cnv;
    }

    public void setCnv(String cnv) {
        this.cnv = cnv != null ? cnv.toUpperCase() : null;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro != null ? registro.toUpperCase() : null;
    }

    public LocalDate getDataExpedicaoCnv() {
        return dataExpedicaoCnv;
    }

    public void setDataExpedicaoCnv(LocalDate dataExpedicaoCnv) {
        this.dataExpedicaoCnv = dataExpedicaoCnv;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Aluno aluno = (Aluno) o;
        return getId() != null && Objects.equals(getId(), aluno.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}