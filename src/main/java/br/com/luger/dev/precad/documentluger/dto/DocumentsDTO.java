package br.com.luger.dev.precad.documentluger.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DocumentsDTO {

    private String documentType;//PRE,INI,SOL,EP,CAN,ENV,PEN,FIN,A
    private UUID idCadastroCurso;
    private String content;// base64 encoded
    private String documentExtencao;//PNG
    private String replacementAdditionalText;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate validity;
}
