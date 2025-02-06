package br.com.luger.dev.precad.service;


import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public abstract class CRUDserviceImplement<T> implements CRUDservice<T> {
    protected abstract AbstractRepository<T, UUID> getRepository();

    @Override
    public Page<T> findAll(Pageable pageable) {
        int size = pageable.getPageSize();
        int page = pageable.getPageNumber();
        int[] validSizes = {10, 20, 30, 40, 50};
        int finalSize = size;
        if (Arrays.stream(validSizes).noneMatch(s -> s == finalSize)) {
            size = 10;
        }
        Pageable returnPageable = PageRequest.of(page, size);
        return getRepository().findAll(returnPageable);
    }


    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(UUID id) {
    }

    @Override
    public Optional<T> findById(UUID id) {
        return getRepository().findById(id);
    }


}
