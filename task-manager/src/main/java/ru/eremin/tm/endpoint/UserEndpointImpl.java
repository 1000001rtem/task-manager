package ru.eremin.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.api.endpoint.UserEndpoint;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.util.PasswordHashUtil;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService(endpointInterface = "ru.eremin.tm.api.endpoint.UserEndpoint")
public class UserEndpointImpl implements UserEndpoint {

    @Autowired
    private IUserService userService;

    @Override
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @Override
    public UserDTO findOneUser(final @Nullable String userId) throws IncorrectDataException {
        return userService.findOne(userId);
    }

    @Override
    public UserDTO findUserByLogin(final @Nullable String login) throws IncorrectDataException {
        return userService.findByLogin(login);
    }

    @Override
    public ResultDTO createUser(final @Nullable String login, @Nullable final String password) throws IncorrectDataException {
        if (login == null || login.isEmpty()) return new ResultDTO(false);
        if (password == null || password.isEmpty()) return new ResultDTO(false);
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(PasswordHashUtil.md5(password));
        userDTO.setRole(Role.USER);
        userService.persist(userDTO);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO changeUserPassword(final @Nullable String userId, final @Nullable String oldPassword, final @Nullable String newPassword) throws IncorrectDataException {
        @NotNull final UserDTO userDTO = userService.findOne(userId);
        if (!userDTO.getHashPassword().equals(PasswordHashUtil.md5(oldPassword))) return new ResultDTO(false);
        userDTO.setHashPassword(PasswordHashUtil.md5(newPassword));
        userService.update(userDTO);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO updateUser(final @Nullable UserDTO userDTO) throws IncorrectDataException {
        userService.update(userDTO);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO removeUser(final @Nullable String userId) throws IncorrectDataException {
        userService.remove(userId);
        return new ResultDTO(true);
    }

}
