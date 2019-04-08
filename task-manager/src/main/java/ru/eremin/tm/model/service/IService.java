package ru.eremin.tm.model.service;

import ru.eremin.tm.model.dto.AbstractDTO;
import ru.eremin.tm.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IService<T extends AbstractEntity, E extends AbstractDTO> {

    List<E> findAll();

    E findById(String id);

    void insert(E e);

    void update(E e);

    void delete(E e);

    void deleteAll();

    T getEntity(E e);

}
