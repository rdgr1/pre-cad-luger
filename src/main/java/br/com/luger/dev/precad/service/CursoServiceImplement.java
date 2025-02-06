package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.model.Curso;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.CursoRepository;
import br.com.luger.dev.precad.service.interfaceService.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CursoServiceImplement extends CRUDserviceImplement<Curso> implements CursoService {


    private final CursoRepository entityRepository;

    @Autowired
    public CursoServiceImplement(CursoRepository entityRepository) {

        this.entityRepository = entityRepository;


    }


    @Override
    protected AbstractRepository<Curso, UUID> getRepository() {

        return entityRepository;

    }


    @Override
    public Optional<Curso> findByTitulo(String titulo) {
        return entityRepository.findByTitulo(titulo);
    }


    public List<Curso> findAll() {
        return entityRepository.findAll();
    }
}


