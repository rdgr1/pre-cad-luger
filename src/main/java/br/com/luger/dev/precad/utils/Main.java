package br.com.luger.dev.precad.utils;

import br.com.luger.dev.precad.dto.DocumentsDto;
import br.com.luger.dev.precad.enums.TipoDocumentoCadastroCurso;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        List<DocumentsDto> documentsList = obterListaDocumentos();

        List<String> listaDocumentType = documentsList.stream()
                .map(DocumentsDto::getDocumentType)
                .toList();


        List<String> documentTypeList = new ArrayList<>(); // = Arrays.asList("F", "DI", "DIV", "CNH", "CPF", "SCC", "TE", "TEV", "CR", "CRV", "CE", "CEV", "ES", "EAP", "CEND", "CQE", "CNJF", "CNJFR", "CNJE", "CNJER", "CNJMU", "CNJME", "CNJMER", "CNJEL");
        for (TipoDocumentoCadastroCurso tipoDocumento : TipoDocumentoCadastroCurso.values()) {
            documentTypeList.add(tipoDocumento.name());
        }
        List<String> valoresNaoPertencentes = documentTypeList.stream()
                .filter(documentType -> !listaDocumentType.contains(documentType))
                .collect(Collectors.toList());

        System.out.println("Valores não pertencentes à lista fornecida: " + valoresNaoPertencentes);
        System.out.println(documentsList);
    }


    private static List<DocumentsDto> obterListaDocumentos() {
        List<DocumentsDto> documentsList = new ArrayList<>();

        // Preencha a lista com exemplos de DocumentsDto
        documentsList.add(createDocument("DIV"));
        documentsList.add(createDocument("DI"));
        documentsList.add(createDocument("CNH"));
        documentsList.add(createDocument("SCC"));
        documentsList.add(createDocument("ES"));
        documentsList.add(createDocument("EAP"));
        return documentsList;
    }

    private static DocumentsDto createDocument(String documentType) {
        DocumentsDto document = new DocumentsDto();
        document.setDocumentType(documentType);
        document.setIdCadastroCurso(UUID.randomUUID());
        document.setContent("Base64ContentExample");
        document.setDocumentExtencao("PDF");
        document.setReplacementAdditionalText("UpdateTextExample");
        document.setValidity(LocalDate.now());

        return document;
    }
}