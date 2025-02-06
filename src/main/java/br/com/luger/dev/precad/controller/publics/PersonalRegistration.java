package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.controller.base.ClasseController;
import br.com.luger.dev.precad.controller.base.CursoController;
import br.com.luger.dev.precad.controller.base.UtilController;
import br.com.luger.dev.precad.dto.CadPessoalDTO;
import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Classe;
import br.com.luger.dev.precad.model.Curso;
import br.com.luger.dev.precad.utils.GeradorToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/person")
public class PersonalRegistration {

    private final CadastroCursoController cadastroCursoController;
    private final ClasseController classeController;
    private final UtilController utilController;
    private final CursoController cursoController;


    @Autowired
    public PersonalRegistration(CadastroCursoController cadastroCursoController, ClasseController classeController, UtilController utilController, CursoController cursoController) {

        this.cadastroCursoController = cadastroCursoController;
        this.classeController = classeController;

        this.utilController = utilController;
        this.cursoController = cursoController;
    }

    public ResponseEntity<CadastroCurso> getRegistration(@PathVariable String cpf) {


        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);


        CadPessoalDTO cadPessoalDTO = new CadPessoalDTO();
        return aluno.map(cadastroCurso -> ResponseEntity.status(200).body(cadastroCurso)).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @PostMapping("/cad")
    public ResponseEntity<String> CadPessoalDTO(@RequestBody @Validated CadPessoalDTO cadpessoalDTO) {


        Optional<Classe> optionalClasse = classeController.findById(cadpessoalDTO.getClasse());
        Optional<Curso> optionalCurso = cursoController.findById(cadpessoalDTO.getCurso());
        ResponseEntity<CadastroCurso> response = this.getRegistration(cadpessoalDTO.getCpf());
        if (response.getStatusCode().is2xxSuccessful()) {
            if (!utilController.sendEmail(cadpessoalDTO.getEmail(), Objects.requireNonNull(response.getBody()).getToken()).getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.badRequest().body("error sending email");
            }
            return ResponseEntity.status(409).body("email send success");
        }
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.badRequest().body("curse not exists");
        }
        if (optionalClasse.isEmpty()) {
            return ResponseEntity.badRequest().body("curse not exists");
        }
        CadastroCurso alunoCurso = new CadastroCurso();
        BeanUtils.copyProperties(cadpessoalDTO, alunoCurso);
        alunoCurso.setCurso(optionalCurso.get());
        alunoCurso.setClasse(optionalClasse.get());
        alunoCurso.setToken(GeradorToken.gerarToken().toUpperCase());

        if (!utilController.sendEmail(cadpessoalDTO.getEmail(), alunoCurso.getToken()).getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.badRequest().body("error sending email");
        }

        String alunoCursoString = cadastroCursoController.save(alunoCurso).toString();

        cadastroCursoController.updateStatusClasse(alunoCurso.getClasse().getId());

        return ResponseEntity.ok().body(alunoCursoString);
    }


    @GetMapping("/iscad/{cpf}")
    public ResponseEntity<String> isCadPessoalDTO(@PathVariable String cpf) {
        System.out.println(cpf);
        ResponseEntity<CadastroCurso> response = this.getRegistration(cpf);
        if (response.getStatusCode().is2xxSuccessful()) {
            if (!utilController.sendEmail(Objects.requireNonNull(response.getBody()).getEmail(), response.getBody().getToken()).getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.internalServerError().body("error sending email");
            }
            return ResponseEntity.status(200).body("email send success");
        }
        return ResponseEntity.status(400).body("user not exists");
    }


}
