package ru.eremin.tm.server.model.repository.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.entity.AbstractEntity;

import java.util.List;

/**
 * @autor av.eremin on 23.04.2019.
 */

public interface IBasedRepository<T extends AbstractEntity> extends IRepository<T> {

    List<T> findByUserId(@NotNull String userId);

    void removeAll(@NotNull String userId);

    List<T> findAllSortedByCreateDate(@NotNull String userId);

    List<T> findAllSortedByStartDate(@NotNull String userId);

    List<T> findAllSortedByEndDate(@NotNull String userId);

    List<T> findAllSortedByStatus(@NotNull String userId);

    List<T> findByName(@NotNull String userId, @NotNull String name);

    List<T> findByDescription(@NotNull String userId, @NotNull String description);

}
