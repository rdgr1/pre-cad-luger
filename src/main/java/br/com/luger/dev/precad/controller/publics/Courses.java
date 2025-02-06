package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.dto.CursoDTO;
import br.com.luger.dev.precad.model.CadastroCurso;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/courses")
public class Courses {

    private final CadastroCursoController cadastroCursoController;

    @Autowired
    public Courses(CadastroCursoController cadastroCursoController) {

        this.cadastroCursoController = cadastroCursoController;
    }

    @GetMapping("/infos/{cpf}/{token}")
    public ResponseEntity<CursoDTO> getRegistration(@PathVariable String cpf, @PathVariable String token) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        CursoDTO cursoDTO = new CursoDTO();
        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        if (!aluno.get().getToken().equals(token.toUpperCase())) {
            return ResponseEntity.status(403).body(null);
        }
        BeanUtils.copyProperties(aluno.get(), cursoDTO);
        return ResponseEntity.status(200).body(cursoDTO);
    }

    @PostMapping("/cad")
    public ResponseEntity<String> cadCurso(@RequestBody CursoDTO cursoDTO) {
        System.out.println(cursoDTO.toString());
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cursoDTO.getCpf());
        if (aluno.isEmpty()) {
            System.out.println("aluno is empty");
            return ResponseEntity.status(404).body(null);
        }
        if (!aluno.get().getToken().equals(cursoDTO.getToken().toUpperCase())) {
            System.out.println("token not found: " + cursoDTO.getToken());
            return ResponseEntity.status(403).body(null);
        }

        CadastroCurso cadastroCurso = aluno.get();
        BeanUtils.copyProperties(cursoDTO, cadastroCurso);


        cadastroCursoController.save(cadastroCurso);

        String cadastroCursoString = cadastroCursoController.save(cadastroCurso).toString();
        System.out.println(cursoDTO + " sucess: 200");
        return ResponseEntity.status(200).body(cadastroCursoString);
    }


}

