package ru.eremin.tm.model.repository.api;

import ru.eremin.tm.model.entity.base.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IRepository<T extends AbstractEntity> {

    List<T> findAll();

    T findOne(String id);

    void persist(T t);

    void merge(T t);

    void update(T t);

    void remove(String id);

    void remove(List<T> ts);

    void removeAll();

}
