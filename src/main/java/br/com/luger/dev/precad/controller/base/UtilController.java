package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.controller.management.RegistrationController;
import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Users;
import br.com.luger.dev.precad.utils.TokenUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping()
public class UtilController {
    private final RegistrationController registrationController;
    private final TokenUtil tokenUtil;
    private final JavaMailSender javaMailSender;
    private final CadastroCursoController cadastroCursoController;
    private final UserModelController userModelController;
    @Value("${local.server.address}")
    private String serverAddress;

    @Autowired
    public UtilController(RegistrationController registrationController, TokenUtil tokenUtil, JavaMailSender javaMailSender, CadastroCursoController cadastroCursoController, UserModelController userModelController) {
        this.registrationController = registrationController;
        this.tokenUtil = tokenUtil;
        this.javaMailSender = javaMailSender;
        this.cadastroCursoController = cadastroCursoController;
        this.userModelController = userModelController;
    }

    @GetMapping("/mgmt/isfacc")
    public ResponseEntity<String> isFAcc(@RequestHeader HttpHeaders headers) {
        if (tokenUtil.isFirstAccess(headers)) {
            UUID id = tokenUtil.getUserIdByToken(headers);
            return ResponseEntity.status(200).body(id.toString());
        }
        return ResponseEntity.status(406).body("");
    }

    @GetMapping("/facc/{id}")
    public ResponseEntity<String> fAcc(HttpServletResponse response, @PathVariable UUID id) throws IOException {

        System.out.println("id: " + id);
        Optional<Users> optionalCadastroCurso = userModelController.findById(id);
        if (optionalCadastroCurso.isEmpty()) {
            return ResponseEntity.status(404).body("not found");
        }
        response.sendRedirect(serverAddress + "/alterarsenha/" + optionalCadastroCurso.get().getCpf());

        return ResponseEntity.status(200).body("");

    }


    @GetMapping("/admin/isadmin")
    public ResponseEntity<String> isAdmin(@RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }
        return ResponseEntity.status(200).body("access granted");
    }

    public ResponseEntity<String> sendEmail(String mail, String code) {
        try {
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
            helper.setTo(mail);
            helper.setFrom(mail);
            helper.setSubject("check mail");

            // Define o conteúdo como HTML
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"pt-br\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Código de Ativação</title>\n" +
                    "    \n" +
                    "</head>\n" +
                    "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; height: 100vh; display: flex; justify-content: center; align-items: center;\">\n" +
                    "\n" +
                    "<div class=\"container\" style=\"margin: auto; max-width: 360px; padding: 20px; text-align: center; background-color: #FF9800; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.2);\">\n" +
                    "  <h1 style=\"color: white; font-size: 28px; font-weight: bold; margin-bottom: 20px;\">Código de Ativação</h1>\n" +
                    "  <h2 style=\"color: white; font-size: 20px; margin-bottom: 20px;\">Seu código de verificação é:</h2>\n" +
                    "  <div class=\"code-container\" style=\"display: flex; justify-content: space-between; margin-bottom: 30px;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(0) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase; height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; margin-right: 10px; padding: 0;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(1) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase; height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; margin-right: 10px; padding: 0;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(2) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase; height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; margin-right: 10px; padding: 0;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(3) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase; height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; margin-right: 10px; padding: 0;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(4) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase;  height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; margin-right: 10px; padding: 0;\">\n" +
                    "    <input class=\"code-box\" type=\"text\" maxlength=\"1\" value=\"" + code.charAt(5) +
                    "\" readonly style=\"width: calc(100% / 6); text-transform: uppercase;  height: 50px; line-height: 50px; background-color: rgba(255,255,255,0.2); border: none; border-radius: 4px; font-size: 24px; color: white; text-align: center; padding: 0; margin-right: 0;\">\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";


            helper.setText(htmlContent, true);


            javaMailSender.send(mensagem);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Erro ao enviar email: " + e.getMessage());
        }

        return ResponseEntity.status(200).body("Email enviado com sucesso");
    }


    @PostMapping("/public/istokenvalid")
    public ResponseEntity<String> verifyToken(@RequestBody Map<String, String> map) {
        String token = map.get("token");
        String cpf = map.get("cpf");
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);

        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        if (!aluno.get().getToken().equals(token.toUpperCase())) {
            return ResponseEntity.status(403).body(null);
        }
        System.out.println("cpf" + aluno.get().getCpf());
        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/public/email/forwarding")
    private ResponseEntity<String> forward(@RequestBody Map<String, String> map) {
        String cpf = map.get("cpf");

        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        String token = aluno.get().getToken();
        String email = aluno.get().getEmail();
        if (!sendEmail(email, token).getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(null);

    }


    @PostMapping("/pdf/concatenate")
    public String concatenatePdfs(@RequestBody Map<String, String> mapa) throws IOException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        byte[] pdfBytes = Base64.getDecoder().decode(mapa.get("first"));
        pdfMerger.addSource(new ByteArrayInputStream(pdfBytes));

        byte[] pdfBytes2 = Base64.getDecoder().decode(mapa.get("second"));
        pdfMerger.addSource(new ByteArrayInputStream(pdfBytes2));


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfMerger.setDestinationStream(outputStream);
        pdfMerger.mergeDocuments(null);

        byte[] mergedPdfBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(mergedPdfBytes);
    }


}
