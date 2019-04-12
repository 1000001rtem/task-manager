package ru.eremin.tm.model.service;

import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IUserService extends IService<User, UserDTO> {

    UserDTO findByLogin(String login);

}
