package br.com.luger.dev.precad.enums;

public enum StatusCurso {
    A("ATIVO"),
    INA("INATIVO");

    private final String label;

    StatusCurso(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
