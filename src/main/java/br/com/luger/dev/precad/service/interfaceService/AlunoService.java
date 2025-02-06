package br.com.luger.dev.precad.service.interfaceService;

import br.com.luger.dev.precad.model.Aluno;

import java.util.Optional;

public interface AlunoService extends CRUDservice<Aluno> {

    Optional<Aluno> findByCpf(String cpf);
}
