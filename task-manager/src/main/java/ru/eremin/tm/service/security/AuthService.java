package ru.eremin.tm.service.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.api.IAuthService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.repository.UserRepository;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Service
public class AuthService implements IAuthService {

    @NotNull
    @Autowired
    private UserRepository userRepository;

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
