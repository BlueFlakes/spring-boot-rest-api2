package com.stockexchange.dao;

import com.stockexchange.model.PossessId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CommonRepository<T extends PossessId , U extends Serializable> extends CrudRepository<T, U> {

    T findByArchivedIsFalseAndIdEquals(Integer id);
    Iterable<T> findAllByArchivedIsFalse();
}
