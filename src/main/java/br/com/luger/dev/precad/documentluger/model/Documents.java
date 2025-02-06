package br.com.luger.dev.precad.documentluger.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
@Table(name = "cadastro_curso_documento")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;//

    @JoinColumn(name = "id_cadastro_curso")
    private UUID idCadastroCurso;

    @Column(name = "tipo_documento", length = Integer.MAX_VALUE)
    private String documentType;

    @Column(name = "caminho", length = Integer.MAX_VALUE)
    private String path;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "substituicao_texto_adicional", length = Integer.MAX_VALUE)
    private String replacementAdditionalText;//
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "validade")
    private LocalDate validity;//

    @Column(name = "te_id")
    private UUID teId;//


}
