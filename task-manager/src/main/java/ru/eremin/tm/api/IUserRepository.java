package ru.eremin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.entity.User;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull String login) throws IncorrectDataException;

}
