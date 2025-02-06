package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import br.com.luger.dev.precad.service.interfaceService.CadastroCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/cadastrocurso")
public class CadastroCursoController extends CRUDcontroller<CadastroCurso> {

    private final CadastroCursoService cadastroCursoService;


    @Autowired
    public CadastroCursoController(CadastroCursoService cadastroCursoService) {
        this.cadastroCursoService = cadastroCursoService;

    }


    @Override
    public CRUDservice<CadastroCurso> getService() {

        return cadastroCursoService;

    }

    public void updateStatusClasse(UUID classeId) {
        cadastroCursoService.salvarCadastroCurso(classeId);
        cadastroCursoService.removerCadastroCurso(classeId);
    }


    public Optional<CadastroCurso> findByCpf(String cpf) {
        return cadastroCursoService.findByCpf(cpf);
    }

    public Page<CadastroCurso> findByStatus(String status, Pageable pageable) {
        return cadastroCursoService.findByStatus(status, pageable);
    }

    public void deleteByCpf(String cpf) {
        cadastroCursoService.deleteByCpf(cpf);
    }
}


