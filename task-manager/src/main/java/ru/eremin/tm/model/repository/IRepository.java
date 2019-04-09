package ru.eremin.tm.model.repository;

import ru.eremin.tm.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IRepository<T extends AbstractEntity> {

    List<T> findAll();

    T findById(String id);

    void insert(T t);

    void update(T t);

    void delete(String id);

    void deleteAll();

}
