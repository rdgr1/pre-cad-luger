package br.com.luger.dev.precad.controller.management;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.model.CadastroCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mgmt/studentinfo")
public class StudentInfo {

    private final CadastroCursoController cadastroCurso;

    @Autowired
    public StudentInfo(CadastroCursoController cadastroCurso) {
        this.cadastroCurso = cadastroCurso;
    }


    public Optional<CadastroCurso> findByCpf(String cpf) {
        return cadastroCurso.findByCpf(cpf);
    }

    @GetMapping("/cursosbystatus")
    public Page<CadastroCurso> getCursosByStatus(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return cadastroCurso.findByStatus(status, pageable);
    }

    @GetMapping
    public Page<CadastroCurso> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size) {


        return cadastroCurso.findAll(page, size);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<CadastroCurso> delete(@PathVariable String cpf) {
        Optional<CadastroCurso> optionalCadastroCurso = cadastroCurso.findByCpf(cpf);
        if (optionalCadastroCurso.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        cadastroCurso.deleteByCpf(cpf);
        cadastroCurso.updateStatusClasse(optionalCadastroCurso.get().getClasse().getId());
        return ResponseEntity.ok().body(null);
    }


}


