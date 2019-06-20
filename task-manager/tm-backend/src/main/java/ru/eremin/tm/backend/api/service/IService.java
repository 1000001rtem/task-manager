package ru.eremin.tm.backend.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.AbstractDTO;
import ru.eremin.tm.backend.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IService<T extends AbstractEntity, E extends AbstractDTO> {

    @NotNull
    List<E> findAll();

    @Nullable
    E findOne(@Nullable String id) throws IncorrectDataException;

    void persist(@Nullable E e) throws IncorrectDataException;

    void update(@Nullable E e) throws IncorrectDataException;

    void merge(@Nullable E e) throws IncorrectDataException;

    void remove(@Nullable String id) throws IncorrectDataException;

    boolean isExist(@Nullable String id);

    @NotNull
    T getEntity(@NotNull E e);

}
