package ru.eremin.tm.security;

import ru.eremin.tm.model.dto.UserDTO;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface IRegistrationService {

    void registration(final UserDTO userDTO);

}
