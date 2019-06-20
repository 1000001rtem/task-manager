package ru.eremin.tm.backend.api.service;

import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.UserDTO;
import ru.eremin.tm.backend.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserService extends IService<User, UserDTO> {

    List<UserDTO> findAll();

    UserDTO findByLogin(String login) throws IncorrectDataException;

    void removeAll();

}
