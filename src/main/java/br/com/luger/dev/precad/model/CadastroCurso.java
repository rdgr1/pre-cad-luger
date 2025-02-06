package br.com.luger.dev.precad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cadastro_curso")
public class CadastroCurso {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Curso")
    @ToString.Exclude
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Classe")
    @ToString.Exclude
    private Classe classe;

    @Column(name = "nome", length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "sexo", length = Integer.MAX_VALUE)
    private String sexo;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nome_social", length = Integer.MAX_VALUE)
    private String nomeSocial;

    @Column(name = "nome_mae", length = Integer.MAX_VALUE)
    private String nomeMae;

    @Column(name = "nome_pai", length = Integer.MAX_VALUE)
    private String nomePai;

    @Column(name = "documento_identidade", length = Integer.MAX_VALUE)
    private String documentoIdentidade;//-

    @Column(name = "uf_documento_identidade", length = Integer.MAX_VALUE)
    private String ufDocumentoIdentidade;//-

    @Column(name = "orgao_expedidor_documento_identidade", length = Integer.MAX_VALUE)
    private String orgaoExpedidorDocumentoIdentidade;//-

    @Column(name = "naturalidade_pais")
    private UUID naturalidadePais;

    @Column(name = "naturalidade_estado", length = Integer.MAX_VALUE)
    private String naturalidadeEstado;

    @Column(name = "naturalidade_municipio", length = Integer.MAX_VALUE)
    private String naturalidadeMunicipio;

    @Column(name = "endereco", length = Integer.MAX_VALUE)
    private String endereco;

    @Column(name = "endereco_bairro", length = Integer.MAX_VALUE)
    private String enderecoBairro;

    @Column(name = "endereco_municipio", length = Integer.MAX_VALUE)
    private String enderecoMunicipio;

    @Column(name = "endereco_cep", length = Integer.MAX_VALUE)
    private String enderecoCep;

    @Column(name = "telefone1", length = Integer.MAX_VALUE)
    private String telefone1;

    @Column(name = "telefone2", length = Integer.MAX_VALUE)
    private String telefone2;

    @Column(name = "cpf", length = Integer.MAX_VALUE)
    private String cpf;

    @Column(name = "registro", length = Integer.MAX_VALUE)
    private String registro;

