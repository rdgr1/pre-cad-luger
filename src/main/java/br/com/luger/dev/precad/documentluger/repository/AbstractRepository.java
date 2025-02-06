package br.com.luger.dev.precad.documentluger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


@NoRepositoryBean
public interface AbstractRepository<T, ID> extends PagingAndSortingRepository<T, ID>, JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}