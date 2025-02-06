package br.com.luger.dev.precad.documentluger.service.interfaces;

import br.com.luger.dev.precad.documentluger.model.Documents;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CRUDservice<T> {

    List<T> findAll();

    T save(T entity);

    void delete(UUID id);

    Optional<T> findById(UUID id);

    List<T> findByIdCadastroCurso(UUID id);


    void deleteByIdCadastroCurso(UUID idCadastroCurso);

    Optional<Documents> findByIdCadastroCursoAndDocumentType(UUID id, String type);
}