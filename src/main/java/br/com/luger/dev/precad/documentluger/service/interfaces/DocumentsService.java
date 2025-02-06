package br.com.luger.dev.precad.documentluger.service.interfaces;

import br.com.luger.dev.precad.documentluger.model.Documents;

import java.util.Optional;
import java.util.UUID;

public interface DocumentsService extends CRUDservice<Documents> {
    Optional<Documents> findByIdCadastroCursoAndDocumentType(UUID id, String type);
}
