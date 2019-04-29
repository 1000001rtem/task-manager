package ru.eremin.tm.server.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.User;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull String login) throws IncorrectDataException;

}
