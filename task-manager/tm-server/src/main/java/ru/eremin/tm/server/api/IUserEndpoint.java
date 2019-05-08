package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 23.04.2019.
 */

public interface IUserEndpoint {

    void init(String port);

    ResultDTO persistUser(@Nullable UserDTO userDTO) throws AccessForbiddenException, IncorrectDataException;

    List<UserDTO> findAllUsers(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    UserDTO findOneUser(@Nullable SessionDTO sessionDTO, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

    UserDTO findOneUserByLogin(@Nullable SessionDTO sessionDTO, @Nullable String login) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO changeUserPassword(@Nullable SessionDTO sessionDTO, @Nullable String oldPassword, @Nullable String newPassword) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO updateUser(@Nullable SessionDTO sessionDTO, @Nullable UserDTO userDTO) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO removeUser(@Nullable SessionDTO sessionDTO, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

}
