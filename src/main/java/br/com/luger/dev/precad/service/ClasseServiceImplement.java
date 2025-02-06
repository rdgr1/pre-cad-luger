package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.model.Classe;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.ClasseRepository;
import br.com.luger.dev.precad.service.interfaceService.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClasseServiceImplement extends CRUDserviceImplement<Classe> implements ClasseService {


    private final ClasseRepository entityRepository;

    @Autowired
    public ClasseServiceImplement(ClasseRepository entityRepository) {

        this.entityRepository = entityRepository;


    }


    @Override
    protected AbstractRepository<Classe, UUID> getRepository() {

        return entityRepository;

    }


    public List<Classe> findAll() {
        return entityRepository.findAll();
    }

    public int contarCadastrosPorClasse(UUID classeId) {
        return entityRepository.countById(classeId);
    }
}


