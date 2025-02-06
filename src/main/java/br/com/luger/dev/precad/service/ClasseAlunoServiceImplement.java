package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.model.ClasseAluno;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.ClasseAlunoRepository;
import br.com.luger.dev.precad.service.interfaceService.ClasseAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClasseAlunoServiceImplement extends CRUDserviceImplement<ClasseAluno> implements ClasseAlunoService {


    private final ClasseAlunoRepository entityRepository;

    @Autowired
    public ClasseAlunoServiceImplement(ClasseAlunoRepository entityRepository) {

        this.entityRepository = entityRepository;

    }


    @Override
    protected AbstractRepository<ClasseAluno, UUID> getRepository() {

        return entityRepository;

    }

}