    @Column(name = "data_ultimo_curso")
    private LocalDate dataUltimoCurso;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "tipo_processo", length = Integer.MAX_VALUE)
    private String tipoProcesso;

    @Column(name = "endereco_estado", length = Integer.MAX_VALUE)
    private String enderecoUf;

    @Column(name = "token", length = Integer.MAX_VALUE)
    private String token;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "ts_aceite_termo")
    private OffsetDateTime tsAceiteTermo;

    @Column(name = "tipo_identidade", length = Integer.MAX_VALUE)
    private String tipoIdentidade;

    @Column(name = "identidade_possui_cpf")
    private Boolean identidadePossuiCpf;//-

    @Column(name = "empresa_atual", length = Integer.MAX_VALUE)
    private String empresaAtual;

    @Column(name = "cnv", length = Integer.MAX_VALUE)
    private String cnv;

    @Column(name = "data_expedicao_cnv")
    private LocalDate dataExpedicaoCnv;

    @Column(name = "endereco_complemento", length = 70)
    private String enderecoComplemento;


    @Column(name = "titulo_eleitor", length = Integer.MAX_VALUE)
    private String tituloEleitor;

    @Column(name = "endereco_numero", length = Integer.MAX_VALUE)
    private String enderecoNumero;

    @Column(name = "te_id")
    private Long teId;


    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : null;
    }

    // Setter method for 'sexo' field
    public void setSexo(String sexo) {
        this.sexo = sexo != null ? sexo.toUpperCase() : null;
    }

    // Setter method for 'nomeSocial' field
    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial != null ? nomeSocial.toUpperCase() : null;
    }

    // Setter method for 'nomeMae' field
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae != null ? nomeMae.toUpperCase() : null;
    }

    // Setter method for 'nomePai' field
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai != null ? nomePai.toUpperCase() : null;
    }

    // Setter method for 'documentoIdentidade' field
    public void setDocumentoIdentidade(String documentoIdentidade) {
        this.documentoIdentidade = documentoIdentidade != null ? documentoIdentidade.toUpperCase() : null;
    }

    // Setter method for 'ufDocumentoIdentidade' field
    public void setUfDocumentoIdentidade(String ufDocumentoIdentidade) {
        this.ufDocumentoIdentidade = ufDocumentoIdentidade != null ? ufDocumentoIdentidade.toUpperCase() : null;
    }

    // Setter method for 'orgaoExpedidorDocumentoIdentidade' field
    public void setOrgaoExpedidorDocumentoIdentidade(String orgaoExpedidorDocumentoIdentidade) {
        this.orgaoExpedidorDocumentoIdentidade = orgaoExpedidorDocumentoIdentidade != null ? orgaoExpedidorDocumentoIdentidade.toUpperCase() : null;
    }

    // Setter method for 'naturalidadeEstado' field
    public void setNaturalidadeEstado(String naturalidadeEstado) {
        this.naturalidadeEstado = naturalidadeEstado != null ? naturalidadeEstado.toUpperCase() : null;
    }

    // Setter method for 'naturalidadeMunicipio' field
    public void setNaturalidadeMunicipio(String naturalidadeMunicipio) {
        this.naturalidadeMunicipio = naturalidadeMunicipio != null ? naturalidadeMunicipio.toUpperCase() : null;
    }

    // Setter method for 'endereco' field
    public void setEndereco(String endereco) {
        this.endereco = endereco != null ? endereco.toUpperCase() : null;
    }

    // Setter method for 'enderecoBairro' field
    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro != null ? enderecoBairro.toUpperCase() : null;
    }

    // Setter method for 'enderecoMunicipio' field
    public void setEnderecoMunicipio(String enderecoMunicipio) {
        this.enderecoMunicipio = enderecoMunicipio != null ? enderecoMunicipio.toUpperCase() : null;
    }

    // Setter method for 'enderecoCep' field
    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep != null ? enderecoCep.toUpperCase() : null;
    }

    // Setter method for 'telefone1' field
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1 != null ? telefone1.toUpperCase() : null;
    }

    // Setter method for 'telefone2' field
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2 != null ? telefone2.toUpperCase() : null;
    }

    // Setter method for 'cpf' field
    public void setCpf(String cpf) {
        this.cpf = cpf != null ? cpf.toUpperCase() : null;
    }

    // Setter method for 'registro' field
    public void setRegistro(String registro) {
        this.registro = registro != null ? registro.toUpperCase() : null;
    }

    // Setter method for 'email' field
    public void setEmail(String email) {
        this.email = email != null ? email.toUpperCase() : null;
    }

    // Setter method for 'tipoProcesso' field
    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso != null ? tipoProcesso.toUpperCase() : null;
    }

    // Setter method for 'enderecoUf' field
    public void setEnderecoUf(String enderecoUf) {
        this.enderecoUf = enderecoUf != null ? enderecoUf.toUpperCase() : null;
    }

    // Setter method for 'token' field
    public void setToken(String token) {
        this.token = token != null ? token.toUpperCase() : null;
    }

    // Setter method for 'status' field
    public void setStatus(String status) {
        this.status = status != null ? status.toUpperCase() : null;
    }

    // Setter method for 'tipoIdentidade' field
    public void setTipoIdentidade(String tipoIdentidade) {
        this.tipoIdentidade = tipoIdentidade != null ? tipoIdentidade.toUpperCase() : null;
    }

    // Setter method for 'empresaAtual' field
    public void setEmpresaAtual(String empresaAtual) {
        this.empresaAtual = empresaAtual != null ? empresaAtual.toUpperCase() : null;
    }

    // Setter method for 'cnv' field
    public void setCnv(String cnv) {
        this.cnv = cnv != null ? cnv.toUpperCase() : null;
    }

    // Setter method for 'enderecoComplemento' field
    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento != null ? enderecoComplemento.toUpperCase() : null;
    }

    // Setter method for 'tituloEleitor' field
    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor != null ? tituloEleitor.toUpperCase() : null;
    }

    // Setter method for 'enderecoNumero' field
    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero != null ? enderecoNumero.toUpperCase() : null;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CadastroCurso that = (CadastroCurso) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}