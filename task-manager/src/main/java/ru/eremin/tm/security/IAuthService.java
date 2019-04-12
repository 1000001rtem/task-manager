package ru.eremin.tm.security;

import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.session.Session;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IAuthService {

    Session login(final String login, final String hashPassword);

    Session logout(Session session);

}
