package ru.eremin.tm.model.repository.api;

import ru.eremin.tm.model.entity.User;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserRepository extends IRepository<User> {

    User findByLogin(String login);

}
