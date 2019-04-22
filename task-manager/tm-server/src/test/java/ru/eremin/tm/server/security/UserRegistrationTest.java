package ru.eremin.tm.server.security;

import org.junit.Test;
import ru.eremin.tm.server.exeption.IncorrectDataException;
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

public class UserRegistrationTest {

//    @Test
//    public void userRegistrationTest() throws IncorrectDataException {
//        final IUserRepository userRepository = new UserRepository();
//    final IUserService userService = new UserService(userRepository);
//    final IRegistrationService registrationService = new RegistrationService(userService);
//
//    final UserDTO userDTO = new UserDTO();
//        userDTO.setLogin("testLogin");
//        userDTO.setHashPassword(PasswordHashUtil.md5("testPassword"));
//        userDTO.setRole(Role.USER);
//
//        registrationService.registration(userDTO);
//
//    assertNotNull(userService.findOne(userDTO.getId()));
//
//        userService.remove(userDTO.getId());
//}

}
