package br.com.luger.dev.precad.controller.base;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/static-pdf")
public class PdfController {

    @GetMapping()
    public ResponseEntity<Resource> getStaticPdf() {
        // Carregar o PDF do diretório static
        Resource pdfFile = new ClassPathResource("/static/imagens/LugerTemodeConsentimentoLGPD.pdf");

        if (pdfFile.exists()) {
            // Configurar cabeçalhos de resposta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=LugerTemodeConsentimentoLGPD.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            // Retornar o arquivo como resposta
            return new ResponseEntity<>(pdfFile, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
