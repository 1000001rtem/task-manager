package ru.eremin.tm.server.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;

import javax.inject.Inject;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class AuthService implements IAuthService {

    @Inject
    @Nullable
    private IUserService userService;

    @NotNull
    @Override
    public SessionDTO login(@Nullable final String login, @Nullable final String hashPassword) throws IncorrectDataException, AccessForbiddenException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Empty login");
        if (hashPassword == null || hashPassword.isEmpty()) throw new IncorrectDataException("Empty password");
        @Nullable final UserDTO userDTO = userService.findByLogin(login);
        if (userDTO == null || !userDTO.getHashPassword().equals(hashPassword))
            throw new AccessForbiddenException("Wrong login or password");
        @NotNull final SessionDTO session = new SessionDTO(userDTO);
        return session;
    }

}
