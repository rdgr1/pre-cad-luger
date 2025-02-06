package br.com.luger.dev.precad.documentluger.cotroller;


import br.com.luger.dev.precad.documentluger.dto.DocumentsDTO;
import br.com.luger.dev.precad.documentluger.dto.DocumentsDTOid;
import br.com.luger.dev.precad.documentluger.enums.StatusCadastroCursoDocumento;
import br.com.luger.dev.precad.documentluger.enums.TipoDocumentoCadastroCurso;
import br.com.luger.dev.precad.documentluger.enums.TipoExtencao;
import br.com.luger.dev.precad.documentluger.model.Documents;
import br.com.luger.dev.precad.documentluger.service.interfaces.CRUDservice;
import br.com.luger.dev.precad.documentluger.service.interfaces.DocumentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jcraft.jsch.*;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

@NoArgsConstructor(force = true)
@RestController
@RequestMapping(value = "/public/documents/api")
public class DocumentsController extends CRUDcontroller<Documents> {

    private final DocumentsService cadastroCursoDocumentoService;
    private final String caminhoNoServidor = "/home/developer/lugerDoc/";
    @Value("${sftp.host}")
    private String SFTP_HOST;
    @Value("${sftp.port}")
    private int SFTP_PORT;
    @Value("${sftp.user}")
    private String SFTP_USER;
    @Value("${sftp.pass}")
    private String SFTP_PASS;

    @Autowired
    public DocumentsController(DocumentsService cadastroCursoDocumentoService) {
        this.cadastroCursoDocumentoService = cadastroCursoDocumentoService;
    }

