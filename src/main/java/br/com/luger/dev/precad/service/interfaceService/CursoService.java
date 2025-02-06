package br.com.luger.dev.precad.service.interfaceService;

import br.com.luger.dev.precad.model.Curso;

import java.util.Optional;

public interface CursoService extends CRUDservice<Curso> {
    Optional<Curso> findByTitulo(String titulo);
}
