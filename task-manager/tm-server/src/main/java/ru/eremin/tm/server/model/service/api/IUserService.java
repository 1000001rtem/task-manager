package ru.eremin.tm.server.model.service.api;

import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserService extends IService<User, UserDTO> {

    List<UserDTO> findAll();

    UserDTO findByLogin(String login) throws IncorrectDataException;

}
