package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.dto.EndContatDTO;
import br.com.luger.dev.precad.model.CadastroCurso;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/addcontacts")
public class AddressAndContact {
    private final CadastroCursoController cadastroCursoController;

    @Autowired
    public AddressAndContact(CadastroCursoController cadastroCursoController) {
        this.cadastroCursoController = cadastroCursoController;
    }

    @GetMapping("/infos/{cpf}/{token}")
    public ResponseEntity<EndContatDTO> getRegistration(@PathVariable String cpf, @PathVariable String token) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        EndContatDTO endContatDto = new EndContatDTO();
        if (aluno.isEmpty()) {
            System.out.println(" infos 404");
            return ResponseEntity.status(404).body(null);
        }
        if (!aluno.get().getToken().equals(token.toUpperCase())) {
            System.out.println(" infos 403");
            return ResponseEntity.status(403).body(null);
        }

        BeanUtils.copyProperties(aluno.get(), endContatDto);
        System.out.println(" infos 200: {" + endContatDto + "}");
        return ResponseEntity.status(200).body(endContatDto);
    }

    @PostMapping("/cad")
    public ResponseEntity<String> cadEndCont(@RequestBody EndContatDTO endContatDTO) {
        System.out.println(endContatDTO.getCpf());
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(endContatDTO.getCpf());
        if (aluno.isEmpty()) {
            System.out.println("cad 404: {" + endContatDTO + "}");
            return ResponseEntity.status(404).body(null);
        }
        if (!aluno.get().getToken().equals(endContatDTO.getToken().toUpperCase())) {
            System.out.println("cad 403: {" + endContatDTO + "}");
            return ResponseEntity.status(403).body(null);
        }

        CadastroCurso cadastroCurso = aluno.get();
        BeanUtils.copyProperties(endContatDTO, cadastroCurso);


        String cadastroCursoString = cadastroCursoController.save(cadastroCurso).toString();
        System.out.println("cad 200: {" + endContatDTO + "}");
        return ResponseEntity.status(200).body(cadastroCursoString);
    }
}