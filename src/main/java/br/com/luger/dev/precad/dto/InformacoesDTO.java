package br.com.luger.dev.precad.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class InformacoesDTO {
    private String nome;
    private String cpf;
    private String email;
    private String limite;
    private String inicio;
    private String status;
    private List<String> pendencias;

    public InformacoesDTO(String cpf, String nome, String email, List<String> pendencias) {
    }

    // Getters and Setters

    // Método para definir pendencias baseado na lista
    public void setPendencias(List<String> list) {
        if (list == null || list.isEmpty()) {
            List<String> lista = new ArrayList<>();
            lista.add("Sem pendências");
            this.pendencias = lista;
        } else {
            this.pendencias = list;
        }
    }

}

