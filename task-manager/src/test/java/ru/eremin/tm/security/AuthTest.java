package ru.eremin.tm.security;

import org.junit.Test;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.Role;
import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.model.repository.UserRepository;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.UserService;
import ru.eremin.tm.model.service.api.IUserService;
import ru.eremin.tm.utils.Utils;

import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class AuthTest {

    @Test
    public void authTest() {
        final IUserRepository userRepository = new UserRepository();
        final IUserService userService = new UserService(userRepository);
        final IAuthService authService = new AuthService(userService);

        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLogin");
        userDTO.setHashPassword(Utils.getHash("testPassword"));
        userDTO.setRole(Role.USER);

        userService.persist(userDTO);

        final Session session = authService.login(userDTO.getLogin(), userDTO.getHashPassword());
        assertNotNull(session);

        userService.remove(userDTO.getId());
    }

}
