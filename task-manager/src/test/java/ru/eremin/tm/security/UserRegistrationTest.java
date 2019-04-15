package ru.eremin.tm.security;

import org.junit.Test;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.model.repository.UserRepository;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.UserService;
import ru.eremin.tm.model.service.api.IUserService;
import ru.eremin.tm.utils.Utils;

import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRegistrationTest {

    @Test
    public void userRegistrationTest() {
        final IUserRepository userRepository = new UserRepository();
        final IUserService userService = new UserService(userRepository);
        final IRegistrationService registrationService = new RegistrationService(userService);

        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLogin");
        userDTO.setHashPassword(Utils.getHash("testPassword"));
        userDTO.setRole(Role.USER);

        registrationService.registration(userDTO);

        assertNotNull(userService.findOne(userDTO.getId()));

        userService.remove(userDTO.getId());
    }

}
