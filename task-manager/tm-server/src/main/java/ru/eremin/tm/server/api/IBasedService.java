package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.model.dto.AbstractDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;
import ru.eremin.tm.server.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 15.04.2019.
 */

public interface IBasedService<T extends AbstractEntity, E extends AbstractDTO> extends IService<T, E> {

    List<E> findByUserId(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    void removeAll(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    List<E> findAllSortedByCreateDate(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    List<E> findAllSortedByStartDate(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    List<E> findAllSortedByEndDate(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    List<E> findAllSortedByStatus(@Nullable UserDTO userDTO) throws AccessForbiddenException;

    List<E> findByName(@Nullable UserDTO userDTO, @Nullable String name) throws AccessForbiddenException;

    List<E> findByDescription(@Nullable UserDTO userDTO, @Nullable String description) throws AccessForbiddenException;

}
