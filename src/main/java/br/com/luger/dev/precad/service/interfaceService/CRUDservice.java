package br.com.luger.dev.precad.service.interfaceService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CRUDservice<T> {

    Page<T> findAll(Pageable pageable);


    T save(T entity);

    void delete(UUID id);

    Optional<T> findById(UUID id);


}