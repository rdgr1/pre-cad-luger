package br.com.luger.dev.precad.controller.management;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.controller.publics.Documents;
import br.com.luger.dev.precad.dto.InformacoesDTO;
import br.com.luger.dev.precad.enums.TipoDocumentoCadastroCurso;
import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Conteudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/altomation")
public class AltomationController {

    private final Documents docs;
    private final CadastroCursoController cadastroCursoController;

    @Autowired
    public AltomationController(Documents docs, CadastroCursoController cadastroCursoController) {
        this.docs = docs;
        this.cadastroCursoController = cadastroCursoController;
    }

    public static List<String> verificarCamposNulos(CadastroCurso cadastroCurso) {
        List<String> camposNulos = new ArrayList<>();
        if (cadastroCurso.getId() == null) camposNulos.add("id -> nulo");
        if (cadastroCurso.getCurso() == null) camposNulos.add("curso -> nulo");
        if (cadastroCurso.getClasse() == null) camposNulos.add("classe -> nulo");
        if (cadastroCurso.getNome() == null) camposNulos.add("nome -> nulo");
        if (cadastroCurso.getSexo() == null) camposNulos.add("sexo -> nulo");
        if (cadastroCurso.getDataNascimento() == null) camposNulos.add("dataNascimento -> nulo");
        if (cadastroCurso.getNomeSocial() == null) camposNulos.add("nomeSocial -> nulo");
        if (cadastroCurso.getNomeMae() == null) camposNulos.add("nomeMae -> nulo");
        if (cadastroCurso.getNomePai() == null) camposNulos.add("nomePai -> nulo");
        if (cadastroCurso.getDocumentoIdentidade() == null) camposNulos.add("documentoIdentidade -> nulo");
        if (cadastroCurso.getUfDocumentoIdentidade() == null) camposNulos.add("ufDocumentoIdentidade -> nulo");
        if (cadastroCurso.getOrgaoExpedidorDocumentoIdentidade() == null)
            camposNulos.add("orgaoExpedidorDocumentoIdentidade -> nulo");
        if (cadastroCurso.getNaturalidadePais() == null) camposNulos.add("naturalidadePais -> nulo");
        if (cadastroCurso.getNaturalidadeEstado() == null) camposNulos.add("naturalidadeEstado -> nulo");
        if (cadastroCurso.getNaturalidadeMunicipio() == null) camposNulos.add("naturalidadeMunicipio -> nulo");
        if (cadastroCurso.getEndereco() == null) camposNulos.add("endereco -> nulo");
        if (cadastroCurso.getEnderecoBairro() == null) camposNulos.add("enderecoBairro -> nulo");
        if (cadastroCurso.getEnderecoMunicipio() == null) camposNulos.add("enderecoMunicipio -> nulo");
        if (cadastroCurso.getEnderecoCep() == null) camposNulos.add("enderecoCep -> nulo");
        if (cadastroCurso.getTelefone1() == null) camposNulos.add("telefone1 -> nulo");
        if (cadastroCurso.getTelefone2() == null) camposNulos.add("telefone2 -> nulo");
        if (cadastroCurso.getCpf() == null) camposNulos.add("cpf -> nulo");
        if (cadastroCurso.getRegistro() == null) camposNulos.add("registro -> nulo");
        if (cadastroCurso.getDataUltimoCurso() == null) camposNulos.add("dataUltimoCurso -> nulo");
        if (cadastroCurso.getEmail() == null) camposNulos.add("email -> nulo");
        if (cadastroCurso.getTipoProcesso() == null) camposNulos.add("tipoProcesso -> nulo");
        if (cadastroCurso.getEnderecoUf() == null) camposNulos.add("enderecoUf -> nulo");
        if (cadastroCurso.getToken() == null) camposNulos.add("token -> nulo");
        if (cadastroCurso.getStatus() == null) camposNulos.add("status -> nulo");
        if (cadastroCurso.getTsAceiteTermo() == null) camposNulos.add("tsAceiteTermo -> nulo");
        if (cadastroCurso.getTipoIdentidade() == null) camposNulos.add("tipoIdentidade -> nulo");
        if (cadastroCurso.getIdentidadePossuiCpf() == null) camposNulos.add("identidadePossuiCpf -> nulo");
        if (cadastroCurso.getEmpresaAtual() == null) camposNulos.add("empresaAtual -> nulo");
        if (cadastroCurso.getCnv() == null) camposNulos.add("cnv -> nulo");
        if (cadastroCurso.getDataExpedicaoCnv() == null) camposNulos.add("dataExpedicaoCnv -> nulo");
        if (cadastroCurso.getEnderecoComplemento() == null) camposNulos.add("enderecoComplemento -> nulo");
        if (cadastroCurso.getTituloEleitor() == null) camposNulos.add("tituloEleitor -> nulo");
        if (cadastroCurso.getEnderecoNumero() == null) camposNulos.add("enderecoNumero -> nulo");
        if (cadastroCurso.getTeId() == null) camposNulos.add("teId -> nulo");

        return camposNulos;
    }

    @GetMapping("/report/pending")
    public String getPendingReport(Model model) {
        List<Conteudo> conteudoList = generateContentList();
        model.addAttribute("conteudos", conteudoList);
        return "index";
    }

    private List<Conteudo> generateContentList() {
        List<Conteudo> conteudoList = new ArrayList<>();
        Page<CadastroCurso> cadastroCursoPage = cadastroCursoController.findAll(0, 10);

        for (int i = 0; i < cadastroCursoPage.getTotalPages(); i++) {
            cadastroCursoPage = cadastroCursoController.findAll(i, 10);
            Conteudo currentConteudo = createConteudo(cadastroCursoPage, i);
            conteudoList.add(currentConteudo);
        }
        return conteudoList;
    }

    private Conteudo createConteudo(Page<CadastroCurso> cadastroCursoPage, int currentPage) {
        Conteudo conteudo = new Conteudo();
        conteudo.setCurrentPage(currentPage);
        List<InformacoesDTO> informacoesDTOList = cadastroCursoPage.getContent().stream()
                .map(this::createInformacoesDTO)
                .collect(Collectors.toList());
        conteudo.setContent(informacoesDTOList);
        return conteudo;
    }

    private InformacoesDTO createInformacoesDTO(CadastroCurso cadastroCurso) {
        String cpf = cadastroCurso.getCpf();
        String nome = cadastroCurso.getNome();
        String email = cadastroCurso.getEmail();
        List<String> pendencias;

        try {
            ResponseEntity<List<String>> responseEntity = docs.isntFilleds(cpf);
            pendencias = Objects.requireNonNull(responseEntity.getBody()).stream()
                    .map(TipoDocumentoCadastroCurso::valueOf)
                    .map(TipoDocumentoCadastroCurso::getLabel)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            pendencias = verificarCamposNulos(cadastroCurso);
        }

        return new InformacoesDTO(cpf, nome, email, pendencias);
    }


}
