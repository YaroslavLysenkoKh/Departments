package com.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> getAll();

    T getById(Long id);

    void delete(T model);

    void addOrUpdate(T model);

}
