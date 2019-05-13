package ru.eremin.tm.server.service.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Service
public class AuthService implements IAuthService {

    @Nullable
    @Autowired
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
