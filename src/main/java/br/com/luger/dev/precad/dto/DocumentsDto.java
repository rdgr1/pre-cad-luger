package br.com.luger.dev.precad.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DocumentsDto {

    private String documentType;


    private UUID idCadastroCurso;// mandar cpf na url


    private String content;//CONTEUDO EM BASE64


    private String documentExtencao;//PNG, JPG, PDF


    private String replacementAdditionalText;// SE O DOCUMENTO VOLTAR PARA UPDATE


    private LocalDate validity;// SE O DOCUMENTO TIVER VALIDADE
}
