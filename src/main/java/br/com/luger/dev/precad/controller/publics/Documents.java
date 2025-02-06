package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.CadastroCursoController;
import br.com.luger.dev.precad.dto.DocumentsDTOid;
import br.com.luger.dev.precad.dto.DocumentsDto;
import br.com.luger.dev.precad.enums.TipoDocumentoCadastroCurso;
import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.service.DocumentsServiceAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public/doc")
public class Documents {

    private final DocumentsServiceAPI service;
    private final CadastroCursoController cadastroCursoController;

    @Autowired
    public Documents(DocumentsServiceAPI service, CadastroCursoController cadastroCursoController) {
        this.service = service;
        this.cadastroCursoController = cadastroCursoController;
    }

    public static List<String> getDocumentTypeList(CadastroCurso cadastroCursoUser) {
        return Arrays.stream(TipoDocumentoCadastroCurso.values())
                .filter(tipoDocumento ->
                        !(
                                (!cadastroCursoUser.getSexo().equals("MASCULINO") && (tipoDocumento.name().equals("CR") || tipoDocumento.name().equals("CRV"))) ||
                                        (cadastroCursoUser.getIdentidadePossuiCpf() && tipoDocumento.name().equals("CPF")) ||
                                        (cadastroCursoUser.getTipoProcesso().equals("Curso") && tipoDocumento.name().equals("CNV")) ||
                                        (cadastroCursoUser.getEnderecoUf().equals("DF") && (tipoDocumento.name().equals("CNJMER") || tipoDocumento.name().equals("CNJFR") || tipoDocumento.name().equals("CNJER"))) ||
                                        (cadastroCursoUser.getTipoIdentidade().equals("CNH") && tipoDocumento.name().equals("DI")) ||
                                        (cadastroCursoUser.getTipoIdentidade().equals("CNH") && tipoDocumento.name().equals("DIV")) ||
                                        (cadastroCursoUser.getTipoIdentidade().equals("RG") && tipoDocumento.name().equals("CNH"))
                        ))
                .map(TipoDocumentoCadastroCurso::name)
                .collect(Collectors.toList());
    }

    @PostMapping("/add/{cpf}")
    public ResponseEntity<String> addDocuments(@PathVariable String cpf, @RequestBody DocumentsDto dto) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        dto.setIdCadastroCurso(aluno.get().getId());
        return service.postDocument(dto);
    }

    @GetMapping("/all/{cpf}")
    public ResponseEntity<List<DocumentsDTOid>> getDocuments(@PathVariable String cpf) throws JsonProcessingException {
        System.out.println("CPF buscado: " + cpf);

        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            System.out.println("Aluno não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UUID id = aluno.get().getId();
        System.out.println("ID do aluno: " + id);

        ResponseEntity<List<Map<String, Object>>> listResponseEntity = service.getListDocuments(id);
        System.out.println("Resposta de getListDocuments: " + listResponseEntity);

        if (listResponseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(listResponseEntity.getStatusCode()).body(null);
        }

        List<Map<String, Object>> listMapping = listResponseEntity.getBody();
        if (listMapping == null || listMapping.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<DocumentsDTOid> documentsList = listMapping.stream()
                .map(this::mapToDocumentsDTOid)
                .toList();

        documentsList.forEach(doc -> System.out.println("ID do documento: " + doc.getIdCadastroCurso()));

        List<DocumentsDTOid> documentsListUser = documentsList.stream()
                .filter(doc -> doc.getIdCadastroCurso().equals(id))
                .collect(Collectors.toList());

        System.out.println("Documentos filtrados: " + documentsListUser.size());

        return ResponseEntity.ok(documentsListUser);
    }

    @PutMapping("/update/{cpf}")
    public ResponseEntity<String> updateDocuments(@PathVariable String cpf, @RequestBody @Validated DocumentsDto dto) {
        Optional<CadastroCurso> aluno = cadastroCursoController.findByCpf(cpf);
        if (aluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        dto.setIdCadastroCurso(aluno.get().getId());
        return service.updateDocument(aluno.get().getId(), dto);
    }

    @GetMapping("/isntfilleds/{cpf}")
    public ResponseEntity<List<String>> isntFilleds(@PathVariable String cpf) {
        try {
            ResponseEntity<List<DocumentsDTOid>> listResponseEntity = getDocuments(cpf);
            if (listResponseEntity.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(listResponseEntity.getStatusCode()).build();
            }

            List<DocumentsDTOid> documentsList = listResponseEntity.getBody();
            if (documentsList == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Erro ao obter documentos"));
            }

            Optional<CadastroCurso> user = cadastroCursoController.findByCpf(cpf);
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("Usuário não encontrado"));
            }

            CadastroCurso cadastroCursoUser = user.get();
            List<String> documentTypeList = getDocumentTypeList(cadastroCursoUser);

            List<String> listaDocumentType = documentsList.stream()
                    .map(DocumentsDTOid::getDocumentType)
                    .toList();

            List<String> valoresNaoPertencentes = documentTypeList.stream()
                    .filter(documentType -> !listaDocumentType.contains(documentType))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(valoresNaoPertencentes);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Erro ao processar documentos"));
        }
    }

    private DocumentsDTOid mapToDocumentsDTOid(Map<String, Object> item) {
        DocumentsDTOid documentsDto = new DocumentsDTOid();
        documentsDto.setId((UUID) item.get("id"));
        documentsDto.setDocumentType(TipoDocumentoCadastroCurso.getInstance((String) item.get("documentType")).name());
        documentsDto.setIdCadastroCurso((UUID) item.get("idCadastroCurso"));
        documentsDto.setContent((String) item.get("content"));
        documentsDto.setDocumentExtencao((String) item.get("documentExtencao"));
        documentsDto.setReplacementAdditionalText((String) item.get("replacementAdditionalText"));
        String validityString = (String) item.get("validity");
        if (validityString != null) {
            documentsDto.setValidity(LocalDate.parse(validityString, DateTimeFormatter.ISO_DATE));
        }
        documentsDto.setStatus((String) item.get("status"));
        return documentsDto;
    }


}
