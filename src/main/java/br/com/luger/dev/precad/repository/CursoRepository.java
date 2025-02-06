package br.com.luger.dev.precad.repository;

import br.com.luger.dev.precad.model.Curso;

import java.util.Optional;
import java.util.UUID;

public interface CursoRepository extends AbstractRepository<Curso, UUID> {
    Optional<Curso> findByTitulo(String titulo);
}