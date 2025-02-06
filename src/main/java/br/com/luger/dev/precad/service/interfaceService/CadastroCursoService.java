package br.com.luger.dev.precad.service.interfaceService;

import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CadastroCursoService extends CRUDservice<CadastroCurso> {

    long contarCadastrosPorClasse(Classe classe);

    Optional<CadastroCurso> findByCpf(String cpf);

    void deleteByCpf(String cpf);

    Page<CadastroCurso> findByStatus(String status, Pageable pageable);

    void salvarCadastroCurso(UUID classeId);

    void removerCadastroCurso(UUID classeId);
}
