package ru.eremin.tm.server.model.service.api;

import ru.eremin.tm.server.model.dto.AbstractDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor av.eremin on 15.04.2019.
 */

public interface IBasedService<T extends AbstractEntity, E extends AbstractDTO> extends IService<T, E> {

    List<E> findAll(String userId);

    void removeAll(String userId);

    List<E> findAllSortedByCreateDate(String userId);

    List<E> findAllSortedByStartDate(String userId);

    List<E> findAllSortedByEndDate(String userId);

    List<E> findAllSortedByStatus(String userId);

}
