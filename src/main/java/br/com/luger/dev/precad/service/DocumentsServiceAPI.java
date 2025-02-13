package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.documentluger.cotroller.DocumentsController;
import br.com.luger.dev.precad.documentluger.dto.DocumentsDTO;
import br.com.luger.dev.precad.dto.DocumentsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DocumentsServiceAPI {

    private static final Logger logger = LoggerFactory.getLogger(DocumentsServiceAPI.class);
    private final DocumentsController documentsController;
    private final RestTemplate restTemplate;
    @Value("${API.Documents}")
    private String baseUrl;


    @Autowired
    public DocumentsServiceAPI(DocumentsController documentsController, RestTemplate restTemplate) {
        this.documentsController = documentsController;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> updateStatusDocument(UUID id, String type, String status) {

        return documentsController.updateStatusDocuments(id, type, status);
    }

    public ResponseEntity<String> updateDocument(UUID id, DocumentsDto dto) {
        DocumentsDTO newDocument = new DocumentsDTO();
        BeanUtils.copyProperties(dto, newDocument);

        return documentsController.updateDocuments(id, newDocument);

    }

    public ResponseEntity<String> deleteByCadastroCursoIdDocument(UUID idCadastroCurso) {
        return documentsController.deleteByCadastroCursoId(idCadastroCurso);
    }

    public ResponseEntity<String> postDocument(DocumentsDto dto) {
        DocumentsDTO newDocument = new DocumentsDTO();
        BeanUtils.copyProperties(dto, newDocument);
        return documentsController.addDocuments(newDocument);
    }

    public ResponseEntity<List<Map<String, Object>>> getListDocuments(UUID idCadastroCurso) throws JsonProcessingException {
        return documentsController.getDocuments(idCadastroCurso);
    }

    public ResponseEntity<String> deleteDocument(UUID id, String type) {
        return documentsController.delete(id, type);
    }
}
