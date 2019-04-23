package ru.eremin.tm.server.security;

import org.junit.Test;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.repository.UserRepository;
import ru.eremin.tm.server.model.repository.api.IUserRepository;
import ru.eremin.tm.server.model.service.UserService;
import ru.eremin.tm.server.model.service.api.IUserService;
import ru.eremin.tm.server.utils.PasswordHashUtil;

import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class AuthTest {

    @Test
    public void authTest() throws IncorrectDataException, AccessForbiddenException {
        final IUserRepository userRepository = new UserRepository();
        final IUserService userService = new UserService(userRepository);
        final IAuthService authService = new AuthService(userService);

        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLogin");
        userDTO.setHashPassword(PasswordHashUtil.md5("testPassword"));
        userDTO.setRole(Role.USER);

        userService.persist(userDTO);

        final SessionDTO session = authService.login(userDTO.getLogin(), userDTO.getHashPassword());
        assertNotNull(session);

        userService.remove(userDTO.getId());
    }

}
