package ru.eremin.tm.model.service.api;

import ru.eremin.tm.model.dto.base.AbstractDTO;
import ru.eremin.tm.model.entity.base.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IService<T extends AbstractEntity, E extends AbstractDTO> {

    List<E> findAll();

    E findOne(String id);

    void persist(E e);

    void merge(E e);

    void update(E e);

    boolean remove(String id);

    boolean isExist(String id);

    T getEntity(E e);

}
