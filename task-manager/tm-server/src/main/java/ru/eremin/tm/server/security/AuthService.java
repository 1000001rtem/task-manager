package ru.eremin.tm.server.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.service.api.IUserService;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class AuthService implements IAuthService {

    @Nullable
    private IUserService userService;

    public AuthService(@Nullable final IUserService userService) {
        if (userService == null) return;
        this.userService = userService;
    }

    @Override
    @Nullable
    public SessionDTO login(@Nullable final String login, @Nullable final String hashPassword) {
        if (login == null || login.isEmpty()) return null;
        if (hashPassword == null || hashPassword.isEmpty()) return null;
        @Nullable final UserDTO userDTO = userService.findByLogin(login);
        if (userDTO == null || !userDTO.getHashPassword().equals(hashPassword)) return null;
        @NotNull final SessionDTO session = new SessionDTO(userDTO);
        return session;
    }

}
