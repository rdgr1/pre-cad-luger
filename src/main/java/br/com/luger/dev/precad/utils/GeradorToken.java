package br.com.luger.dev.precad.utils;

import java.util.Random;

public class GeradorToken {
    public static void main(String[] args) {
        String senha = gerarToken();
        System.out.println("Senha gerada: " + senha);
    }

    public static String gerarToken() {
        StringBuilder senha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            boolean isLetter = random.nextBoolean();
            if (isLetter) {
                char letraAleatoria = (char) (random.nextInt(26) + 'a');
                senha.append(letraAleatoria);
            } else {
                int numeroAleatorio = random.nextInt(10);
                senha.append(numeroAleatorio);
            }
        }

        return senha.toString();
    }

}
