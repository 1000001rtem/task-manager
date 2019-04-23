package ru.eremin.tm.server.model.service.api;

import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.model.dto.AbstractDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor av.eremin on 15.04.2019.
 */

public interface IBasedService<T extends AbstractEntity, E extends AbstractDTO> extends IService<T, E> {

    List<E> findByUserId(String userId) throws AccessForbiddenException;

    void removeAll(String userId) throws AccessForbiddenException;

    List<E> findAllSortedByCreateDate(String userId) throws AccessForbiddenException;

    List<E> findAllSortedByStartDate(String userId) throws AccessForbiddenException;

    List<E> findAllSortedByEndDate(String userId) throws AccessForbiddenException;

    List<E> findAllSortedByStatus(String userId) throws AccessForbiddenException;

}
