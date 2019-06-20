package ru.eremin.tm.backend.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eremin.tm.backend.api.service.IUserService;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.UserDTO;
import ru.eremin.tm.backend.model.entity.enumerated.Role;

import javax.annotation.PostConstruct;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Component
public class Bootstrap {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IncorrectDataException {
        @NotNull final UserDTO user = new UserDTO();
        user.setId("6bf0f091-e795-42d1-bb9a-77799cdf37da");
        user.setLogin("user");
        user.setHashPassword(passwordEncoder.encode("pass"));
        user.setRole(Role.USER);

        @NotNull final UserDTO admin = new UserDTO();
        admin.setId("6706e691-2f78-45ad-b021-3730c48959f0");
        admin.setLogin("admin");
        admin.setHashPassword(passwordEncoder.encode("pass"));
        admin.setRole(Role.ADMIN);

        @NotNull final UserDTO testUser = new UserDTO();
        testUser.setId("8208421e-86df-11e9-bc42-526af7764f64");
        testUser.setLogin("testUser");
        testUser.setHashPassword(passwordEncoder.encode("pass"));
        testUser.setRole(Role.USER);

        @NotNull final UserDTO testAdmin = new UserDTO();
        testAdmin.setId("f299a6aa-8769-11e9-bc42-526af7764f64");
        testAdmin.setLogin("testAdmin");
        testAdmin.setHashPassword(passwordEncoder.encode("pass"));
        testAdmin.setRole(Role.ADMIN);

        try {
            userService.findOne(user.getId());
        } catch (IncorrectDataException e) {
            userService.persist(user);
        }
        try {
            userService.findOne(admin.getId());
        } catch (IncorrectDataException e) {
            userService.persist(admin);
        }
        try {
            userService.findOne(testUser.getId());
        } catch (IncorrectDataException e) {
            userService.persist(testUser);
        }
        try {
            userService.findOne(testAdmin.getId());
        } catch (IncorrectDataException e) {
            userService.persist(testAdmin);
        }
    }

}
