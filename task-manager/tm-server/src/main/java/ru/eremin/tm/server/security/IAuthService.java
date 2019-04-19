package ru.eremin.tm.server.security;

import ru.eremin.tm.server.model.entity.session.Session;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IAuthService {

    Session login(String login, String hashPassword);

}
