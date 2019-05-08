package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IAuthorizationEndpoint {

    void init(String port);

    SessionDTO login(@Nullable String login, @Nullable String password) throws IncorrectDataException, AccessForbiddenException;

    ResultDTO logout(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

}
