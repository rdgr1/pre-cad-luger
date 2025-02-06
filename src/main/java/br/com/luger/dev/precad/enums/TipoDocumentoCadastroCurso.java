package br.com.luger.dev.precad.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoDocumentoCadastroCurso {

    F("FOTO"),
    DI("DOCUMENTO DE IDENTIDADE (FRENTE)"),
    DIV("DOCUMENTO DE IDENTIDADE (VERSO)"),
    CNH("CNH"),
    CPF("CPF"),
    SCC("SITUAÇÃO CADASTRAL DO CPF"),
    TE("TÍTULO DE ELEITOR (FRENTE)"),
    TEV("TÍTULO DE ELEITOR (VERSO)"),
    CR("CERTIFICADO DE RESERVISTA/CERTIFICADO DE DISPENSA DA INCORPORAÇÃO (FRENTE)"),
    CRV("CERTIFICADO DE RESERVISTA/CERTIFICADO DE DISPENSA DA INCORPORAÇÃO (VERSO)"),
    CE("CERTIFICADO DE ESCOLARIDADE (FRENTE)"),
    CEV("CERTIFICADO DE ESCOLARIDADE (VERSO)"),
    ES("EXAME DE SAÚDE"),
    EAP("EXAME DE APTIDÃO PSICOLÓGICA"),
    CEND("COMPROVANTE DE ENDEREÇO"),
    CQE("CERTIDÃO DE QUITAÇÃO ELEITORAL"),
    CNJF("CERTIDÃO NEGATIVA DA JUSTIÇA FEDERAL"),
    CNJFR("CERTIDÃO NEGATIVA DA JUSTIÇA FEDERAL - ESTADO ONDE RESIDE"),
    CNJE("CERTIDÃO NEGATIVA DA JUSTIÇA ESTADUAL"),
    CNJER("CERTIDÃO NEGATIVA DA JUSTIÇA ESTADUAL - ESTADO ONDE RESIDE"),
    CNJMU("CERTIDÃO NEGATIVA DA JUSTIÇA MILITAR DA UNIÃO"),
    CNJME("CERTIDÃO NEGATIVA DA JUSTIÇA MILITAR ESTADUAL"),
    CNJMER("CERTIDÃO NEGATIVA DA JUSTIÇA MILITAR ESTADUAL - ESTADO ONDE RESIDE"),
    CNJEL("CERTIDÃO NEGATIVA DA JUSTIÇA ELEITORAL");

    private static final Map<String, TipoDocumentoCadastroCurso> funcaoPorLabel = new HashMap<>();

    static {
        for (TipoDocumentoCadastroCurso tipoDocumentoCadastroCurso : TipoDocumentoCadastroCurso.values()) {
            funcaoPorLabel.put(tipoDocumentoCadastroCurso.getLabel(), tipoDocumentoCadastroCurso);
        }
    }

    private final String label;

    TipoDocumentoCadastroCurso(String label) {
        this.label = label;
    }

    public static TipoDocumentoCadastroCurso getInstance(String label) {
        return funcaoPorLabel.get(label);
    }

    public String getLabel() {
        return this.label;
    }
}
