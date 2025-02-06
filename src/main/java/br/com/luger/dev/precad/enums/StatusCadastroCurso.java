package br.com.luger.dev.precad.enums;

public enum StatusCadastroCurso {

    PRE("PRÉ-INSERIDO"),
    INI("INICIADO"),
    SOL("SOLICITADO"),
    EP("EM PREENCHIMENTO"),
    CAN("CANCELADO"),
    ENV("ENVIADO"),
    PEN("COM PENDÊNCIA(S)"),
    FIN("FINALIZADO"),
    A("ACEITO");

    private final String label;

    StatusCadastroCurso(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