    @Override
    public CRUDservice<Documents> getService() {
        return cadastroCursoDocumentoService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDocuments(@RequestBody @Validated DocumentsDTO dto) {
        String extencao;
        switch (dto.getDocumentExtencao()) {
            case "PDF" -> extencao = TipoExtencao.PDF.getLabel();
            case "JPG" -> extencao = TipoExtencao.JPG.getLabel();
            case "PNG" -> extencao = TipoExtencao.PNG.getLabel();
            default -> {
                return ResponseEntity.status(404).body("status error");
            }
        }
        String tipoDocumento;
        switch (dto.getDocumentType()) {
            case "F" -> tipoDocumento = TipoDocumentoCadastroCurso.F.getLabel();
            case "DI" -> tipoDocumento = TipoDocumentoCadastroCurso.DI.getLabel();
            case "DIV" -> tipoDocumento = TipoDocumentoCadastroCurso.DIV.getLabel();
            case "CNH" -> tipoDocumento = TipoDocumentoCadastroCurso.CNH.getLabel();
            case "CPF" -> tipoDocumento = TipoDocumentoCadastroCurso.CPF.getLabel();
            case "SCC" -> tipoDocumento = TipoDocumentoCadastroCurso.SCC.getLabel();
            case "TE" -> tipoDocumento = TipoDocumentoCadastroCurso.TE.getLabel();
            case "TEV" -> tipoDocumento = TipoDocumentoCadastroCurso.TEV.getLabel();
            case "CR" -> tipoDocumento = TipoDocumentoCadastroCurso.CR.getLabel();
            case "CRV" -> tipoDocumento = TipoDocumentoCadastroCurso.CRV.getLabel();
            case "CE" -> tipoDocumento = TipoDocumentoCadastroCurso.CE.getLabel();
            case "CEV" -> tipoDocumento = TipoDocumentoCadastroCurso.CEV.getLabel();
            case "ES" -> tipoDocumento = TipoDocumentoCadastroCurso.ES.getLabel();
            case "EAP" -> tipoDocumento = TipoDocumentoCadastroCurso.EAP.getLabel();
            case "CEND" -> tipoDocumento = TipoDocumentoCadastroCurso.CEND.getLabel();
            case "CQE" -> tipoDocumento = TipoDocumentoCadastroCurso.CQE.getLabel();
            case "CNJF" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJF.getLabel();
            case "CNJFR" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJFR.getLabel();
            case "CNJE" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJE.getLabel();
            case "CNJER" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJER.getLabel();
            case "CNJMU" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJMU.getLabel();
            case "CNJME" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJME.getLabel();
            case "CNJMER" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJMER.getLabel();
            case "CNJEL" -> tipoDocumento = TipoDocumentoCadastroCurso.CNJEL.getLabel();
            default -> {
                return ResponseEntity.status(404).body("type documents error");
            }
        }

        Documents documents = new Documents();
        BeanUtils.copyProperties(dto, documents);
        documents.setDocumentType(tipoDocumento);
        ResponseEntity<Documents> documentsResponseEntity;
        try {
            documentsResponseEntity = save(documents);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

        if (!documentsResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(404).body("documents error");
        }

        String fileName = Objects.requireNonNull(documentsResponseEntity.getBody()).getId() + extencao;
        String remoteFilePath = caminhoNoServidor + documentsResponseEntity.getBody().getIdCadastroCurso() + "/" + fileName;
        byte[] decodedBytes = Base64.getDecoder().decode(dto.getContent());

        try {
            uploadFileToSftp(decodedBytes, remoteFilePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        Documents doc = documentsResponseEntity.getBody();
        doc.setPath(remoteFilePath);

        ResponseEntity<Documents> documentsResponseEntity2 = save(doc);
        if (!documentsResponseEntity2.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(500).body("error creating documents");
        }

        return ResponseEntity.status(200).body(Objects.requireNonNull(documentsResponseEntity2.getBody()).toString());
    }

    @GetMapping("/idCadastroCurso/{findByIdCadastroCurso}")
    public ResponseEntity<List<Map<String, Object>>> getDocuments(@PathVariable UUID findByIdCadastroCurso) throws JsonProcessingException {
        List<DocumentsDTOid> documentsDTOs = new ArrayList<>();
        List<Documents> documentsList = findByIdCadastroCurso(findByIdCadastroCurso);

        for (Documents document : documentsList) {
            String extensao = FilenameUtils.getExtension(document.getPath());
            String encodedString;
            try {
                byte[] fileContent = downloadFileFromSftp(document.getPath());
                encodedString = Base64.getEncoder().encodeToString(fileContent);
                DocumentsDTOid documentDTO = new DocumentsDTOid();
                BeanUtils.copyProperties(document, documentDTO);
                documentDTO.setContent(encodedString);
                documentDTO.setDocumentExtencao(extensao);

                documentsDTOs.add(documentDTO);
            } catch (Exception ignored) {
                getService().delete(document.getId());
                continue;
            }

        }

        List<Map<String, Object>> documentsDTOsAsMap = new ArrayList<>();
        for (DocumentsDTOid documentsDTO : documentsDTOs) {
            Map<String, Object> documentsDTOMap = new HashMap<>();
            documentsDTOMap.put("id", documentsDTO.getId());
            documentsDTOMap.put("documentType", documentsDTO.getDocumentType());
            documentsDTOMap.put("idCadastroCurso", documentsDTO.getIdCadastroCurso());
            documentsDTOMap.put("content", documentsDTO.getContent());
            documentsDTOMap.put("documentExtencao", documentsDTO.getDocumentExtencao());
            documentsDTOMap.put("replacementAdditionalText", documentsDTO.getReplacementAdditionalText());
            documentsDTOMap.put("validity", documentsDTO.getValidity());
            documentsDTOMap.put("status", documentsDTO.getStatus());
            System.out.print("{\n.....id:" + documentsDTO.getId() + "\n.....documentType" + documentsDTO.getDocumentType() + "\n.....idCadastroCurso" + documentsDTO.getIdCadastroCurso() + "\n.....content" + documentsDTO.getContent().length() + "\n.....documentExtencao" + documentsDTO.getDocumentExtencao() + "\n.....replacementAdditionalText" + documentsDTO.getReplacementAdditionalText() + "\n.....validity" + documentsDTO.getValidity() + "\n.....status" + documentsDTO.getStatus());

            documentsDTOsAsMap.add(documentsDTOMap);
        }

        return ResponseEntity.ok().body(documentsDTOsAsMap);
    }

    private void uploadFileToSftp(byte[] fileContent, String remoteFilePath) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASS);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        try {
            session.connect();
            if (!session.isConnected()) {
                System.out.println("Falha ao estabelecer conexão com o servidor.");
                return;
            }

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            if (!channelSftp.isConnected()) {
                System.out.println("Falha ao abrir canal SFTP.");
                return;
            }

            String parentDir = null;
            try {
                // Verifica se o diretório pai existe
                parentDir = remoteFilePath.substring(0, remoteFilePath.lastIndexOf('/'));
                channelSftp.stat(parentDir);
            } catch (SftpException e) {
                // Diretório pai não existe, cria-o
                channelSftp.mkdir(parentDir);
            }

            try (ByteArrayInputStream bais = new ByteArrayInputStream(fileContent)) {
                channelSftp.put(bais, remoteFilePath);
                System.out.println("Arquivo enviado com sucesso para " + remoteFilePath);
            } catch (Exception e) {
                System.out.println("Erro ao enviar o arquivo: " + e.getMessage());
            } finally {
                channelSftp.disconnect();
                session.disconnect();
                System.out.println("Desconectado do servidor.");
            }
        } catch (JSchException e) {
            System.out.println("Erro ao conectar-se ao servidor: " + e.getMessage());
        }
    }

    private byte[] downloadFileFromSftp(String remoteFilePath) throws Exception {
        System.out.println("Iniciando conexão com o servidor SFTP...");
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASS);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();
        System.out.println("Conexão estabelecida com sucesso.");

        System.out.println("Abrindo canal SFTP...");
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        System.out.println("Canal SFTP aberto com sucesso.");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            System.out.println("Baixando arquivo do servidor...");
            channelSftp.get(remoteFilePath, baos);
            System.out.println("Arquivo baixado com sucesso de " + remoteFilePath);
            return baos.toByteArray();
        } finally {
            System.out.println("Fechando recursos e desconectando do servidor...");
            baos.close();
            channelSftp.disconnect();
            session.disconnect();
            System.out.println("Desconectado do servidor.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDocuments(@RequestParam(name = "id") UUID id, @RequestBody @Validated DocumentsDTO dto) {
        String extencao;
        switch (dto.getDocumentExtencao()) {
            case "PDF" -> extencao = TipoExtencao.PDF.getLabel();
            case "JPG" -> extencao = TipoExtencao.JPG.getLabel();
            case "PNG" -> extencao = TipoExtencao.PNG.getLabel();
            default -> {
                return ResponseEntity.status(404).body("status error");
            }
        }

        String fileName = id + extencao;

        String remoteFilePath = caminhoNoServidor + dto.getIdCadastroCurso() + "/" + fileName;
        byte[] decodedBytes = Base64.getDecoder().decode(dto.getContent());

        try {
            uploadFileToSftp(decodedBytes, remoteFilePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        Documents doc = new Documents();
        BeanUtils.copyProperties(dto, doc);
        doc.setPath(remoteFilePath);
        doc.setId(id);
        doc.setStatus(StatusCadastroCursoDocumento.SD.getLabel());

        ResponseEntity<Documents> documentsResponseEntity2 = save(doc);
        if (!documentsResponseEntity2.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(500).body("error creating documents");
        }

        return ResponseEntity.status(200).body(Objects.requireNonNull(documentsResponseEntity2.getBody()).toString());
    }

    @DeleteMapping("/deleteByCadastroCursoId/{idCadastroCurso}")
    public ResponseEntity<String> deleteByCadastroCursoId(@PathVariable UUID idCadastroCurso) {
        // Delete all documents associated with the cadastroCursoId
        try {
            getService().deleteByIdCadastroCurso(idCadastroCurso);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting documents associated with cadastroCursoId (" + e.getMessage() + ")");
        }

        // Delete the folder with the name equal to cadastroCursoId
        String remoteFolderPath = caminhoNoServidor + idCadastroCurso;
        try {
            deleteDirectoryFromSftp(remoteFolderPath);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting folder: " + e.getMessage());
        }

        return ResponseEntity.status(200).body("Successfully deleted documents and folder");
    }

    private void deleteDirectoryFromSftp(String remoteFolderPath) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASS);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        try {
            List<ChannelSftp.LsEntry> files = channelSftp.ls(remoteFolderPath);
            for (ChannelSftp.LsEntry file : files) {
                String filePath = remoteFolderPath + "/" + file.getFilename();
                if (!file.getAttrs().isDir()) {
                    channelSftp.rm(filePath);
                } else if (!file.getFilename().equals(".") && !file.getFilename().equals("..")) {
                    deleteDirectoryFromSftp(filePath);
                }
            }
            channelSftp.rmdir(remoteFolderPath);
        } finally {
            channelSftp.disconnect();
            session.disconnect();
        }
    }

    @PutMapping("/update/status")
    public ResponseEntity<String> updateStatusDocuments(@RequestParam(name = "id") UUID id, @RequestParam(name = "type") String type, @RequestParam(name = "status") String status) {

        assert cadastroCursoDocumentoService != null;
        Optional<Documents> doc = cadastroCursoDocumentoService.findByIdCadastroCursoAndDocumentType(id, type);
        if (doc.isEmpty()) {
            return ResponseEntity.badRequest().body("id not found");
        }

        String exte;
        switch (status) {
            case "A" -> exte = StatusCadastroCursoDocumento.A.getLabel();
            case "AS" -> exte = StatusCadastroCursoDocumento.AS.getLabel();
            case "SD" -> exte = StatusCadastroCursoDocumento.SD.getLabel();
            default -> {
                return ResponseEntity.status(404).body("status error NOT enums (A S SD)");
            }
        }

        doc.get().setStatus(exte);
        ResponseEntity<Documents> documentsResponseEntity2 = save(doc.get());
        if (!documentsResponseEntity2.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(500).body("error creating documents");
        }

        return ResponseEntity.status(200).body(Objects.requireNonNull(documentsResponseEntity2.getBody()).toString());
    }

}


