package ru.eremin.tm.server.security;

import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IAuthService {

    SessionDTO login(String login, String hashPassword);

}
