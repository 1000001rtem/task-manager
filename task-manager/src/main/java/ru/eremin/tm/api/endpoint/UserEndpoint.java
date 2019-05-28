package ru.eremin.tm.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.UserDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService
public interface UserEndpoint {

    List<UserDTO> findAllUsers();

    UserDTO findOneUser(@Nullable final String userId) throws IncorrectDataException;

    UserDTO findUserByLogin(@Nullable final String login) throws IncorrectDataException;

    ResultDTO createUser(@Nullable final String login, @Nullable final String password) throws IncorrectDataException;

    ResultDTO changeUserPassword(@Nullable final String userId, @Nullable final String oldPassword, @Nullable final String newPassword) throws IncorrectDataException;

    ResultDTO updateUser(@Nullable final UserDTO userDTO) throws IncorrectDataException;

    ResultDTO removeUser(@Nullable final String userId) throws IncorrectDataException;

}
