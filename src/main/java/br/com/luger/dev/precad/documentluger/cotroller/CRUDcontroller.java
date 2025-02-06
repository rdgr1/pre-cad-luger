package br.com.luger.dev.precad.documentluger.cotroller;


import br.com.luger.dev.precad.documentluger.model.Documents;
import br.com.luger.dev.precad.documentluger.service.interfaces.CRUDservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CRUDcontroller<T> {

    public abstract CRUDservice<T> getService();


    @PostMapping
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public ResponseEntity<T> save(@Validated @RequestBody T entity) {
        return ResponseEntity.ok(getService().save(entity));
    }


    @GetMapping
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public List<T> findAll() {
        return getService().findAll();
    }

    @GetMapping("/findByIdCadastroCurso/{idCadastroCurso}")
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public List<T> findByIdCadastroCurso(@PathVariable UUID idCadastroCurso) {
        return getService().findByIdCadastroCurso(idCadastroCurso);
    }

    @GetMapping("/findById/{id}")
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public Optional<T> findById(@PathVariable UUID id) {
        return getService().findById(id);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public ResponseEntity<String> delete(
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "type") String type) {
        Optional<Documents> doc = getService().findByIdCadastroCursoAndDocumentType(id, type);
        if (doc.isEmpty()) {
            return ResponseEntity.badRequest().body("id not found");
        }
        System.out.println(doc.get().getId());

        getService().delete(doc.get().getId());
        return null;
    }


}