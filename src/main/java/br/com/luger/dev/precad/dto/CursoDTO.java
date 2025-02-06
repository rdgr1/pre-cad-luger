package br.com.luger.dev.precad.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CursoDTO {


    public String cpf;
    private String empresaAtual;//-
    private String cnv;//-
    private String registro;//-
    private LocalDate dataExpedicaoCnv;//-
    private LocalDate dataUltimoCurso;
    private String tipoProcesso;
    private String token;
}
