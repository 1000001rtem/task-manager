package ru.eremin.tm.server.service.security;

import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IAuthService {

    SessionDTO login(String login, String hashPassword) throws IncorrectDataException, AccessForbiddenException;

}
