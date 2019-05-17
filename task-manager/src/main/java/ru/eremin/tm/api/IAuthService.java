package ru.eremin.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;

/**
 * @autor av.eremin on 13.05.2019.
 */

public interface IAuthService {

    UserDTO check(@Nullable String login, @Nullable String password) throws IncorrectDataException;

}
