package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.api.IAuthService;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;

/**
 * @autor av.eremin on 13.05.2019.
 */

@NoArgsConstructor
@Service(AuthService.NAME)
public class AuthService implements IAuthService {

    public static final String NAME = "authService";

    @NotNull
    @Autowired
    private IUserRepository userRepository;

    @Nullable
    @Override
    @Transactional(readOnly = true)
    public UserDTO check(@Nullable final String login, @Nullable final String password) throws IncorrectDataException {
        @Nullable final User user = userRepository.findByLogin(login);
        if (user == null) return null;
        if (!user.getHashPassword().equals(password)) return null;
        return new UserDTO(user);
    }

}
