package br.com.luger.dev.precad.documentluger.repository;


import br.com.luger.dev.precad.documentluger.model.Documents;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface DocumentsRepository extends AbstractRepository<Documents, UUID> {

    void deleteByIdCadastroCurso(UUID idCadastroCurso);

    Optional<Documents> findByIdCadastroCursoAndDocumentType(UUID id, String type);

    List<Documents> findByIdCadastroCurso(UUID id);
}