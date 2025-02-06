package br.com.luger.dev.precad.controller.management;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.dto.DocumentsDto;
import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.service.DocumentsServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/mgmt/documents")
public class DocMgmt {
    private final DocumentsServiceAPI service;
    private final CadastroCursoController cadastroCursoController;

    @Autowired
    public DocMgmt(DocumentsServiceAPI service, CadastroCursoController cadastroCursoController) {
        this.service = service;
        this.cadastroCursoController = cadastroCursoController;
    }

    @PutMapping("/update/status")
    public ResponseEntity<String> updateStatusDocuments(@RequestParam(name = "type") String type, @RequestParam(name = "cpf") String cpf, @RequestParam(name = "status") String status) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return service.updateStatusDocument(aluno.get().getId(), type, status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDocuments(@RequestParam(name = "type") String type, @RequestParam(name = "cpf") String cpf) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return service.deleteDocument(aluno.get().getId(), type);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDocuments(@RequestParam(name = "id") UUID id, @RequestBody @Validated DocumentsDto dto) {
        return service.updateDocument(id, dto);
    }

    @DeleteMapping("/deleteByCadastroCursoId/{idCadastroCurso}")
    public ResponseEntity<String> deleteByCadastroCursoId(@PathVariable UUID idCadastroCurso) {
        return service.deleteByCadastroCursoIdDocument(idCadastroCurso);
    }


}
