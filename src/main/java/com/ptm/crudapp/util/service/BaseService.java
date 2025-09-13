package com.ptm.crudapp.util.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, K> {
    E save(E entity);
    Optional<E> findById(K id);
    List<E> findAll();
    E update(K id, E entity);
    void deleteById(K id);
}
