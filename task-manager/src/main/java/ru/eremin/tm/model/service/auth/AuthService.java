package ru.eremin.tm.model.service.auth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.model.service.api.IAuthService;
import ru.eremin.tm.model.service.api.IUserService;

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
    public Session login(@Nullable final String login, @Nullable final String hashPassword) {
        if (login == null || login.isEmpty()) return null;
        if (hashPassword == null || hashPassword.isEmpty()) return null;
        @Nullable final UserDTO userDTO = userService.findByLogin(login);
        if (userDTO == null || !userDTO.getHashPassword().equals(hashPassword)) return null;
        @NotNull final Session session = new Session(userDTO);
        return session;
    }

    @Override
    public Session logout(@NotNull final Session session) {
        return null;
    }

}
