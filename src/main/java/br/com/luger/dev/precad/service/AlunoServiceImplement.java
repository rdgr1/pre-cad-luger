package br.com.luger.dev.precad.service;


import br.com.luger.dev.precad.model.Aluno;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.AlunoRepository;
import br.com.luger.dev.precad.service.interfaceService.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoServiceImplement extends CRUDserviceImplement<Aluno> implements AlunoService {


    private final AlunoRepository entityRepository;


    @Autowired
    public AlunoServiceImplement(AlunoRepository entityRepository) {
        this.entityRepository = entityRepository;
    }


    @Override
    protected AbstractRepository<Aluno, UUID> getRepository() {

        return entityRepository;

    }

    @Override
    public Optional<Aluno> findByCpf(String cpf) {
        return entityRepository.findByCpf(cpf);
    }
}


