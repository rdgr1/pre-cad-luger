package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.model.CadastroCurso;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/public/termo")
public class Termo {
    private final CadastroCursoController cadastroCurso;
    private final SecretKey chaveAES;
    private final Cipher cifraAES;
    @Value("${front.return.url}")
    private String frontUrl;
    @Value("${front.return.registration}")
    private String frontRegistration;
    @Value("${termo.urlDoc}")
    private String urlDoc;

    public Termo(@Value("${chaveAES}") String chave, CadastroCursoController cadastroCurso) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        this.cadastroCurso = cadastroCurso;
        // Converte a string hexa para um array de bytes
        byte[] chaveBytes = DatatypeConverter.parseHexBinary(chave);
        this.chaveAES = new SecretKeySpec(chaveBytes, "AES");
        this.cifraAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] fromHexString(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    public byte[] encript(String texto) {
        try {
            this.cifraAES.init(Cipher.ENCRYPT_MODE, this.chaveAES);
            byte[] textoPuro = texto.getBytes(StandardCharsets.UTF_8);
            int paddingLength = 16 - textoPuro.length % 16;
            byte[] paddedTexto = new byte[textoPuro.length + paddingLength];
            System.arraycopy(textoPuro, 0, paddedTexto, 0, textoPuro.length);
            return this.cifraAES.doFinal(paddedTexto);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String desencript(byte[] texto) {
        try {
            this.cifraAES.init(Cipher.DECRYPT_MODE, this.chaveAES);
            byte[] textoDecriptografado = this.cifraAES.doFinal(texto);
            return new String(textoDecriptografado, StandardCharsets.UTF_8).trim();
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{cpf}")
    public String termo(HttpServletResponse response, @PathVariable String cpf, Model model) throws IOException {


        Optional<CadastroCurso> aluno = cadastroCurso.findByCpf(cpf);
        if (aluno.isEmpty()) {
            response.sendRedirect("/error");
            return null;
        }
        if (aluno.get().getTsAceiteTermo() != null) {
            System.out.println(aluno.get().getToken());
            response.sendRedirect(frontUrl + frontRegistration);
            return null;
        }
        System.out.println("cpf" + aluno.get().getCpf());
        model.addAttribute("urlDoc", urlDoc);
        model.addAttribute("toHexString", toHexString(encript(aluno.get().getToken() + "}" + cpf)));
        return "termo";


    }

    @GetMapping(path = "/accept")
    public ResponseEntity<String> accept(HttpServletResponse response, @RequestParam(name = "request") String secret) throws IOException {
        String desencript = desencript(fromHexString(secret));
        String[] termos = desencript.split("}");
        Optional<CadastroCurso> aluno = cadastroCurso.findByCpf(termos[1]);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body("Error cpf not found");
        }
        aluno.get().setTsAceiteTermo(OffsetDateTime.now());
        cadastroCurso.save(aluno.get());
        response.sendRedirect(frontUrl + frontRegistration);
        return ResponseEntity.ok("succes");
    }
}
