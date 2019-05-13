package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IAuthService;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.repository.UserRepository;

/**
 * @autor av.eremin on 13.05.2019.
 */

@NoArgsConstructor
public enum AuthService implements IAuthService {

    INSTANCE;

    @NotNull
    private final IUserRepository userRepository = UserRepository.INSTANCE;

    @Nullable
    @Override
    public UserDTO check(@Nullable final String login, @Nullable final String password) {
        @Nullable final User user = userRepository.findByLogin(login);
        if (user == null) return null;
        if (!user.getHashPassword().equals(password)) return null;
        return new UserDTO(user);
    }

}
