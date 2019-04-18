package ru.eremin.tm.server.model.service.api;

import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.AbstractDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IService<T extends AbstractEntity, E extends AbstractDTO> {

    E findOne(String id);

    void persist(E e);

    void merge(E e);

    void update(E e);

    void remove(String id) throws IncorrectDataException;

    boolean isExist(String id);

    T getEntity(E e);

}
