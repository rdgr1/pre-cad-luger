package br.com.luger.dev.precad.repository;

import br.com.luger.dev.precad.model.Classe;

import java.util.UUID;

public interface ClasseRepository extends AbstractRepository<Classe, UUID> {
    int countById(UUID classeId);
}