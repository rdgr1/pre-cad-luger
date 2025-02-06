package br.com.luger.dev.precad.documentluger.service;


import br.com.luger.dev.precad.documentluger.repository.AbstractRepository;
import br.com.luger.dev.precad.documentluger.service.interfaces.CRUDservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CRUDserviceImplement<T> implements CRUDservice<T> {
    protected abstract AbstractRepository<T, UUID> getRepository();

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }


    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(UUID id) {
        getRepository().deleteById(id);
    }

    @Override
    public Optional<T> findById(UUID id) {
        return getRepository().findById(id);
    }
}
