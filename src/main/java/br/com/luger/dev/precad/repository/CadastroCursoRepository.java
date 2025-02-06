package br.com.luger.dev.precad.repository;


import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Classe;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface CadastroCursoRepository extends AbstractRepository<CadastroCurso, UUID> {
    Optional<CadastroCurso> findByCpf(String cpf);

    Page<CadastroCurso> findByStatus(String status, Pageable pageable);

    void deleteByCpf(String cpf);

    void deleteCadastroCursosByCpf(String cpf);

    long countCadastroCursoByClasse(Classe classe);
}