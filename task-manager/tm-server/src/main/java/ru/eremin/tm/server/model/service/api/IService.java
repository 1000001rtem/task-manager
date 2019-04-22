package ru.eremin.tm.server.model.service.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.AbstractDTO;
import ru.eremin.tm.server.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IService<T extends AbstractEntity, E extends AbstractDTO> {

    @NotNull
    List<E> findAll();

    @Nullable
    E findOne(@Nullable String id);

    void persist(@Nullable E e);

    void merge(@Nullable E e);

    void update(@Nullable E e);

    void remove(@Nullable String id) throws IncorrectDataException;

    boolean isExist(@Nullable String id);

    @NotNull
    T getEntity(@NotNull E e);

}
