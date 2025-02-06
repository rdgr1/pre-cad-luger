package br.com.luger.dev.precad.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CadPessoalDTO {

    private UUID curso;
    private UUID classe;
    private String email;
    private String naturalidadeMunicipio;//-
    private String naturalidadeEstado;//-
    private UUID naturalidadePais;//-
    private String tipoIdentidade;

    private String tituloEleitor;//-
    private String documentoIdentidade;
    private String ufDocumentoIdentidade;
    private String orgaoExpedidorDocumentoIdentidade;
    private Boolean identidadePossuiCpf;
    private String nome;//-
    private String cpf;//-
    private String sexo;//-
    private String nomeSocial;//-
    private String nomePai;//-
    private String nomeMae;//-
    private LocalDate dataNascimento;//-
}
