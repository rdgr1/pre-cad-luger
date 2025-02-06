package br.com.luger.dev.precad.documentluger.enums;

public enum StatusCadastroCursoDocumento {
    A("ACEITO"),
    AS("SUBSTITUIR"),
    SD("SUBSTITUIDO");

    private final String label;

    StatusCadastroCursoDocumento(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}
