package br.com.luger.dev.precad.documentluger.service;


import br.com.luger.dev.precad.documentluger.model.Documents;
import br.com.luger.dev.precad.documentluger.repository.AbstractRepository;
import br.com.luger.dev.precad.documentluger.repository.DocumentsRepository;
import br.com.luger.dev.precad.documentluger.service.interfaces.DocumentsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentsServiceImplement extends CRUDserviceImplement<Documents> implements DocumentsService {


    private final DocumentsRepository entityRepository;

    @Autowired
    public DocumentsServiceImplement(DocumentsRepository entityRepository) {

        this.entityRepository = entityRepository;


    }


    @Override
    protected AbstractRepository<Documents, UUID> getRepository() {

        return entityRepository;

    }

    @Override
    public List<Documents> findByIdCadastroCurso(UUID id) {
        return entityRepository.findByIdCadastroCurso(id);
    }

    @Transactional
    @Override
    public void deleteByIdCadastroCurso(UUID idCadastroCurso) {
        entityRepository.deleteByIdCadastroCurso(idCadastroCurso);
    }

    @Override
    public Optional<Documents> findByIdCadastroCursoAndDocumentType(UUID id, String type) {
        return entityRepository.findByIdCadastroCursoAndDocumentType(id, type);
    }
}


