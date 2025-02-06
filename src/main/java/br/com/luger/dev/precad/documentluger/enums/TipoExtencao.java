package br.com.luger.dev.precad.documentluger.enums;

public enum TipoExtencao {
    PDF(".pdf"),
    JPG(".jpeg"),
    PNG(".png");
    private final String label;

    TipoExtencao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
