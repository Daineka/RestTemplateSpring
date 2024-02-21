package com.daineka.service;

import java.util.List;

public interface Service<T> {
    T saveOrUpdate(T entity);

    T get(Long id);

    void delete(Long id);

    List<T> getAll();
}