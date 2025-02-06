package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.model.Aluno;
import br.com.luger.dev.precad.service.interfaceService.AlunoService;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/aluno")
public class AlunoController extends CRUDcontroller<Aluno> {

    private final AlunoService alunoService;


    @Autowired

    public AlunoController(AlunoService alunoService) {

        this.alunoService = alunoService;
    }

    @Override
    public CRUDservice<Aluno> getService() {
        return alunoService;
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return alunoService.findByCpf(cpf);
    }
}


