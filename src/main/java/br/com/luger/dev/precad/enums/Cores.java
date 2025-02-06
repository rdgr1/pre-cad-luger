package br.com.luger.dev.precad.enums;

import lombok.Getter;

@Getter
public enum Cores {

    AMARELO("#FFFF00"),
    AZUL("#0000FF"),
    BRANCO("#FFFFFF"),
    CINZA("#808080"),
    LARANJA("#FFA500"),
    PRETO("#000000"),
    ROSA("#FFC0CB"),
    ROXO("#800080"),
    VERDE("#00FF00"),
    VERMELHO("#FF0000");

    private final String hexValue;

    Cores(String hexValue) {
        this.hexValue = hexValue;
    }
}
