package ru.eremin.tm.server.api;

import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IRepository<T extends AbstractEntity> {

    List<T> findAll();

    T findOne(String id);

    void persist(T t);

    void merge(T t);

    void update(T t) throws IncorrectDataException;

    void remove(String id);

}
