package br.com.luger.dev.precad.enums;

public enum StatusCadastroCursoDocumento {
    ACEITO("ACEITO"),
    SUBSTITUIR("SUBSTITUIR"),
    CONFERIR("CONFERIR"),
    PENDENTE("PENDENTE");

    private final String label;

    StatusCadastroCursoDocumento(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}
