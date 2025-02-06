package br.com.luger.dev.precad.repository;

import br.com.luger.dev.precad.model.Aluno;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends AbstractRepository<Aluno, UUID> {
    Optional<Aluno> findByCpf(String cpf);
}