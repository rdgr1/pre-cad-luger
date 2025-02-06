package br.com.luger.dev.precad.model;

import br.com.luger.dev.precad.dto.InformacoesDTO;
import lombok.Data;

import java.util.List;

/**
 * Classe que representa o conteúdo de uma página de relatório.
 */
@Data
public class Conteudo {
    /**
     * Número da página atual.
     */
    private int currentPage;

    /**
     * Lista de itens de conteúdo para a página.
     */
    private List<InformacoesDTO> content;
}
