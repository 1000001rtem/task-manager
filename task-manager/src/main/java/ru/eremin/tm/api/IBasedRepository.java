package ru.eremin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.model.entity.AbstractEntity;
import ru.eremin.tm.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 23.04.2019.
 */

public interface IBasedRepository<T extends AbstractEntity> extends IRepository<T> {

    List<T> findByUserId(@NotNull User user);

    void removeAll(@NotNull User user);

    List<T> findAllSortedByCreateDate(@NotNull User user);

    List<T> findAllSortedByStartDate(@NotNull User user);

    List<T> findAllSortedByEndDate(@NotNull User user);

    List<T> findAllSortedByStatus(@NotNull User user);

    List<T> findByName(@NotNull User user, @NotNull String name);

    List<T> findByDescription(@NotNull User user, @NotNull String description);

}
