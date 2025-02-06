package br.com.luger.dev.precad.dto;

import lombok.Data;

@Data
public class EndContatDTO {
    public String cpf;
    private String token;
    private String telefone1;//-
    private String telefone2;//-
    private String enderecoCep;//-
    private String endereco;//-
    private String enderecoBairro;//-
    private String enderecoMunicipio;//-
    private String enderecoUf;//-
    private String enderecoComplemento;//-
    private String enderecoNumero;//-
}
