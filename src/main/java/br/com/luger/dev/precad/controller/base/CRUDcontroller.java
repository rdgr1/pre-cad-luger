package br.com.luger.dev.precad.controller.base;


import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Page<T> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return getService().findAll(pageable);
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint protegido")
    @SecurityRequirement(name = "Bearer Authentication", scopes = "global")
    @Parameters({
            @Parameter(name = "Authorization",
                    required = true,
                    schema = @Schema(type = "string", format = "bearer token"),
                    in = ParameterIn.HEADER
            )})
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        getService().delete(id);
        return null;
    }


}